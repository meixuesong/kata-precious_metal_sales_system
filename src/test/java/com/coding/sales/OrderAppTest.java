package com.coding.sales;

import com.coding.sales.representation.OrderRepresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

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
        String orderCommand = FileReader.readFromFile(getResourceFilePath(commandFileName));
        OrderApp app = new OrderApp();
        OrderRepresentation actualRepresentation = app.checkout(orderCommand);

        String expectedRepresentation = FileReader.readFromFile(getResourceFilePath(expectedResultFileName));

        replaceOrderIdandCreateTime(actualRepresentation, expectedRepresentation);
        assertEquals(expectedRepresentation, actualRepresentation.toString());
    }

    private void replaceOrderIdandCreateTime(OrderRepresentation actualRepresentation, String expectedRepresentation) {
        Date orderDate = findOrderDate(expectedRepresentation);
        actualRepresentation.setCreateTime(orderDate);
        actualRepresentation.setOrderId("0000001");
    }

    private Date findOrderDate(String expectedRepresentation) {
        String[] lines = expectedRepresentation.split("\n");
        String dateLine = lines[2];

        String datePrefix = "日期：";
        int i = dateLine.indexOf(datePrefix);
        if (i <= 0) {
            throw new RuntimeException("未找到期望的订单创建时间");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateLine.substring(i + datePrefix.length());
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("未找到期望的订单创建时间");
        }
    }

    private String getResourceFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }

}
