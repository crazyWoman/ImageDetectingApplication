package com.image.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TBL_NORMALIZED_VERTICES")
public class NormalizedVertex {
  @Id
  @SequenceGenerator(
      name = "VERTICES_ID_GENERATOR",
      sequenceName = "VERTICES_ID_SEQUENCE",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERTICES_ID_GENERATOR")
  @Column(name = "VERTICES_ID")
  private Long verticesId;

  @Column(name = "META_DATA_ID")
  private Long metaDataId;

  @Column(name = "X_COORDINATE")
  private float xCoordinate;

  @Column(name = "Y_COORDINATE")
  private float yCoordinate;
}
