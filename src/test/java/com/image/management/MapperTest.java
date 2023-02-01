package com.image.management;

import Mother.ImageMetadataMother;
import Mother.ImageMother;
import Mother.NormalizedVertexMother;

import com.image.management.controller.response.ImageDataVO;
import com.image.management.controller.response.NormalizedVertexVO;
import com.image.management.mapper.ImageVOMapper;
import com.image.management.model.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MapperTest {
    Image image;


    private final ImageVOMapper imageVOMapper = Mappers.getMapper(ImageVOMapper.class);

    @BeforeEach
    public void setUp(){
        ImageMother imageMother =
                new ImageMother()
                        .setImageID(1)
                        .setImageLabel("Test_Image")
                        .setImageMetadataMother(
                                new ImageMetadataMother()
                                        .setImageID(1)
                                        .setObjectScore(.98f)
                                        .setName("Bicycle")
                                        .setMid("1234")
                                        .setMetaDataId(2L)
                                        .setNormalizedVertexMother(
                                                new NormalizedVertexMother().setYCoordinate(.3f).setXCoordinate(.25f)));
        image = imageMother.build();

    }

    @Test
    public void willImageMaptoImageVO(){
        ImageDataVO imageDataVO =  imageVOMapper.mapImageToImageVO(image);

        Assertions.assertEquals(imageDataVO.getImageID(),image.getImageID());

        assertThat(imageDataVO.getImageMetaDataVOList())
                .isNotNull()
                .extracting("mid", "name", "objectScore")
                .contains(tuple("1234", "Bicycle", .98f));

        NormalizedVertexVO normalizedVertexVO = imageDataVO.getImageMetaDataVOList().get(0).getNormalizedVertexVOSList().get(0);

        Assertions.assertEquals(normalizedVertexVO.getXCoordinate(),0.25f);



    }
}
