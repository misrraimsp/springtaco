package misrraimsp.sia.springtaco.data;

import misrraimsp.sia.springtaco.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}