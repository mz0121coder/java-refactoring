# Java Refactoring Task

## Summary of Changes

### File 1 - BobsAutoRegister.java:

1. Added import statements for necessary Java classes.
2. Modified the parseOrder method to use an enhanced for loop for better readability.
3. Added a new method getProductCount to calculate the count of each product in the order using a HashMap.
4. Modified the getReceiptForOrder method to accept a Map of product counts and refactored the logic to display a single line for each type of product purchased.
5. Implemented sorting of the product counts by product name in the getReceiptForOrder method.
6. Updated the main method to use the new getProductCount method and passed the product count map to the getReceiptForOrder method.
7. Removed unnecessary type specification in the order list declaration.

### File 2 - BobsAutoRegisterTest.java:

1. Added import statements for necessary Java classes.
2. Modified the testParseOrder method to include assertions for all test cases.
3. Updated the testGetReceiptForOrder method to calculate the order total and obtain the product count map using the new getProductCount method.

---

These changes were made to meet the requirements mentioned in the guidelines:

1. Refactored the code to be more maintainable, testable, and object-oriented.
2. Implemented a well-factored object-oriented domain model.
3. Added appropriate testing using JUnit.
4. Refactored the code for better readability and adhering to clean code principles.
5. Modified the receipt generation logic to print a single line for each type of product purchased, displaying the number of items, the type of product, and the total cost of the items.

These changes improve the structure, readability, and maintainability of the code while fulfilling the requirements specified in the guidelines.

---
