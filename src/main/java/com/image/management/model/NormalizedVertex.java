package com.image.management.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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

 /* @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "META_DATA_ID", nullable = false, updatable = false, insertable = false)
  private ImageMetaData imageMetaData;*/

/*  @ManyToOne()
  @JoinColumn(name="META_DATA_ID", referencedColumnName = "META_DATA_ID", insertable = false, updatable = false)
  private ImageMetaData imageMetaData;*/

}
