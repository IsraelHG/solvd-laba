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
            text = FileUtils.readFileToString(new File("src\\main\\resources\\input.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String updatedText = text.replaceAll("[^\\w\\s]", "").toLowerCase();

        // Split text into words using StringUtils
        List<String> list = List.of(StringUtils.split(updatedText));

        // Count unique words using a HashSet
        HashSet<String> hashMap = new HashSet<>(list);

        // Output file
        File outputFile = new File("src\\main\\resources\\output.txt");

        // Write count to output file using FileUtils
        try {
            FileUtils.writeStringToFile(outputFile, String.valueOf(hashMap.size()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
