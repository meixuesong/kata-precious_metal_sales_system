package com.coding.sales;

import java.math.BigDecimal;

public enum MoneyOff implements Promotion {
    OFF_350_PER_3000 {
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                calcMoneyOffByTotalPrice(item, "3000", "350");
            }
        }
    },
    OFF_30_PER_2000 {
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                calcMoneyOffByTotalPrice(item, "2000", "30");
            }
        }
    },
    OFF_10_PER_1000 {
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                calcMoneyOffByTotalPrice(item, "1000", "10");
            }
        }
    },
    BUY_3_HALF_1 {
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                calcMoneyOffByAmount(item, "3", "0.5");
            }
        }
    },
    BUY_4_FREE_1{
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                if (item.getAmount().compareTo(new BigDecimal("4")) >= 0) {
                    calcMoneyOffByAmount(item, "4", "1");
                }
            }
        }
    };

    private static void calcMoneyOffByAmount(OrderItem item, String productAmount, String offRate) {
        if (item.getAmount().compareTo(new BigDecimal(productAmount)) >= 0) {
            item.setDiscount(item.getProduct().getPrice().multiply(new BigDecimal(offRate)).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }

    private static void calcMoneyOffByTotalPrice(OrderItem item, String perMoney, String moneyOff) {
        int count = item.getSubTotal().divide(new BigDecimal(perMoney), BigDecimal.ROUND_FLOOR).intValue();
        BigDecimal money = new BigDecimal(moneyOff).multiply(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP);

        item.setDiscount(money);
    }
}
