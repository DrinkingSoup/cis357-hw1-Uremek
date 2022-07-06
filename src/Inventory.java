import java.util.Scanner;
import java.io.File;

/**
 * Base class for storing and referencing grocery store item data.
 * 
 * @author Adam Uremeks
 */
public class Inventory {
    /**
     * Integer array of item codes from.
     */
    private static int[] itemCodes;
    /**
     * String array of item names.
     */
    private static String[] itemNames;
    /**
     * Float array of unit prices.
     */
    private static float[] unitPrices;
    /**
     * Boolean value that is used to check if the inventory has been initialized.
     * In other words, this is used to make sure the static arrays are not
     * repopulated
     * for every construction of this class.
     */
    private static boolean _isInitialzied;

    /**
     * Constant for storing relative inventory CSV path
     */
    private final String INVENTORY_PATH = "./src/inventory.csv";

    /**
     * Default constructor for the Inventory class that reads and stores all items
     * from a "database"
     * for use with other calsses.
     */
    public Inventory() {
        if (!_isInitialzied) {
            try {
                // Create scanner and find number of lines in the inventory CSV table.
                // Start indexing at -1 to ignore the table
                Scanner scanObj = new Scanner(new File(INVENTORY_PATH));
                int lineCount = -1;

                while (scanObj.hasNext()) {
                    lineCount++;
                    scanObj.nextLine();
                }
                // Close scanner for variable to be resued
                scanObj.close();

                // Create new arrays to store data in using the discovered linecount
                itemCodes = new int[lineCount];
                itemNames = new String[lineCount];
                unitPrices = new float[lineCount];

                // Create a new scanner for the same inventory but read paths this time
                scanObj = new Scanner(new File(INVENTORY_PATH));

                // Skip first line of CSV, and repurpose lne count variable
                // as an indexer starting at 0 for storing data
                scanObj.nextLine();
                lineCount = 0;

                while (scanObj.hasNext()) {
                    // Split line wiht comma delimiter
                    String[] line = scanObj.nextLine().split(",");
                    // Add items to arrays
                    itemCodes[lineCount] = Integer.parseInt(line[0]);
                    itemNames[lineCount] = line[1];
                    unitPrices[lineCount] = Float.parseFloat(line[2]);
                    // Increment indexer
                    lineCount++;
                }

                // Close scanner and set initializer variable to true
                scanObj.close();
                _isInitialzied = true;
            } catch (Exception e) {
                // Print error if any
                System.out.println(e);
            }
        }
    }

    /**
     * Gets an array index from an item code.
     * 
     * @param code Code of the item.
     * @return Index of code if found, -1 if not found.
     */
    private static int getIndexFromCode(int code) {
        for (int idx = 0; idx < itemCodes.length; idx++) {
            if (itemCodes[idx] == code)
                return idx;
        }
        return -1;
    }

    /**
     * Checks for the existence of an item code in the inventory.
     * 
     * @param itemCode Code of the item.
     * @return Boolean
     */
    public static boolean itemExists(String itemCode) {
        // Since item codes are currently strored as integers,
        // if the code cant convert to an integer, then it is invalid.
        try {
            int code = Integer.parseInt(itemCode);
            if (getIndexFromCode(code) == -1) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the name of an item from its code.
     * 
     * @param code Code of the item.
     * @return Item name.
     */
    public static String getNameFromCode(int code) {
        return itemNames[getIndexFromCode(code)];
    }

    /**
     * Gets teh price of an item from its code.
     * 
     * @param code Code of the item.
     * @return Item unit price.
     */
    public static float getPriceFromCode(int code) {
        return unitPrices[getIndexFromCode(code)];
    }
}
