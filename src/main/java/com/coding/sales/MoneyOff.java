package com.coding.sales;

import java.math.BigDecimal;

public enum MoneyOff implements Promotion {
    OFF_350_PER_3000;

    @Override
    public void calcPromotion(OrderItem item) {
        if (item.getProduct().getMoneyOffs().contains(MoneyOff.OFF_350_PER_3000)) {
            int countOf3000 = item.getSubTotal().divide(new BigDecimal("3000"), BigDecimal.ROUND_FLOOR).intValue();
            BigDecimal money = new BigDecimal("350").multiply(new BigDecimal(countOf3000));

            item.setDiscount(money);
        }
    }
}
