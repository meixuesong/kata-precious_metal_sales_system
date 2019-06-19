package com.coding.sales.order;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class OrderAppTest {
    @Test
    public void should_checkout_order() {
        String inputFileName = "sample_command.json";
        String command = readFromFile(inputFileName);

        OrderApp app = new OrderApp();
        String actualResult = app.checkout(command);

        String expectedResult = readFromFile("sample_result.txt");
        assertEquals(expectedResult, actualResult);
    }

    private String readFromFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            Path srtFile = Paths.get(file.getAbsolutePath());
            List<String> lines = Files.readAllLines(srtFile, StandardCharsets.UTF_8);

            return String.join("\n", lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}