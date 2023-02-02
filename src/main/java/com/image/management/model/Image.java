package com.image.management.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "TBL_IMAGE")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Image {

  @Id
  @SequenceGenerator(
      name = "IMAGE_ID_GENERATOR",
      sequenceName = "IMAGE_ID_SEQUENCE",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_ID_GENERATOR")
  @Column(name = "IMAGE_ID")
  private Integer imageID;

  @Lob
  @Column(name = "IMAGE_DATA")
  private byte[] imageData;

  @Column(name = "IMAGE_LABEL")
  private String imageLabel;


/*  @OneToMany(targetEntity=ImageMetaData.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ImageMetaData> ImageMetaData;*/

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name="IMAGE_ID", referencedColumnName = "IMAGE_ID")
  private List<ImageMetaData> imageMetaData;


}
