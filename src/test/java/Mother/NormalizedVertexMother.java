package Mother;

import com.image.management.model.NormalizedVertex;

public class NormalizedVertexMother implements Builder<NormalizedVertex> {

  private float xCoordinate;

  private float yCoordinate;

  public NormalizedVertexMother setxCoordinate(float xCoordinate) {
    this.xCoordinate = xCoordinate;
    return this;
  }

  public NormalizedVertexMother setyCoordinate(float yCoordinate) {
    this.yCoordinate = yCoordinate;
    return this;
  }

  @Override
  public NormalizedVertex build() {
    NormalizedVertex normalizedVertex = new NormalizedVertex();
    normalizedVertex.setXCoordinate(this.xCoordinate);
    normalizedVertex.setYCoordinate(this.yCoordinate);
    return normalizedVertex;
  }
}
