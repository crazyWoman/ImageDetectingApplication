package com.image.management.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ImageExceptionController {
    @ExceptionHandler(value = ImageNotFoundException.class)
    public ResponseEntity<Object> exception(ImageNotFoundException exception) {
        return new ResponseEntity<>(ExceptionConstants.IMAGE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = ImageReadingException.class)
    public ResponseEntity<Object> readingException(ImageReadingException exception) {
        return new ResponseEntity<>(ExceptionConstants.UNABLE_TO_READ, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ImageFileException.class)
    public ResponseEntity<Object> imageFileException(ImageFileException exception) {
        return new ResponseEntity<>(ExceptionConstants.UNABLE_TO_READ, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = URLMissingException.class)
    public ResponseEntity<Object> urlMissingException(URLMissingException exception) {
        return new ResponseEntity<>(ExceptionConstants.URL_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }



}
