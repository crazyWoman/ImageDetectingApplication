package Mother;

import com.image.management.model.Image;
import com.image.management.model.ImageMetaData;

import java.util.List;

public class ImageMother implements Builder<Image> {

  private Integer imageID;

  private String imageLabel;

    ImageMetadataMother imageMetadataMother = new ImageMetadataMother();

    public ImageMother setImageID(Integer imageID) {
        this.imageID = imageID;
        return this;
    }

    public ImageMother setImageLabel(String imageLabel) {
        this.imageLabel = imageLabel;
        return this;
    }

    public ImageMother setImageMetadataMother(ImageMetadataMother imageMetadataMother) {
        this.imageMetadataMother = imageMetadataMother;
        return this;
    }

    @Override
  public Image build() {
        Image image = new Image();
        image.setImageMetaData(List.of(imageMetadataMother.build()));
        image.setImageLabel(this.imageLabel);
        image.setImageID(this.imageID);
    return null;
  }
}
