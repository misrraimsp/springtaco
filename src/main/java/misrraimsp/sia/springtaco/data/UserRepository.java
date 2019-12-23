package misrraimsp.sia.springtaco.data;

import misrraimsp.sia.springtaco.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}