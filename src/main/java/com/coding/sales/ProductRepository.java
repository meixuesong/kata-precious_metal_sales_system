package com.coding.sales;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    static Map<String, Product> products = new HashMap<>();

    public Product findById(String id) {
        return products.get(id);
    }

    public ProductRepository() {
        products.put("001001", new Product("001001", "世园会五十国钱币册", "998.00"));
        Product product = new Product("001002", "2019北京世园会纪念银章大全40g", "1380.00");
        product.addDiscount(Discount.PERCENT_90);
        products.put("001002", product); //可使用9折打折

        Product product1 = new Product("003001", "招财进宝", "1580.00");
        products.put("003001", product1); //可使用95折打折
        product1.addDiscount(Discount.PERCENT_95);

        Product product2 = new Product("003002", "水晶之恋", "980.00");
        products.put("003002", product2);
        product2.addMoneyOff(MoneyOff.BUY_3_HALF_1);
        product2.addMoneyOff(MoneyOff.BUY_4_FREE_1);

        Product product3 = new Product("002002", "中国经典钱币套装", "998.00");
        products.put("002002", product3);// 参与满减：每满2000减30，每满1000减10
        product3.addMoneyOff(MoneyOff.OFF_30_PER_2000);
        product3.addMoneyOff(MoneyOff.OFF_10_PER_1000);

        Product product4 = new Product("002001", "守扩之羽比翼双飞4.8g", "1080.00");
        products.put("002001", product4); //参与满减：第3件半价，满3送1 可使用95折打折
        product4.addMoneyOff(MoneyOff.BUY_3_HALF_1);
        product4.addMoneyOff(MoneyOff.BUY_4_FREE_1);
        product4.addDiscount(Discount.PERCENT_95);

        Product product5 = new Product("002003", "中国银象棋12g", "698.00");
        products.put("002003", product5);
        //参与满减：每满3000元减350, 每满2000减30，每满1000减10 可使用9折打折券
        product5.addDiscount(Discount.PERCENT_90);
        product5.addMoneyOff(MoneyOff.OFF_350_PER_3000);
        product5.addMoneyOff(MoneyOff.OFF_30_PER_2000);
        product5.addMoneyOff(MoneyOff.OFF_10_PER_1000);
    }
}
