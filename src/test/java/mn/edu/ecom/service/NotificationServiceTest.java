package mn.edu.ecom.service;

import mn.edu.ecom.domain.cart.Cart;
import mn.edu.ecom.domain.cart.CartItem;
import mn.edu.ecom.domain.order.model.Order;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private Order sampleOrder() {
        Cart cart = new Cart(UUID.randomUUID());
        cart.addItem(new CartItem("BOOK-01", 1, 50.0));
        return new OrderService().createOrder(cart, "STANDARD");
    }

    @Test
    void emailUsesHtmlFormat() {
        String msg = new NotificationService().notifyCustomer("EMAIL", "a@b.mn", sampleOrder());
        assertTrue(msg.startsWith("<html>"));
    }

    @Test
    void smsUsesShortFormat() {
        String msg = new NotificationService().notifyCustomer("SMS", "+97699112233", sampleOrder());
        assertTrue(msg.contains("confirmed"));
        assertFalse(msg.contains("<html>"));
    }
}
