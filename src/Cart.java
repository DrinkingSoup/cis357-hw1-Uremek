/**
 * This class is to store and operate on items for checkout.
 */
public class Cart {
    /**
     * An array used to store items in the cart
     */
    private Item[] items;
    /**
     * Last open index of the items array, used when expanding the items array to
     * fit more
     * stuff in it.
     */
    private int cartIdx;

    /**
     * Default constructor for the cart
     */
    public Cart() {
        // Initialize items array
        cartIdx = 0;
        items = new Item[1];
    }

    /**
     * Expands the cart by enlarging the items array by 1 unit.
     */
    private void expandCart() {
        // Make new array to put current items in
        Item[] expandedCart = new Item[items.length + 1];

        // Populate new array
        for (int idx = 0; idx < items.length; idx++) {
            expandedCart[idx] = items[idx];
        }

        // Apply new items array
        cartIdx++;
        items = expandedCart;
    }

    /**
     * Adds an item to the cart.
     * 
     * @param item Item to be added.
     */
    public void addItem(Item item) {
        // Iterate though the items array
        for (Item existingItem : items) {
            // Make sure that if the item already exists in the array, just modify the
            // quantity of
            // what's already in the cart.
            if (existingItem != null && existingItem.getItemCode() == item.getItemCode()) {
                existingItem.add(item);
                return;
            }
        }

        // If the item is not in the cart, add it and expand the cart for more unique
        // items.
        items[cartIdx] = item;
        expandCart();
    }

    /**
     * Calculate the subtotal of the items in the cart.
     * 
     * @return Item subtotal as a float
     */
    public float cartSubTotal() {
        float subTotal = 0f;

        // Iterate though all itesma and add their prices
        for (Item item : items) {
            if (item != null) {
                subTotal += item.getFinalPrice();
            }
        }

        return subTotal;
    }

    /**
     * Calculate cart total with tax.
     * 
     * @param tax Tax to be added.
     * @return Cart total witht tax.
     */
    public float cartTotal(float tax) {
        return cartSubTotal() * (1 + tax);
    }

    /**
     * Converts cart to a readable string that lists its items
     */
    @Override
    public String toString() {
        String out = "Items List:\n";

        // Iterate through cart and add format items
        for (Item item : items) {
            if (item != null) {
                out += String.format("    %-16s$%7.2f\n", item.toString(), item.getFinalPrice());
            }
        }
        return out;
    }
}
