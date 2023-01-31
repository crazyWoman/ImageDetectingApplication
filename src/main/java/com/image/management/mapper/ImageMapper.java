package com.image.management.mapper;

import com.google.cloud.vision.v1.BoundingPoly;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;

import com.image.management.model.ImageMetaData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper()
public interface ImageMapper {
  @Mapping(source = "mid", target = "mid")
  @Mapping(source = "score", target = "objectScore")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "boundingPoly.normalizedVerticesList", target = "normalizedVertices")
  ImageMetaData localizedObjectAnnotationToImageMetaData(
      LocalizedObjectAnnotation localizedObjectAnnotation);

    List<ImageMetaData> localizedObjectAnnotationListToImageMetaDataList(List<LocalizedObjectAnnotation> localizedObjectAnnotations);


    @Mapping(source = "x", target = "XCoordinate")
    @Mapping(source = "y", target = "YCoordinate")
    com.image.management.model.NormalizedVertex normalizedVertexToModelNormalizedVertices(com.google.cloud.vision.v1.NormalizedVertex normalizedVertex);


}
