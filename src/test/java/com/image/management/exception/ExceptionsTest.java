package com.image.management.exception;

import com.image.management.controller.request.ImageDetectionRequest;
import com.image.management.service.ImageDetectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class ExceptionsTest {

    @Autowired
    ImageDetectionService sut;

    @Test
    public void willThrowURLMissingException() {
        ImageDetectionRequest request = new ImageDetectionRequest();
        request.setImageURL(null);
        Assertions.assertThrows(URLMissingException.class, () -> sut.saveImage(request));
    }

    @Test
    public void willThrowImageFileException() {
        ImageDetectionRequest request = new ImageDetectionRequest();
        request.setImageURL("classPath:pics/bike.png");
        Assertions.assertThrows(ImageFileException.class, () -> sut.saveImage(request));

    }
}
