package com.bob;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BobsAutoRegister {
    public static void main(String[] args) {
        List<Product> order = parseOrder(args);
        Map<Product, Integer> productCount = getProductCount(order);
        double orderTotal = getTotalForOrder(order);
        String orderReceipt = getReceiptForOrder(productCount, orderTotal);
        System.out.println(orderReceipt);
    }

    static protected List<Product> parseOrder(String[] args) {
        List<Product> order = new ArrayList<>();
        for (String arg : args) {
            order.add(Product.valueOf(arg));
        }
        return order;
    }

    static protected Map<Product, Integer> getProductCount(List<Product> order) {
        Map<Product, Integer> productCount = new HashMap<>();
        for (Product product : order) {
            productCount.put(product, productCount.getOrDefault(product, 0) + 1);
        }
        return productCount;
    }

    static protected double getTotalForOrder(List<Product> order) {
        double totalPrice = 0.0;
        for (Product item : order) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

		static protected String getReceiptForOrder(Map<Product, Integer> productCount, double orderTotal) {
			StringBuilder receipt = new StringBuilder();
			List<Map.Entry<Product, Integer>> sortedProductCount = new ArrayList<>(productCount.entrySet());
			sortedProductCount.sort(Comparator.comparing(Map.Entry::getKey));
	
			for (Map.Entry<Product, Integer> entry : sortedProductCount) {
					Product product = entry.getKey();
					int count = entry.getValue();
					double productTotal = product.getPrice() * count;
					receipt.append(String.format("%d %s @ £%.2f : £%.2f\n", count, product.name(), product.getPrice(), productTotal));
			}
			receipt.append(String.format("GRAND TOTAL : £%.2f\n", orderTotal));
			return receipt.toString();
	}
}
