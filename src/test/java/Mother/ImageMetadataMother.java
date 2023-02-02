package Mother;

import com.image.management.model.ImageMetaData;

import java.util.List;

public class ImageMetadataMother implements Builder<ImageMetaData> {

  private Long metaDataId;

  private String mid;

  private String name;

  private float objectScore;

  private Integer imageID;

  private NormalizedVertexMother normalizedVertexMother = new NormalizedVertexMother();

  public ImageMetadataMother setMetaDataId(Long metaDataId) {
    this.metaDataId = metaDataId;
    return this;
  }

  public ImageMetadataMother setMid(String mid) {
    this.mid = mid;
    return this;
  }

  public ImageMetadataMother setName(String name) {
    this.name = name;
    return this;
  }

  public ImageMetadataMother setObjectScore(float objectScore) {
    this.objectScore = objectScore;
    return this;
  }

  public ImageMetadataMother setImageID(Integer imageID) {
    this.imageID = imageID;
    return this;
  }

  public ImageMetadataMother setNormalizedVertexMother(
      NormalizedVertexMother normalizedVertexMother) {
    this.normalizedVertexMother = normalizedVertexMother;
    return this;
  }

  @Override
  public ImageMetaData build() {
    ImageMetaData metaData = new ImageMetaData();
    metaData.setName(this.name);
    metaData.setObjectScore(this.objectScore);
    metaData.setMid(this.mid);
    metaData.setNormalizedVertices(List.of(this.normalizedVertexMother.build()));
    return metaData;
  }
}
