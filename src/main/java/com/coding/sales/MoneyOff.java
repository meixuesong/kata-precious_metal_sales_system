package com.coding.sales;

import java.math.BigDecimal;

public enum MoneyOff implements Promotion {
    OFF_350_PER_3000 {
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                int countOf3000 = item.getSubTotal().divide(new BigDecimal("3000"), BigDecimal.ROUND_FLOOR).intValue();
                BigDecimal money = new BigDecimal("350").multiply(new BigDecimal(countOf3000));

                item.setDiscount(money);
            }
        }
    },
    OFF_30_PER_2000 {
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                int countOf2000 = item.getSubTotal().divide(new BigDecimal("2000"), BigDecimal.ROUND_FLOOR).intValue();
                BigDecimal money = new BigDecimal("30").multiply(new BigDecimal(countOf2000));

                item.setDiscount(money);
            }
        }
    },
    OFF_10_PER_1000 {
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                int countOf1000 = item.getSubTotal().divide(new BigDecimal("1000"), BigDecimal.ROUND_FLOOR).intValue();
                BigDecimal money = new BigDecimal("10").multiply(new BigDecimal(countOf1000));

                item.setDiscount(money);
            }
        }
    },
    BUY_3_HALF_1 {
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                if (item.getAmount().compareTo(new BigDecimal("3")) >= 0) {
                    item.setDiscount(item.getProduct().getPrice().divide(new BigDecimal("2"), BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
    },
    BUY_4_FREE_1{
        @Override
        public void calcPromotion(OrderItem item) {
            if (item.getProduct().getMoneyOffs().contains(this)) {
                if (item.getAmount().compareTo(new BigDecimal("4")) >= 0) {
                    item.setDiscount(item.getProduct().getPrice());
                }
            }
        }
    };
}
