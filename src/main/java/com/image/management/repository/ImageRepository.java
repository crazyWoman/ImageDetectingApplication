package com.image.management.repository;

import com.image.management.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

  @Query(value = " select distinct i from Image i left join fetch i.imageMetaData m where m.name in :objects")
  List<Image> findImagesByObjectsList(@Param("objects") List<String> objects);

  @Query(value = "select distinct i from Image i left join fetch i.imageMetaData m")
  List<Image> findImageByImageID();

/*  @Query(value = " from Image i left join fetch i.imageMetaData m where i.imageID = :imageId")
  Image findImageByImageID(@Param("imageId") Integer imageId);*/


 // @Query(" from Image image where  image.imageID = :imageId")
  Image findImageByImageID(@Param("imageId") Integer imageId);
}
