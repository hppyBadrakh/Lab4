package mn.edu.ecom.service;

import mn.edu.ecom.domain.cart.Cart;
import mn.edu.ecom.domain.cart.CartItem;
import mn.edu.ecom.domain.order.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/** Baseline tests: they must pass on main BEFORE you start any branch. */
class OrderServiceTest {

    private Cart testCart;
    private final OrderService service = new OrderService();

    @BeforeEach
    void setUp() {
        testCart = new Cart(UUID.randomUUID());
        testCart.addItem(new CartItem("LAPTOP-01", 1, 1000.0)); // 1000
        testCart.addItem(new CartItem("MOUSE-01", 2, 100.0));   // 200 -> subtotal 1200
    }

    @Test
    @DisplayName("STANDARD order: no discount, 10% tax -> 1320")
    void standardOrder() {
        Order order = service.createOrder(testCart, "STANDARD");
        assertEquals("STANDARD", order.getOrderTier());
        assertEquals(0.0, order.getDiscountRate(), 0.001);
        assertEquals(1320.0, order.calculateFinalPrice(), 0.01);
        assertNotNull(order.getAuditTimestamp());
    }

    @Test
    @DisplayName("VIP order: 15% discount, 10% tax -> 1122")
    void vipOrder() {
        Order order = service.createOrder(testCart, "VIP");
        assertEquals("VIP_PRIORITY", order.getOrderTier());
        assertEquals(0.15, order.getDiscountRate(), 0.001);
        assertEquals(1122.0, order.calculateFinalPrice(), 0.01);
    }

    @Test
    @DisplayName("Unknown tier is rejected")
    void unknownTier() {
        assertThrows(IllegalArgumentException.class, () -> service.createOrder(testCart, "GOLD"));
    }
}
