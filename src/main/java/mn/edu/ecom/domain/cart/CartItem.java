package mn.edu.ecom.domain.cart;

public class CartItem {
    private final String sku;
    private int quantity;
    private final double unitPrice;

    public CartItem(String sku, int quantity, double unitPrice) {
        this.sku = sku;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getSku() { return sku; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getLineTotal() { return quantity * unitPrice; }
}
