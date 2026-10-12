package mn.edu.ecom.domain.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {
    private final UUID id = UUID.randomUUID();
    private final UUID customerId;
    private final List<CartItem> items = new ArrayList<>();

    public Cart(UUID customerId) {
        this.customerId = customerId;
    }

    public void addItem(CartItem item) { items.add(item); }

    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public List<CartItem> getItems() { return items; }
}
