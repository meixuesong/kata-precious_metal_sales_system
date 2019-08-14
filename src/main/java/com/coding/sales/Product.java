package com.coding.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private List<Discount> discounts = new ArrayList<Discount>();
    private ArrayList<MoneyOff> moneyOffs = new ArrayList<>();

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void addDiscount(Discount percent90) {
        discounts.add(percent90);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void addMoneyOff(MoneyOff moneyOff) {
        moneyOffs.add(moneyOff);
    }

    public List<MoneyOff> getMoneyOffs() {
        return moneyOffs;
    }
}
