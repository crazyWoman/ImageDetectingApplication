package com.image.management.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_IMAGE_METADATA")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ImageMetaData {
  @Id
  @SequenceGenerator(
      name = "META_DATA_ID_GENERATOR",
      sequenceName = "META_DATA_ID_SEQUENCE",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "META_DATA_ID_GENERATOR")
  @Column(name = "META_DATA_ID")
  private Long metaDataId;

  @Column(name = "IMAGE_ID")
  private Integer imageID;


  @Column(name = "OBJECT_MID")
  private String mid;

  @Column(name = "META_DATA_NAME")
  private String name;

  @Column(name = "OBJECT_SCORE")
  private float objectScore;


/*  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "META_DATA_ID")
  private List<NormalizedVertex> normalizedVertices;*/

  /*@OneToMany(targetEntity=NormalizedVertex.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
  private List<NormalizedVertex> normalizedVertices;
*/


/*  @ManyToOne()
  @JoinColumn(name="IMAGE_ID", referencedColumnName = "IMAGE_ID", insertable = false, updatable = false)
  private Image image;*/
  /*@ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="IMAGE_ID", referencedColumnName = "IMAGE_ID")
  private Image image;*/

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name="META_DATA_ID", referencedColumnName = "META_DATA_ID")
  private List<NormalizedVertex> normalizedVertices;


}

