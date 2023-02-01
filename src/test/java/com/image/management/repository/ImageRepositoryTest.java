package com.image.management.repository;

import com.image.management.model.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@SqlGroup({

        @Sql("/test-insert.sql")
})

public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;


    @Test
    public void testImage(){
        Image  images = imageRepository.findImageByImageID(200);
        assertThat(images.getImageID()).isEqualTo(200L);
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


}
