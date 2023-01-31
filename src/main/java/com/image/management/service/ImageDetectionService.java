package com.image.management.service;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;
import com.image.management.controller.request.ImageDetectionRequest;
import com.image.management.controller.response.ImageDataVO;
import com.image.management.exception.NotFoundException;
import com.image.management.mapper.ImageMapper;
import com.image.management.mapper.ImageVOMapper;
import com.image.management.model.Image;
import com.image.management.model.ImageMetaData;
import com.image.management.repository.ImageRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageDetectionService {

  private final ResourceLoader resourceLoader;
  private final CloudVisionTemplate cloudVisionTemplate;

  private final ImageRepository imageRepository;

private  final ImageMetaDataMapper imageMetaDataMapper;

private final ImageMapper imageMapper = Mappers.getMapper(ImageMapper.class);
private final ImageVOMapper imageVOMapper = Mappers.getMapper(ImageVOMapper.class);


  public ImageDetectionService(ResourceLoader resourceLoader, CloudVisionTemplate cloudVisionTemplate, ImageRepository imageRepository, ImageMetaDataMapper imageMetaDataMapper) {
    this.resourceLoader = resourceLoader;
    this.cloudVisionTemplate = cloudVisionTemplate;
      this.imageRepository = imageRepository;
      this.imageMetaDataMapper = imageMetaDataMapper;

  }

  public ImageDataVO saveImage(final ImageDetectionRequest imageDetectionRequest) {
     Image image = new Image();
    final byte[] fileContent = extractTheImage(imageDetectionRequest);
    final List<LocalizedObjectAnnotation>  annotations = getLocalizedObjectAnnotation(imageDetectionRequest);
    List<ImageMetaData> imageMetaData =  imageMapper.localizedObjectAnnotationListToImageMetaDataList(annotations);
      image.setImageData(fileContent);
      image.setImageLabel(imageDetectionRequest.getImageLabel());
      image.setImageMetaData(imageMetaData);
      imageRepository.save(image);
      return imageVOMapper.mapImageToImageVO(imageRepository.findImagesWithMetaData());
  }

  public byte[] extractTheImage(ImageDetectionRequest imageDetectionRequest) {

    return Optional.ofNullable(imageDetectionRequest)
        .map(ImageDetectionRequest::getImageURL)
        .map(url -> resourceLoader.getResource(url))
        .map(
            resource -> {
              try {
                return resource.getInputStream();
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            })
        .map(
            inputStream -> {
              try {
                return inputStream.readAllBytes();
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            })
        .orElseThrow(
            () ->
                new NotFoundException(
                    "Not able extract the image content. File or imageURL is null  "));
  }

  public List<LocalizedObjectAnnotation> getLocalizedObjectAnnotation(final ImageDetectionRequest imageDetectionRequest) {
    return
        cloudVisionTemplate
            .analyzeImage(
                resourceLoader.getResource(imageDetectionRequest.getImageURL()),
                Feature.Type.OBJECT_LOCALIZATION)
            .getLocalizedObjectAnnotationsList();
  }
}
