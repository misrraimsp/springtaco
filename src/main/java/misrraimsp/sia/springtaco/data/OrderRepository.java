package misrraimsp.sia.springtaco.data;

import misrraimsp.sia.springtaco.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
