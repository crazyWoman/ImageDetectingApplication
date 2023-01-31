package com.image.management.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImageDataVO {
    private Integer imageID;
    private String imageLabel;
    private List<ImageMetaDataVO> imageMetaDataVOList;

}
