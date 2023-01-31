package com.image.management.service;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;
import com.image.management.controller.request.ImageDetectionRequest;
import com.image.management.controller.response.ImageDataVO;
import com.image.management.controller.response.ImageMetaDataVO;
import com.image.management.exception.*;
import com.image.management.mapper.ImageMapper;
import com.image.management.mapper.ImageVOMapper;
import com.image.management.model.Image;
import com.image.management.model.ImageMetaData;
import com.image.management.repository.ImageMetaDataRepository;
import com.image.management.repository.ImageRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageDetectionService {

  private final ResourceLoader resourceLoader;
  private final CloudVisionTemplate cloudVisionTemplate;

  private final ImageRepository imageRepository;
  private final ImageMetaDataRepository imageMetaDataRepository;

  private final ImageMapper imageMapper = Mappers.getMapper(ImageMapper.class);
  private final ImageVOMapper imageVOMapper = Mappers.getMapper(ImageVOMapper.class);

  public ImageDetectionService(
      ResourceLoader resourceLoader,
      CloudVisionTemplate cloudVisionTemplate,
      ImageRepository imageRepository,
      ImageMetaDataRepository imageMetaDataRepository) {
    this.resourceLoader = resourceLoader;
    this.cloudVisionTemplate = cloudVisionTemplate;
    this.imageRepository = imageRepository;
    this.imageMetaDataRepository = imageMetaDataRepository;
  }

  public ImageDataVO saveImage(final ImageDetectionRequest imageDetectionRequest) {
    Image image = new Image();
    final byte[] fileContent = extractTheImage(imageDetectionRequest);
    final List<LocalizedObjectAnnotation> annotations =
        getLocalizedObjectAnnotation(imageDetectionRequest);
    List<ImageMetaData> imageMetaData =
        imageMapper.localizedObjectAnnotationListToImageMetaDataList(annotations);
    image.setImageData(fileContent);
    image.setImageLabel(imageDetectionRequest.getImageLabel());
    image.setImageMetaData(imageMetaData);
    Image image1 = imageRepository.save(image);
    return imageVOMapper.mapImageToImageVO(imageRepository.findImageByImageID(image1.getImageID()));
  }

  public ImageDataVO saveOnlyImage(final ImageDetectionRequest imageDetectionRequest) {
    Image image = new Image();
    final byte[] fileContent = extractTheImage(imageDetectionRequest);
    image.setImageData(fileContent);
    image.setImageLabel(imageDetectionRequest.getImageLabel());
    image.setImageMetaData(null);
    Image image1 = imageRepository.save(image);
    return imageVOMapper.mapImageToImageVO(imageRepository.findImageByImageID(image1.getImageID()));
  }

  public List<ImageMetaDataVO> fetchImage(final Integer imageId) {
    return Optional.ofNullable(imageRepository.findImageByImageID(imageId))
        .map(
            image ->
                imageVOMapper.mapImageMetaDataLstToImageMetaDataVOLst(image.getImageMetaData()))
        .orElse(null);
  }

  public List<ImageMetaDataVO> fetchAllImages() {
    List<ImageMetaData> metaDataList =
        Optional.ofNullable(imageRepository.findAll()).stream()
            .flatMap(Collection::stream)
            .map(Image::getImageMetaData)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    return Optional.of(metaDataList)
        .map(imageVOMapper::mapImageMetaDataLstToImageMetaDataVOLst)
        .orElse(null);
  }

  public List<ImageMetaDataVO> fetchAllImagesForSpecificMetaData(List<String> names) {
    List<ImageMetaData> metaDataList =
        Optional.ofNullable(imageRepository.findImagesByObjectsList(names)).stream()
            .flatMap(Collection::stream)
            .map(Image::getImageMetaData)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    return Optional.of(metaDataList)
        .map(imageVOMapper::mapImageMetaDataLstToImageMetaDataVOLst)
        .orElse(null);
  }

  public List<ImageMetaDataVO> fetchAllMetaDataForSpecificNames(List<String> names) {
    List<ImageMetaData> metaDataList =
        Optional.ofNullable(imageMetaDataRepository.findImageMetaDataByNames(names)).stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    return Optional.of(metaDataList)
        .map(imageVOMapper::mapImageMetaDataLstToImageMetaDataVOLst)
        .orElse(null);
  }

  public byte[] extractTheImage(ImageDetectionRequest imageDetectionRequest) {

    return Optional.ofNullable(imageDetectionRequest)
        .map(ImageDetectionRequest::getImageURL)
        .map(resourceLoader::getResource)
        .map(
            resource -> {
              try {
                return resource.getInputStream();
              } catch (IOException e) {
                throw new ImageFileException();
              }
            })
        .map(
            inputStream -> {
              try {
                return inputStream.readAllBytes();
              } catch (IOException e) {
                throw new ImageReadingException();
              }
            })
        .orElseThrow(URLMissingException::new);
  }

  public List<LocalizedObjectAnnotation> getLocalizedObjectAnnotation(
      final ImageDetectionRequest imageDetectionRequest) {
    return cloudVisionTemplate
        .analyzeImage(
            resourceLoader.getResource(imageDetectionRequest.getImageURL()),
            Feature.Type.OBJECT_LOCALIZATION)
        .getLocalizedObjectAnnotationsList();
  }
}
