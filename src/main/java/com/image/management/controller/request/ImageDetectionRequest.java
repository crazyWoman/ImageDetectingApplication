package com.image.management.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"imageURL"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDetectionRequest {

  @JsonProperty("imageURL")
  @NotEmpty
  private String imageURL;

  @JsonProperty("imageLabel")
  private String imageLabel;

  @JsonProperty("objectProcessing")
  private boolean objectProcessing;
}
