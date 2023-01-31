package com.image.management.service;

import com.image.management.controller.request.ImageDetectionRequest;
import com.image.management.exception.ImageProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.validation.constraints.NotEmpty;

@SpringBootTest
class ImageDetectionServiceTest {
  @Autowired ImageDetectionService sut;

  @Test
  public void willEmptyURLThrowNotFoundException() {
    ImageDetectionRequest request = new ImageDetectionRequest();
    request.setImageURL(null);
    Assertions.assertThrows(ImageProcessingException.class, () -> sut.extractTheImage(request));
  }

  @Test
  public void willnullObjectThrowNotFoundException() {
    ImageDetectionRequest request = null;
    Assertions.assertThrows(ImageProcessingException.class, () -> sut.extractTheImage(request));
  }

  @Test
  public void willSaveTheImage() {
    ImageDetectionRequest request = new ImageDetectionRequest();
    @NotEmpty String ClassPath;
    request.setImageURL( "c:/ClientHealth/living.PNG");
    request.setImageLabel("room");
    sut.saveImage(request);
    Assertions.assertThrows(ImageProcessingException.class, () -> sut.extractTheImage(request));
  }
}
