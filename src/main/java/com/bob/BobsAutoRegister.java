package com.bob;

import java.util.ArrayList;
import java.util.List;

public class BobsAutoRegister {

	public static void main(String[] args) {


		List<Product> order = parseOrder(args);

		double orderTotal = getTotalForOrder(order);

		String orderReceipt = getReceiptForOrder(order, orderTotal);

		System.out.println(orderReceipt);
	}


	static protected List<Product> parseOrder(String[] args) {
		List<Product> order = new ArrayList<Product>();
		for (int i = 0; i < args.length; i++) {
			order.add(Product.valueOf(args[i]));
		}
		return order;
	}

	static protected double getTotalForOrder(List<Product> order) {
		double totalPrice = 0.0;

		for (Product item : order) {
			totalPrice += item.getPrice();
		}

		return totalPrice;
	}

	static protected String getReceiptForOrder(List<Product> order,
			double orderTotal) {
		StringBuilder receipt = new StringBuilder();

		for (Product product : order) {
			receipt.append(String.format("1 %s : £%.2f\n", product.name(), product.getPrice()));
		}

		receipt.append(String.format("GRAND TOTAL : £%.2f\n", orderTotal));

		return receipt.toString();
	}
}
