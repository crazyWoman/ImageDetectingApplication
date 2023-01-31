package com.image.management.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ImageMetaDataVO {
    private Long metaDataId;
    private Long imageID;
    private String mid;
    private String name;
    private float objectScore;
    private List<NormalizedVertexVO> normalizedVertexVOSList;


}
