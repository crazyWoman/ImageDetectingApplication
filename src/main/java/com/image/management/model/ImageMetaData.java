package com.image.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TBL_IMAGE_METADATA")
public class ImageMetaData {
    @Id
    @SequenceGenerator(
            name = "META_DATA_ID_GENERATOR",
            sequenceName = "META_DATA_ID_SEQUENCE",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "META_DATA_ID_GENERATOR")
    @Column(name = "META_DATA_ID")
    private Long metaDataId;

    @Column(name = "OBJECT_MID")
    private String mid;

    @Column(name = "META_DATA_NAME")
    private String name;

    @Column(name = "OBJECT_SCORE")
    private float objectScore;

    @Column(name = "IMAGE_ID")
    private Integer imageID;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "META_DATA_ID")
    private List<NormalizedVertex> normalizedVertices;
}
