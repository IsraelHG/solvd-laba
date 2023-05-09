package com.solvd.laba.lab3;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class Main {
    public static void main(String[] args) throws IOException {
        String text;

        // Read text from input file using FileUtils
        try {
            text = FileUtils.readFileToString(new File("src\\main\\java\\com\\solvd\\laba\\lab3\\input.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Split text into words using StringUtils
        List<String> words = List.of(StringUtils.split(text));

        // Count unique words using a HashSet
        HashSet<String> uniqueWords = new HashSet<>(words);

        // Output file
        File outputFile = new File("src\\main\\java\\com\\solvd\\laba\\lab3\\output.txt");

        // Write count to output file using FileUtils
        try {
            FileUtils.writeStringToFile(outputFile, "Number of unique words: " + uniqueWords.size(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
