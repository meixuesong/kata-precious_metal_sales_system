package com.coding.sales;

import org.assertj.core.data.Offset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class MoneyOffTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {MoneyOff.OFF_350_PER_3000, 300, 350},
                {MoneyOff.OFF_30_PER_2000, 200, 30},
                {MoneyOff.OFF_30_PER_2000, 400, 60},
                {MoneyOff.OFF_10_PER_1000, 100, 10},
                {MoneyOff.BUY_3_HALF_1, 3, 5},
                {MoneyOff.BUY_4_FREE_1, 4, 10},
        };

        return Arrays.asList(data);
    }

    private MoneyOff moneyOff;
    private int amount;
    private int expectedMoneyOff;

    public MoneyOffTest(MoneyOff moneyOff, int amount, int expectedMoneyOff) {
        this.moneyOff = moneyOff;
        this.amount = amount;
        this.expectedMoneyOff = expectedMoneyOff;
    }

    @Test
    public void should_calc_money_off() {
        Product prod = new Product("001", "PROD", new BigDecimal("10.00"));
        prod.addMoneyOff(moneyOff);
        OrderItem item = new OrderItem(prod, new BigDecimal(amount));

        this.moneyOff.calcPromotion(item);

        assertThat(item.getDiscount()).isCloseTo(new BigDecimal(expectedMoneyOff), Offset.offset(new BigDecimal("0.001")));
    }
}