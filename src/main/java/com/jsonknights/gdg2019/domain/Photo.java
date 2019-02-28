package com.jsonknights.gdg2019.domain;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Photo {
    private int index;
    private boolean horizontal;
    private Set<String> tags;
}
