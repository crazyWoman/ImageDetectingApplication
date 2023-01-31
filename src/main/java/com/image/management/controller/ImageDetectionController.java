package com.image.management.controller;

import com.image.management.controller.request.ImageDetectionRequest;
import com.image.management.controller.response.ImageDataVO;
import com.image.management.controller.response.ImageMetaDataVO;
import com.image.management.service.ImageDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageDetectionController {
  private final ImageDetectionService imageDetectionService;

  public ImageDetectionController(ImageDetectionService imageDetectionService) {
    this.imageDetectionService = imageDetectionService;
  }

  @PostMapping(consumes = "application/json")
  @Operation(summary = "Endpoint to process an image based on some specific criteria.")
  public @ResponseBody ResponseEntity<ImageDataVO> uploadImage(
      @Valid @RequestBody ImageDetectionRequest imageDetectionRequest) {
    ImageDataVO image = imageDetectionService.saveImage(imageDetectionRequest);
    return new ResponseEntity<>(image, HttpStatus.OK);
  }

  @GetMapping("/{imageId}")
  @Operation(summary = "Endpoint to process an image based on image id.")
  public @ResponseBody ResponseEntity<List<ImageMetaDataVO>> findImageById(@PathVariable Integer imageId) {
    List<ImageMetaDataVO> imageMetaDatalist = imageDetectionService.fetchImage(imageId);
    return new ResponseEntity<>(imageMetaDatalist
            , HttpStatus.OK);
  }

  @GetMapping("")
  @Operation(summary = "Endpoint to retrieve all available image metadata or images that contain certain objects, if no parameter passed, it will bring all data")
  public @ResponseBody ResponseEntity<List<ImageMetaDataVO>> getAllImageMetaData(
      @RequestParam(value = "objects", required = false) List<String> names) {
    List<ImageMetaDataVO> images =
        Optional.ofNullable(names)
            .filter(strings -> strings.size() > 0)
            .map(imageDetectionService::fetchAllMetaDataForSpecificNames)
            .orElse(imageDetectionService.fetchAllImages());

    return new ResponseEntity<>(images, HttpStatus.OK);
  }
}
