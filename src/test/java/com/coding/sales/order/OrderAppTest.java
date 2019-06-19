package com.coding.sales.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderAppTest {

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        Object[][] data = new Object[][]{
                {"sample_command.json", "sample_result.txt"},
        };

        return Arrays.asList(data);
    }

    private String commandFileName;
    private String expectedResultFileName;

    public OrderAppTest(String commandFileName, String expectedResultFileName) {
        this.commandFileName = commandFileName;
        this.expectedResultFileName = expectedResultFileName;
    }

    @Test
    public void should_checkout_order() {
        OrderApp app = new OrderApp();
        String actualResult = app.checkout(readFromFile(commandFileName));

        assertEquals(readFromFile(expectedResultFileName), actualResult);
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