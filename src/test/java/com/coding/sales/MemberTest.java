package com.coding.sales;

import com.coding.sales.member.Member;
import com.coding.sales.member.MemberType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MemberTest {
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        Object[][] data = new Object[][]{
                {MemberType.NORMAL, "1000.00", 1000},
                {MemberType.GOLD, "1000.00", 1500},
                {MemberType.PLATINUM, "1000.00", 1800},
                {MemberType.DIAMOND, "1000.00", 2000},
        };

        return Arrays.asList(data);
    }

    private MemberType type;
    private BigDecimal money;
    private int expectedPoints;

    public MemberTest(MemberType type, String money, int expectedPoints) {
        this.type = type;
        this.money = new BigDecimal(money);
        this.expectedPoints = expectedPoints;
    }

    @Test
    public void should_calc_member_points() {
        Member member = new Member("11", "11", type);

        member.pay(money);

        assertEquals(expectedPoints, member.getPoints());
    }
}