package com.coding.sales;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {
    private Map<String, Member> members = new HashMap<>();

    public MemberRepository() {
        members.put("6236609999", new Member("6236609999", "马丁","普卡", 9860));
        members.put("6630009999", new Member("6630009999", "王立","金卡", 48860));
        members.put("8230009999", new Member("8230009999", "李想","白金卡", 98860));
        members.put("9230009999", new Member("9230009999", "张三","钻石卡", 198860));
    }

    public Member findById(String id) {
        return members.get(id);
    }
}
