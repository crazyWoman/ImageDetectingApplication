package com.image.management.repository;

import com.image.management.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

  @Query( " from Image image join image.imageMetaData metadata where metadata.name in :objects")
  List<Image> findImagesByObjectsList(@Param("objects") List<String> objects);

  Image findImageByImageID(@Param("imageID") Integer imageID);
}
