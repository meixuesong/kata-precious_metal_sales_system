package com.coding.sales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    public static String readFromFile(String filePath) {
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
                sb.append(s + "\n");

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

    public static void writeToFile(String content, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String[] lines = content.split("\n");
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
