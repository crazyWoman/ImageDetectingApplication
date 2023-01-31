package com.image.management.mapper;

import com.image.management.controller.response.ImageDataVO;
import com.image.management.controller.response.ImageMetaDataVO;
import com.image.management.controller.response.NormalizedVertexVO;
import com.image.management.model.Image;
import com.image.management.model.ImageMetaData;
import com.image.management.model.NormalizedVertex;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper()
public interface ImageVOMapper {

  @Mapping(source = "imageID", target = "imageID")
  @Mapping(source = "imageLabel", target = "imageLabel")
  @Mapping(source = "imageMetaData", target = "imageMetaDataVOList")
  ImageDataVO mapImageToImageVO(Image image);

  @Mapping(source = "metaDataId", target = "metaDataId")
  @Mapping(source = "imageID", target = "imageID")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "mid", target = "mid")
  @Mapping(source = "objectScore", target = "objectScore")
  @Mapping(source = "normalizedVertices", target = "normalizedVertexVOSList")
  ImageMetaDataVO mapImageMetaDataToImageMetaDataVO(ImageMetaData metaData);

  List<ImageMetaDataVO> mapImageMetaDataLstToImageMetaDataVOLst(List<ImageMetaData> metaData);

  @Mapping(source = "XCoordinate", target = "XCoordinate")
  @Mapping(source = "YCoordinate", target = "YCoordinate")
  @Mapping(source = "verticesId", target = "verticesId")
  NormalizedVertexVO mapNormalizedVertexToNormalizedVertexVO(NormalizedVertex normalizedVertex);
}
