package mn.edu.ecom.domain.order.model;

import mn.edu.ecom.domain.cart.CartItem;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * BASELINE Order (on main). Public constructor with 6 positional parameters:
 * this is the "telescoping constructor" smell that the Builder branch fixes.
 */
public class Order {
    private final UUID orderId = UUID.randomUUID();
    private final UUID cartId;
    private final UUID customerId;
    private final List<CartItem> items;
    private final double taxRate;
    private final double discountRate;
    private final String orderTier;
    private Instant auditTimestamp;

    public Order(UUID cartId, UUID customerId, List<CartItem> items,
                 double taxRate, double discountRate, String orderTier) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.items = List.copyOf(items);
        this.taxRate = taxRate;
        this.discountRate = discountRate;
        this.orderTier = orderTier;
    }

    /** Stamps the order with the current time. (Singleton branch: use SystemClock here.) */
    public void applyAuditTimestamp() {
        this.auditTimestamp = Instant.now();
    }

    public double calculateSubtotal() {
        return items.stream().mapToDouble(CartItem::getLineTotal).sum();
    }

    /** subtotal - discount, then + tax. */
    public double calculateFinalPrice() {
        double discounted = calculateSubtotal() * (1.0 - discountRate);
        return discounted * (1.0 + taxRate);
    }

    public UUID getOrderId() { return orderId; }
    public UUID getCartId() { return cartId; }
    public UUID getCustomerId() { return customerId; }
    public List<CartItem> getItems() { return items; }
    public double getTaxRate() { return taxRate; }
    public double getDiscountRate() { return discountRate; }
    public String getOrderTier() { return orderTier; }
    public Instant getAuditTimestamp() { return auditTimestamp; }
}
