package com.bob;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BobsAutoRegisterTest {

    private static final Product[] TEST_ORDER_1 = {
            Product.CAR_WASH_WITH_WAX
    };

    private static final Product[] TEST_ORDER_2 = {
            Product.CAR_WASH_WITH_WAX,
            Product.VALET
    };

    private static final Product[] TEST_ORDER_3 = {
            Product.CAR_WASH_NORMAL,
            Product.SERVICING
    };

    private static final Product[] TEST_ORDER_4 = {
            Product.SERVICING,
            Product.VALET,
            Product.VALET
    };

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testParseOrder() {
        List<Product> expectedProducts = Arrays.asList(TEST_ORDER_1);
        List<Product> products = BobsAutoRegister.parseOrder(new String[]{"CAR_WASH_WITH_WAX"});
        assertEquals(expectedProducts, products);

        expectedProducts = Arrays.asList(TEST_ORDER_2);
        products = BobsAutoRegister.parseOrder(new String[]{"CAR_WASH_WITH_WAX", "VALET"});
        assertEquals(expectedProducts, products);

        expectedProducts = Arrays.asList(TEST_ORDER_3);
        products = BobsAutoRegister.parseOrder(new String[]{"CAR_WASH_NORMAL", "SERVICING"});
        assertEquals(expectedProducts, products);

        expectedProducts = Arrays.asList(TEST_ORDER_4);
        products = BobsAutoRegister.parseOrder(new String[]{"SERVICING", "VALET", "VALET"});
        assertEquals(expectedProducts, products);
    }

    @Test
    public void testGetTotalForOrder() {
        List<Product> order = new ArrayList<>();
        assertEquals(0.0, BobsAutoRegister.getTotalForOrder(order), 0);

        order.add(Product.CAR_WASH_WITH_WAX);
        assertEquals(18.0, BobsAutoRegister.getTotalForOrder(order), 0);

        order.add(Product.SERVICING);
        order.add(Product.VALET);
        assertEquals(143.0, BobsAutoRegister.getTotalForOrder(order), 0);

        order.add(Product.SERVICING);
        assertEquals(243.0, BobsAutoRegister.getTotalForOrder(order), 0);

        assertEquals(188.0, BobsAutoRegister.getTotalForOrder(Arrays.asList(Product.values())), 0);
    }

    @Test
    public void testGetReceiptForOrder() {
        String expectedReceipt =
                "1 CAR_WASH_WITH_WAX @ £18.00 : £18.00\n" +
                        "GRAND TOTAL : £18.00\n";

        List<Product> order = Arrays.asList(TEST_ORDER_1);
        double orderTotal = BobsAutoRegister.getTotalForOrder(order);
        Map<Product, Integer> productCount = BobsAutoRegister.getProductCount(order);
        String receiptForOrder = BobsAutoRegister.getReceiptForOrder(productCount, orderTotal);
        assertEquals(expectedReceipt, receiptForOrder);

        expectedReceipt =
                "1 SERVICING @ £100.00 : £100.00\n" +
                        "2 VALET @ £25.00 : £50.00\n" +
                        "GRAND TOTAL : £150.00\n";

        order = Arrays.asList(TEST_ORDER_4);
        orderTotal = BobsAutoRegister.getTotalForOrder(order);
        productCount = BobsAutoRegister.getProductCount(order);
        receiptForOrder = BobsAutoRegister.getReceiptForOrder(productCount, orderTotal);
        assertEquals(expectedReceipt, receiptForOrder);
    }
}