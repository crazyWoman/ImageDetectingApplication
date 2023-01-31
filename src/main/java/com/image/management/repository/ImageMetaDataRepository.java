package com.image.management.repository;

import com.image.management.model.Image;
import com.image.management.model.ImageMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageMetaDataRepository extends JpaRepository<ImageMetaData, Integer> {

  @Query( " from ImageMetaData metadata join metadata.normalizedVertices vertices where metadata.name in :objects")
  List<ImageMetaData> findImageMetaDataByNames(@Param("objects") List<String> objects);
}
