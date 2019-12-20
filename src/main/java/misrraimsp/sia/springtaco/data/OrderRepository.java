package misrraimsp.sia.springtaco.data;

import misrraimsp.sia.springtaco.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}