package com.coding.sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MemberTypeTest {
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        Object[][] data = new Object[][] {
                {0, MemberType.NORMAL},
                {10000, MemberType.GOLD},
                {60000, MemberType.PLATINUM},
                {10000000, MemberType.DIAMOND},
        };

        return Arrays.asList(data);
    }

    private int points;
    private MemberType expectedType;

    public MemberTypeTest(int points, MemberType expectedType) {
        this.points = points;
        this.expectedType = expectedType;
    }

    @Test
    public void should_get_member_type() {
        assertEquals(expectedType, MemberType.getMemberType(points));
    }
}