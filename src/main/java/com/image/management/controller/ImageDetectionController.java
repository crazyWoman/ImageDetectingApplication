package com.image.management.controller;

import com.image.management.controller.request.ImageDetectionRequest;
import com.image.management.controller.response.ImageDataVO;
import com.image.management.mapper.ImageMapper;
import com.image.management.mapper.ImageVOMapper;
import com.image.management.model.Image;
import com.image.management.service.ImageDetectionService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/images")
public class ImageDetectionController {
    private ImageDetectionService imageDetectionService;


    public ImageDetectionController(ImageDetectionService imageDetectionService) {
        this.imageDetectionService = imageDetectionService;
    }

    @PostMapping(consumes = "application/json")
    @Operation(summary = "Endpoint to process an image based on some specific criteria.")
    public @ResponseBody ResponseEntity<ImageDataVO> uploadImage(@Valid @RequestBody ImageDetectionRequest imageDetectionRequest){
        ImageDataVO image =  imageDetectionService.saveImage(imageDetectionRequest);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
