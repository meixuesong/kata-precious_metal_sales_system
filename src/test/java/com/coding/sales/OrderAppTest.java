package com.coding.sales;

import com.coding.sales.representation.OrderRepresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

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
        OrderRepresentation actualRepresentation = app.checkout(readFromFile(commandFileName));

        String expectedRepresentation = readFromFile(expectedResultFileName);
        assertEquals(expectedRepresentation, actualRepresentation.toString());
    }

    private String readFromFile(String filePath) {
        String result = "";
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            is = new FileInputStream(filePath);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String s;
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null)
                sb.append(s + "");

            result = sb.toString();

            br.close();
        } catch(IOException e) {
            throw new RuntimeException("Failed to read file: " + filePath);
        } finally {
            try {
                // releases any system resources associated
                if (is != null)
                    is.close();
                if (isr != null)
                    isr.close();
                if (br != null)
                    br.close();
            } catch (Exception e) {
            }
        }

        return result;
    }
}
