package com.jsonknights.gdg2019;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InOutManager {

    private static final Path inFileName = Paths.get("data.in");
    private static final Path outFileName = Paths.get("data.out");


    public void submitResult(int count, int[][] slices) {
        Stream.concat(Stream.of((toLine(count))))
        Files.write(outFileName, )
    }

    private String toLine(int... count) {
        return Stream.of(count).collect(Collectors.joining(" "));
    }
}
