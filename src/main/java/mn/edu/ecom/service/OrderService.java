package mn.edu.ecom.service;

import mn.edu.ecom.domain.cart.Cart;
import mn.edu.ecom.domain.order.model.Order;

/**
 * BASELINE OrderService. The switch on the tier string is the smell the
 * Factory Method branch removes (Open/Closed Principle: adding a tier
 * currently means editing this class).
 */
public class OrderService {

    public Order createOrder(Cart cart, String tier) {
        Order order;
        switch (tier.toUpperCase()) {
            case "STANDARD" ->
                order = new Order(cart.getId(), cart.getCustomerId(), cart.getItems(), 0.10, 0.0, "STANDARD");
            case "VIP" ->
                order = new Order(cart.getId(), cart.getCustomerId(), cart.getItems(), 0.10, 0.15, "VIP_PRIORITY");
            default -> throw new IllegalArgumentException("Unknown tier: " + tier);
        }
        order.applyAuditTimestamp();
        return order;
    }
}
