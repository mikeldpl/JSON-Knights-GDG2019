package com.jsonknights.gdg2019.domain;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class SlideShow {
    private final List<Slide> slides;
}
