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
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        Object[][] data = new Object[][]{
                //0 冒烟测试
                {"sample_command.json", "sample_result.txt"},
                //1 普通商品，没有优惠，验证金额
                {"normal_products_command.json", "normal_products_result.txt"},

                //2 验证积分计算和会员升级，注意要分别算分。普卡
                {"member_normal_command.json", "member_normal_result.txt"},
                //3 验证积分计算和会员升级，注意要分别算分。金卡
                {"member_gold_command.json", "member_gold_result.txt"},
                //4 验证积分计算和会员升级，注意要分别算分。白金卡
                {"member_PLATINUM_command.json", "member_PLATINUM_result.txt"},
                //5 验证积分计算和会员升级，注意要分别算分。钻石卡
                {"member_DIAMOND_command.json", "member_DIAMOND_result.txt"},

                //6 95折的优惠金额、应收金额是否正确。不验证会员积分等信息
                {"discount_95_command.json", "discount_95_result.txt"},
                //7 90折的优惠金额、应收金额是否正确。不验证会员积分等信息
                {"discount_90_command.json", "discount_90_result.txt"},

                //8 满减促销，验证优惠金额、应收金额是否正确。不验证会员、积分等信息
                {"promotion_command.json", "promotion_result.txt"},
                //9 选择更优惠的打折或满减
                {"promotion_discount_command.json", "promotion_discount_result.txt"},

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
        String orderCommand = FileUtils.readFromFile(getResourceFilePath(commandFileName));
        OrderApp app = new OrderApp();
        String actualResult = app.checkout(orderCommand);

        String expectedResult = FileUtils.readFromFile(getResourceFilePath(expectedResultFileName));

        assertEquals(expectedResult, actualResult);
    }

    private String getResourceFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }

}
