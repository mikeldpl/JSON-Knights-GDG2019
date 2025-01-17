package com.jsonknights.gdg2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jsonknights.gdg2019.domain.Photo;
import com.jsonknights.gdg2019.domain.Slide;
import com.jsonknights.gdg2019.domain.SlideShow;

public class InOutManager {

    private Path in;
    private Path out;

    public InOutManager(Path in, Path out) {
        this.in = in;
        this.out = out;
    }

    public void submitResult(SlideShow slideShow) throws IOException {
        List<Slide> slides = slideShow.getSlides();
        List<String> collect = Stream.concat(Stream.of(String.valueOf(slides.size())),
                                             slides.stream()
                                                     .map(slide -> slide.getPhotos().stream()
                                                             .map(Photo::getIndex)
                                                             .map(Object::toString)
                                                             .collect(Collectors.joining(" "))))
                .collect(Collectors.toList());
        Files.write(out, collect);
    }

    public List<Photo> readPhotos() throws IOException {
        List<String> strings = Files.readAllLines(in);
        if (strings.size() <= 1) {
            throw new IllegalArgumentException("File contains incorrect count of lines");
        }

        int countOfPhotos = Integer.parseInt(strings.get(0));
        List<Photo> photos = new ArrayList<>(countOfPhotos);
        for (int i = 1; i < strings.size(); i++) {
            photos.add(parsePhoto(strings.get(i), i - 1));
        }
        return photos;
    }

    private Photo parsePhoto(String line, int index) {
        List<String> words = Arrays.asList(line.split(" "));
        if (words.size() < 3) {
            throw new IllegalArgumentException("Line: '" + line + "' has incorrect format.");//do not have time to create appropriate exception class
        }
        String orientationSign = words.get(0);
        int tagCount = Integer.parseInt(words.get(1));
        if (words.size() - 2 != tagCount) {
            throw new IllegalArgumentException("Line: '" + line + "' has incorrect count of tags.");
        }
        List<String> tags = words.subList(2, words.size());
        return createPhoto(orientationSign, tags, index);
    }

    private Photo createPhoto(String orientationSign, List<String> tags, int index) {
        Photo.PhotoBuilder photoBuilder = Photo.builder();
        photoBuilder.tags(new HashSet<>(tags));
        photoBuilder.index(index);
        switch (orientationSign) {
        case "H":
            photoBuilder.horizontal(true);
            break;
        case "V":
            photoBuilder.horizontal(false);
            break;
        default:
            throw new IllegalArgumentException(orientationSign + " is incorrect orientation sign.");
        }
        return photoBuilder.build();
    }

}
