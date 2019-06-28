package com.coding.sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderAppTest {

    public static final String ORDER_DATE = "日期：";
    public static final String ORDER_ID = "销售单号：";

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
        String orderCommand = FileReader.readFromFile(getResourceFilePath(commandFileName));
        OrderApp app = new OrderApp();
        String actualResult = app.checkout(orderCommand);

        String expectedResult = FileReader.readFromFile(getResourceFilePath(expectedResultFileName));

        actualResult = replaceOrderIDandCreateTime(expectedResult, actualResult);
        assertEquals(expectedResult, actualResult);
    }

    private String replaceOrderIDandCreateTime(String source, String target) {
        String sourceDate = findOrderDate(source);
        String targetDate = findOrderDate(target);
        String sourceOrderId = findOrderId(source);
        String targetOrderId = findOrderId(target);

        String result = target.replace(ORDER_DATE + targetDate, ORDER_DATE + sourceDate);
        result = result.replace(ORDER_ID + targetOrderId, ORDER_ID + sourceOrderId);

        return result;
    }

    private String findOrderId(String representation) {
        String line = getOrderIdAndDateLine(representation);

        String prefix = ORDER_ID;
        int i = line.indexOf(prefix);
        if (i < 0) {
            throw new RuntimeException("未找到销售单号");
        }

        String suffix = " " + ORDER_DATE;
        int j = line.indexOf(suffix);
        if (j < 0) {
            throw new RuntimeException("未找到销售单号");
        }

        String orderId = line.substring(i + prefix.length(), j);
        return orderId;
    }

    private String findOrderDate(String representation) {
        String line = getOrderIdAndDateLine(representation);

        String datePrefix = ORDER_DATE;
        int i = line.indexOf(datePrefix);
        if (i < 0) {
            throw new RuntimeException("未找到订单创建时间");
        }

        String date = line.substring(i + datePrefix.length());
        return date;
    }

    private String getOrderIdAndDateLine(String representation) {
        String[] lines = representation.split("\n");
        return lines[2];
    }

    private String getResourceFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }

}
