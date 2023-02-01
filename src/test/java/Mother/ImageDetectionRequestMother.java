package Mother;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.image.management.controller.request.ImageDetectionRequest;

public class ImageDetectionRequestMother implements Builder<ImageDetectionRequest> {

  private String imageURL;

  private String imageLabel;

  private boolean objectProcessing;

  public ImageDetectionRequestMother withImageURL(String imageURL) {
    this.imageURL = imageURL;
    return this;
  }

  public ImageDetectionRequestMother withImageLabel(String imageLabel) {
    this.imageLabel = imageLabel;
    return this;
  }

  public ImageDetectionRequestMother withObjectProcessing(boolean objectProcessing) {
    this.objectProcessing = objectProcessing;
    return this;
  }

  @Override
  public ImageDetectionRequest build() {
    ImageDetectionRequest imageDetectionRequest = new ImageDetectionRequest();
    imageDetectionRequest.setImageURL(this.imageURL);
    imageDetectionRequest.setImageLabel(this.imageLabel);
    imageDetectionRequest.setObjectProcessing(this.objectProcessing);
    return imageDetectionRequest;
  }
}
