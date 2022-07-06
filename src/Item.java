/**
 * Object thats used to store item information.
 * Uses "Inventory" as a base class.
 */
public class Item extends Inventory {
    /**
     * Stores this instance's code.
     */
    private int itemCode;
    /**
     * Stores this instance's name.
     */
    private String itemName;
    /**
     * Stores this instance's unit price.
     */
    private float unitPrice;
    /**
     * Stores the amount of this item when added.
     */
    private int quanitity;
    /**
     * Attribute for if the item existed in the inventory.
     */
    private boolean exists;

    /**
     * Constant for default item code.
     */
    private final int DEFAULT_CODE = 1;

    /**
     * Creates a basic item
     */
    public Item() {
        super();
        quanitity = 1;
        itemCode = DEFAULT_CODE;
        itemName = super.getNameFromCode(itemCode);
        unitPrice = super.getPriceFromCode(itemCode);
        exists = true;
    }

    /**
     * Creates an item with a given item code if it exists.
     * 
     * @param itemCode Code of the item.
     */
    public Item(String itemCode) {
        super();

        // Check existence
        if (super.itemExists(itemCode)) {
            quanitity = 1;
            this.itemCode = Integer.parseInt(itemCode);
            itemName = super.getNameFromCode(this.itemCode);
            unitPrice = super.getPriceFromCode(this.itemCode);
            exists = true;
        } else {
            exists = false;
        }
    }

    /**
     * Creates a number of items with a given item code if it exists.
     * 
     * @param itemCode  Code of the item.
     * @param quanitity Number of items.
     */
    public Item(String itemCode, int quanitity) {
        super();

        // Check existence
        if (super.itemExists(itemCode)) {
            this.quanitity = quanitity;
            this.itemCode = Integer.parseInt(itemCode);
            this.quanitity = quanitity;
            itemName = super.getNameFromCode(this.itemCode);
            unitPrice = super.getPriceFromCode(this.itemCode);
            exists = true;

        } else {
            exists = false;
        }
    }

    /**
     * Adds two item quanitites together.
     * 
     * @param item Item to be added.
     */
    public void add(Item item) {
        // Make sure item exists first
        if (exists) {
            quanitity += item.getQuantity();
        } else {
            throw new RuntimeException("This item does not exist!");
        }
    }

    /**
     * Calculates the price of the item with its quanitity.
     * 
     * @return Price of items as a float.
     */
    public float getFinalPrice() {
        // Make sure item exists first
        if (exists) {
            return quanitity * unitPrice;
        } else {
            throw new RuntimeException("This item does not exist!");
        }
    }

    /**
     * Gets the code of the item.
     * 
     * @return Item code as integer.
     */
    public int getItemCode() {
        // Make sure item exists first
        if (exists) {
            return itemCode;
        } else {
            throw new RuntimeException("This item does not exist!");
        }
    }

    /**
     * Gets the name of the item.
     * 
     * @return Item name as string.
     */
    public String getItemName() {
        // Make sure item exists first
        if (exists) {
            return itemName;
        } else {
            throw new RuntimeException("This item does not exist!");
        }
    }

    /**
     * Gets the quanitity of this item.
     * 
     * @return Item quantity as integer.
     */
    public int getQuantity() {
        // Make sure item exists first
        if (exists) {
            return quanitity;
        } else {
            throw new RuntimeException("This item does not exist!");
        }
    }

    /**
     * Gets the existence of this item.
     * 
     * @return Boolean
     */
    public boolean exists() {
        return exists;
    }

    /**
     * To string method that displays a formatted name and quantity.
     */
    @Override
    public String toString() {
        // Make sure item exists first
        if (exists) {
            return String.format("%d %s", quanitity, itemName);
        } else {
            throw new RuntimeException("This item does not exist!");
        }
    }

}
