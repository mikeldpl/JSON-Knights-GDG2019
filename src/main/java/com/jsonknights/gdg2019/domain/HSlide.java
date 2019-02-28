package com.jsonknights.gdg2019.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class HSlide implements Slide {

    private final Photo photo;

    @Override
    public Set<String> getTags() {
        return photo.getTags();
    }

    @Override
    public List<Photo> getPhotos() {
        return Collections.singletonList(photo);
    }
}
