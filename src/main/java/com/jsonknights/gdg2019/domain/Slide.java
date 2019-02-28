package com.jsonknights.gdg2019.domain;

import java.util.List;
import java.util.Set;

public interface Slide {
    Set<String> getTags();

    List<Photo> getPhotos();
}
