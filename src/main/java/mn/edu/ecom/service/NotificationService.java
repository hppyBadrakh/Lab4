package mn.edu.ecom.service;

import mn.edu.ecom.domain.order.model.Order;

/**
 * BASELINE NotificationService. Channel + message format are chosen with
 * if/else, so sender and formatter can get mismatched. The Abstract Factory
 * branch replaces this with NotificationFactory (Channel + Formatter family).
 */
public class NotificationService {

    /** @return the text that was "sent", so tests can inspect it. */
    public String notifyCustomer(String channel, String recipient, Order order) {
        String message;
        if (channel.equalsIgnoreCase("EMAIL")) {
            message = "<html><body>Order #" + order.getOrderId()
                    + " Total: $" + order.calculateFinalPrice() + "</body></html>";
            System.out.println("SMTP TLS email to " + recipient);
        } else if (channel.equalsIgnoreCase("SMS")) {
            message = "Order #" + order.getOrderId()
                    + " confirmed. Total: $" + order.calculateFinalPrice() + ". ecom.mn/t";
            System.out.println("GSM SMS to " + recipient);
        } else {
            throw new IllegalArgumentException("Unknown channel: " + channel);
        }
        return message;
    }
}
