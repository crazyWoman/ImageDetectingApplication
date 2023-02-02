package com.image.management.repository;

import com.image.management.model.Image;
import com.image.management.model.ImageMetaData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@SqlGroup({

        @Sql("/test-insert.sql")
})

public class ImageRepositoryIntegrationTest {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMetaDataRepository imageMetaDataRepository;


    @Test
    public void testImage(){
        Image  images = imageRepository.findImageByImageID(200);
        assertThat(images.getImageID()).isEqualTo(200L);
        assertThat(images.getImageMetaData().size()).isEqualTo(2);
        assertThat(images.getImageMetaData().get(0).getNormalizedVertices().size()).isEqualTo(2);
        assertThat(images.getImageMetaData().get(0).getMetaDataId()).isEqualTo(100L);
        assertThat(images.getImageMetaData().get(0).getNormalizedVertices().get(0).getVerticesId()).isEqualTo(400L);
    }

    @Test
    public void testMetaData(){
        Image  images = imageRepository.findImageByImageID(200);
        assertThat(images.getImageMetaData())
                .isNotNull()
                .extracting("mid", "name", "objectScore")
                .contains(tuple("/m/0199g", "Bicycle", 0.95442045f));
    }

    @Test
    public void testMetaDataByObjects(){
        List<String> nameList = List.of("Bicycle","chair");
        List<ImageMetaData>  images = imageMetaDataRepository.findImageMetaDataByNames(nameList);
        assertThat(images.size()).isEqualTo(2);
        assertThat(images.get(0).getNormalizedVertices().size()).isEqualTo(2);

    }

    @Test
    public void testMetaDataByObjectsWithNativeQuery(){
        List<String> nameList = List.of("Bicycle","chair");
        List<Image>  images = imageRepository.findImagesByObjects(nameList);
        assertThat(images.size()).isEqualTo(1);


    }



}
