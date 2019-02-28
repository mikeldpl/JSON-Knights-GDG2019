package com.jsonknights.gdg2019.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import lombok.Data;

@Data
public class VSlide implements Slide {
    private final Photo firstPhoto;
    private final Photo secondPhoto;
    private final HashSet<String> tags;

    public VSlide(Photo firstPhoto, Photo secondPhoto) {
        validate(firstPhoto);
        validate(secondPhoto);
        this.firstPhoto = firstPhoto;
        this.secondPhoto = secondPhoto;

        this.tags = new HashSet<>(firstPhoto.getTags());
        this.tags.addAll(secondPhoto.getTags());
    }

    private void validate(Photo firstPhoto) {
        if (firstPhoto.isHorizontal()){
            throw new IllegalArgumentException(firstPhoto + " is horizontal");
        }
    }

    @Override
    public List<Photo> getPhotos() {
        return Arrays.asList(firstPhoto, secondPhoto);
    }
}
