package com.image.management.repository;


import com.image.management.model.ImageMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageMetaDataRepository extends JpaRepository<ImageMetaData, Integer>{

    List<ImageMetaData> findAllByOrderByImageIDAsc();

}
