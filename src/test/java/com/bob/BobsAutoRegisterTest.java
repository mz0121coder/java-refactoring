package com.bob;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BobsAutoRegisterTest {

	private static final Product[] TEST_ORDER_1 = {
		Product.CAR_WASH_WITH_WAX };

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

		expectedProducts = Arrays.asList(TEST_ORDER_3);
		products = BobsAutoRegister.parseOrder(new String[]{"CAR_WASH_NORMAL", "SERVICING"});

		expectedProducts = Arrays.asList(TEST_ORDER_4);
		products = BobsAutoRegister.parseOrder(new String[]{"SERVICING", "VALET", "VALET"});

		assertEquals(expectedProducts, products);
	}

	@Test
	public void testGetTotalForOrder() {
		List<Product> order = new ArrayList<Product>();
		assertEquals(0.0, BobsAutoRegister.getTotalForOrder(order), 0);

		order.add(Product.CAR_WASH_WITH_WAX);
		assertEquals(18.0, BobsAutoRegister.getTotalForOrder(order), 0);

		order.add(Product.SERVICING);
		order.add(Product.VALET);
		assertEquals(143.0, BobsAutoRegister.getTotalForOrder(order), 0);

		order.add(Product.SERVICING);
		assertEquals(243.0, BobsAutoRegister.getTotalForOrder(order), 0);

		Arrays.asList(Product.values());
		assertEquals(188.0, BobsAutoRegister.getTotalForOrder(Arrays.asList(Product.values())), 0);
	}

	@Test
	public void testGetReceiptForOrder() {
		String expectedReceipt =
			"1 CAR_WASH_WITH_WAX : £18.00\n" +
			"GRAND TOTAL : £18.00\n";

		List<Product> order = Arrays.asList(TEST_ORDER_1);
		String receiptForOrder = BobsAutoRegister.getReceiptForOrder(order, BobsAutoRegister.getTotalForOrder(order));
		assertEquals(expectedReceipt, receiptForOrder);


		expectedReceipt = "1 SERVICING : £100.00\n" +
						   "1 VALET : £25.00\n" +
						   "1 VALET : £25.00\n" +
						   "GRAND TOTAL : £150.00\n";
		order = Arrays.asList(TEST_ORDER_4);
		receiptForOrder = BobsAutoRegister.getReceiptForOrder(order, BobsAutoRegister.getTotalForOrder(order));
		assertEquals(expectedReceipt, receiptForOrder);
	}

}
