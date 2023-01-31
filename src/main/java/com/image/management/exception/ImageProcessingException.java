package com.image.management.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ImageProcessingException extends RuntimeException {

  private final String issueReason;
  private final HttpStatus httpStatus;
  private final List<String> parameters;

  public ImageProcessingException(
      final String issueReason, HttpStatus httpStatus, List<String> parameters) {
    this.httpStatus = httpStatus;
    this.parameters = parameters;
    this.issueReason = issueReason;
  }
}
