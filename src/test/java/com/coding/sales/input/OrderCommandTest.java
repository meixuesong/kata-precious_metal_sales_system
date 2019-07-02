package com.coding.sales.input;

import com.coding.sales.FileUtils;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderCommandTest {
    @Test
    public void should_parse_command() {
        String absolutePath = getResourceFilePath("sample_command.json");
        String commandString = FileUtils.readFromFile(absolutePath);

        OrderCommand command = OrderCommand.from(commandString);

        assertEquals("6236609999", command.getMemberId());
        assertEquals("0000001", command.getOrderId());
        assertEquals("2019-07-02 15:00:00", command.getCreateTime());

        assertEquals(4, command.getItems().size());
        assertEquals("001002", command.getItems().get(1).product);
        assertEquals(3, command.getItems().get(1).amount.intValue());


        assertEquals(1, command.getPayments().size());
        assertEquals("余额支付", command.getPayments().get(0).type);
        assertTrue(command.getPayments().get(0).amount.compareTo(BigDecimal.valueOf(9860.00)) == 0);

        assertEquals(1, command.getDiscounts().size());
        assertEquals("9折券", command.getDiscounts().get(0));

    }

    private String getResourceFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}