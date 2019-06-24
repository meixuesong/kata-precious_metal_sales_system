package com.coding.sales.command;

import com.coding.sales.FileReader;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class OrderCommandTest {
    @Test
    public void should_parse_command() {
        String absolutePath = getResourceFilePath("sample_command.json");
        String commandString = FileReader.readFromFile(absolutePath);

        OrderCommand command = OrderCommand.from(commandString);

        assertEquals("6236609999", command.getMemberId());

        assertEquals(4, command.getItems().size());
        assertEquals("001002", command.getItems().get(1).product);
        assertEquals(3, command.getItems().get(1).amount.intValue());


        assertEquals(1, command.getPayments().size());
        assertEquals("账户余额", command.getPayments().get(0).type);
        assertEquals(11612, command.getPayments().get(0).amount.intValue());

        assertEquals(1, command.getDiscounts().size());
        assertEquals("9折券", command.getDiscounts().get(0));

    }

    private String getResourceFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}