package com.coding.sales.product;

import com.coding.sales.order.Discount;
import com.coding.sales.order.MoneyOff;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    static Map<String, Product> products = new HashMap<>();

    public Product findById(String id) {
        return products.get(id);
    }

    public ProductRepository() {
        products.put("001001", new Product("001001", "世园会五十国钱币册", "998.00"));

        Product product2 = new Product("001002", "2019北京世园会纪念银章大全40g", "1380.00");
        product2.addDiscount(Discount.PERCENT_90);
        products.put("001002", product2); //可使用9折打折

        Product product3 = new Product("003001", "招财进宝", "1580.00");
        product3.addDiscount(Discount.PERCENT_95);
        products.put("003001", product3); //可使用95折打折

        Product product4 = new Product("003002", "水晶之恋", "980.00");
        product4.addMoneyOff(MoneyOff.BUY_3_FREE_0_5);
        product4.addMoneyOff(MoneyOff.BUY_4_FREE_1);
        products.put("003002", product4);

        Product product5 = new Product("002002", "中国经典钱币套装", "998.00");
        product5.addMoneyOff(MoneyOff.OFF_30_PER_2000);
        product5.addMoneyOff(MoneyOff.OFF_10_PER_1000);
        products.put("002002", product5);// 参与满减：每满2000减30，每满1000减10

        Product product6 = new Product("002001", "守扩之羽比翼双飞4.8g", "1080.00");
        product6.addMoneyOff(MoneyOff.BUY_3_FREE_0_5);
        product6.addMoneyOff(MoneyOff.BUY_4_FREE_1);
        product6.addDiscount(Discount.PERCENT_95);
        products.put("002001", product6); //参与满减：第3件半价，满3送1 可使用95折打折

        Product product7 = new Product("002003", "中国银象棋12g", "698.00");
        product7.addDiscount(Discount.PERCENT_90);
        product7.addMoneyOff(MoneyOff.OFF_350_PER_3000);
        product7.addMoneyOff(MoneyOff.OFF_30_PER_2000);
        product7.addMoneyOff(MoneyOff.OFF_10_PER_1000);
        products.put("002003", product7);
    }
}
