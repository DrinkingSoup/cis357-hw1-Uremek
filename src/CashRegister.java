// Homework 1: Sales Register Program
// Course: CIS357
// Due date: July 5, 11:59 PM
// Name: Adam Uremek
// GitHub: https://github.com/DrinkingSoup/cis357-hw1-Uremek
// Instructor: Il-Hyung Cho
// Program description: Java program that emulates a cash register at a grocery store.

import java.util.Scanner;

/**
 * Cash register object that allows users to use a terminal POST system.
 */
public class CashRegister {
    /**
     * Stores the daily sales for this instance
     */
    public static float totalDailySales;
    /**
     * Tax constant.
     */
    final static float TAX = 0.06f;

    /**
     * Checks if a user wants to start a new sale or not.
     * 
     * @param userInput Input scanner
     * @return Boolean for whether or not the user wants to continue.
     */
    public static boolean isNewSale(Scanner userInput) {
        while (true) {
            // Get response in upper case
            System.out.printf("%s", "Beginning a new sale (Y/N)\t");
            String response = userInput.next().toUpperCase();

            // If user inputs Y, then continue, if N then break.
            if (response.toUpperCase().equals("Y")) {
                System.out.println("---------------------");
                return true;
            } else if (response.toUpperCase().equals("N")) {
                return false;
            } else {
                System.out.printf("%26s %n", "Invalid input.");
            }
        }
    }

    /**
     * Prompt for getting the quantity of an item.
     * 
     * @param userInput Input Scanner
     * @return Integer quantity.
     */
    public static int getQuantity(Scanner userInput) {
        while (true) {
            // Get the user's response
            System.out.printf("%19s ", "Enter Quantity:");
            String response = userInput.next();

            // If an interger can be parsed, then return it, otherwise keep trying!
            try {
                int quanitity = Integer.parseInt(response);

                if (quanitity != 0) {
                    return quanitity;
                } else {
                    System.out.printf("%19s", "!!! Invalid quantity.\n\n");
                }

            } catch (Exception e) {
                System.out.printf("%19s", "!!! Invalid quantity.\n\n");
            }
        }
    }

    /**
     * Main method for handling item inputs and checkouts.
     * 
     * @param userInput Input Scanner
     */
    public static void enterItems(Scanner userInput) {
        // Cart for this current sale
        Cart currentCart = new Cart();

        while (true) {
            // Get product code input
            System.out.printf("%19s", "Enter Product Code: ");
            String response = userInput.next();

            // If the input is -1, checkout
            if (!response.equals("-1")) {
                // Create a new item with the item code
                Item inputItem = new Item(response);

                // Make sure the item exists for that product code
                if (inputItem.exists()) {
                    // Temps
                    String itemCode = "";
                    int quanitity = 1;

                    // Get the item code and quantity inputs, then store them.
                    System.out.printf("%19s %s\n", "Item Name:", inputItem.getItemName());
                    itemCode = Integer.toString(inputItem.getItemCode());
                    quanitity = getQuantity(userInput);

                    // Make a new instance of the same item with the inputted quanitty
                    inputItem = new Item(itemCode, quanitity);
                    System.out.printf("%19s $%7.2f\n\n", "Item Total:", inputItem.getFinalPrice());

                    // Add item to cart
                    currentCart.addItem(inputItem);
                } else {
                    System.out.printf("%19s", "!!! Invalid product code.\n\n");
                }
            } else {
                // Temp
                float change = 0;

                // Start checkout
                System.out.println("----------------------------");
                System.out.print(currentCart.toString());
                System.out.printf("%-20s$%7.2f\n", "Subtotal", currentCart.cartSubTotal());
                System.out.printf("%-20s$%7.2f\n", "Total with Tax (6%)", currentCart.cartTotal(TAX));

                // Get a valid tendered amount
                while (true) {
                    // Get user input
                    System.out.printf("%-20s$%s ", "Tendered Amount", "  ");
                    String tenderInput = userInput.next();

                    // Make sure the tender is numeric
                    if (tenderInput.matches("[-+]?[0-9]*\\.?[0-9]+")) {
                        // Parse tender to a float
                        float tender = Float.parseFloat(tenderInput);

                        // Calculate change and add the total to daily sales.
                        if (tender >= currentCart.cartTotal(TAX)) {
                            totalDailySales += currentCart.cartTotal(TAX);
                            change = tender - currentCart.cartTotal(TAX);
                            break;
                        }
                    }
                }

                // Stop sale
                System.out.printf("%-20s$%7.2f\n", "Change", change);
                System.out.println("----------------------------\n");
                break;
            }

        }
    }

    /**
     * Starts the main cash register dialog
     */
    public static void startDialog() {
        // Create a new input scanner
        Scanner input = new Scanner(System.in);

        // Intro
        while (true) {
            System.out.println("Welcome to Uremek's cash register system!\n\n");

            // Start the item entering process per sale
            if (isNewSale(input)) {
                enterItems(input);
            } else {
                // Terminate the program
                System.out.printf("\n%s $%7.2f\n\n", "The total sale for the day is ", totalDailySales);
                System.out.println("Thanks for using POST system. Goodbye.");
                break;
            }
        }

    }

    /**
     * Main method entry point.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Initialze daily sales and cash register dialog.
        totalDailySales = 0f;
        startDialog();
    }
}