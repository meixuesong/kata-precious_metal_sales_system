package com.coding.sales;

import com.coding.sales.member.MemberType;
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
                {9999, MemberType.NORMAL},
                {10000, MemberType.GOLD},
                {49999, MemberType.GOLD},
                {50000, MemberType.PLATINUM},
                {99999, MemberType.PLATINUM},
                {100000, MemberType.DIAMOND},
                {900000, MemberType.DIAMOND}
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