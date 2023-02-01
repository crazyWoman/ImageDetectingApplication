package com.image.management.controller;

import Mother.ImageMetadataMother;
import Mother.ImageMother;
import Mother.NormalizedVertexMother;
import com.image.management.controller.response.ImageDataVO;
import com.image.management.controller.response.ImageMetaDataVO;
import com.image.management.mapper.ImageVOMapper;
import com.image.management.model.Image;
import com.image.management.model.ImageMetaData;
import com.image.management.model.NormalizedVertex;
import com.image.management.repository.ImageRepository;
import com.image.management.service.ImageDetectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("ALL")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class ImageControllerTest {

  final String expected =
      "[{\"imageID\":1,\"metaData\":[{\"imageObjectMid\":\"1234\",\"imageObjectName\":\"Bicyle\",\"imageObjectScore\":0.98,\"objectVerticeList\":[{\"x\":0.25,\"y\":0.3}]}]}]";

  @Value("classpath:images/test.jpeg")
  private Resource image2;

  @Value(("classpath:images/bicycle_example.png"))
  private Resource image3;

  @Autowired private org.springframework.test.web.servlet.MockMvc mockMvc;

  private final ImageVOMapper imageVOMapper = Mappers.getMapper(ImageVOMapper.class);

  private ImageRepository imageRepository = mock(ImageRepository.class);
  private ImageDetectionService imageDetectionService = mock(ImageDetectionService.class);

  ImageDetectionController sut = new ImageDetectionController(imageDetectionService);

  @Test
  void testGetAllMetadataForAllImages() throws Exception {

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
                        new NormalizedVertexMother().setyCoordinate(.30f).setxCoordinate(.25f)));
    Image image = imageMother.build();
    ImageMetaDataVO imageDataVO =
        imageVOMapper.mapImageMetaDataToImageMetaDataVO(image.getImageMetaData().get(0));

    when(imageRepository.findImageByImageID(1)).thenReturn(image);

    when(imageDetectionService.fetchImage(any())).thenReturn(List.of(imageDataVO));

    ResponseEntity<List<ImageMetaDataVO>> responseEntity = sut.findImageById(1);

    assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    assertThat(responseEntity.getBody().get(0)).isInstanceOf(ImageMetaDataVO.class);
  }
}
