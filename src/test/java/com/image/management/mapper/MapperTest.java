package com.image.management.mapper;

import Mother.ImageMetadataMother;
import Mother.ImageMother;
import Mother.NormalizedVertexMother;
import com.image.management.controller.response.ImageDataVO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MapperTest {


    private final ImageVOMapper imageVOMapper = Mappers.getMapper(ImageVOMapper.class);

    @Test
    void testImageVOMapper() throws Exception {
    ImageMother imageMother =
        new ImageMother()
            .setImageID(1)
            .setImageLabel("Test_Image")
            .setImageMetadataMother(
                new ImageMetadataMother()
                    .setImageID(1)
                    .setObjectScore(.98f)
                    .setName("Bicyle")
                    .setMid("1234")
                    .setMetaDataId(2L)
                    .setNormalizedVertexMother(
                        new NormalizedVertexMother().setYCoordinate(.39f).setXCoordinate(.25f)));
        ImageDataVO imageDataVO =imageVOMapper.mapImageToImageVO(imageMother.build());
        assertThat(imageDataVO.getImageMetaDataVOList().size()).isEqualTo(1);
        assertThat(imageDataVO.getImageMetaDataVOList())
                .isNotNull()
                .extracting("mid", "name", "objectScore")
                .contains(tuple("1234", "Bicyle", .98f));

        assertThat(imageDataVO.getImageMetaDataVOList().get(0).getNormalizedVertexVOSList())
                .isNotNull()
                .extracting("YCoordinate", "XCoordinate")
                .contains(tuple(.39f,.25f));


    }
}
