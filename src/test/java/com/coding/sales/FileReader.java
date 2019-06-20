package com.coding.sales;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {
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
