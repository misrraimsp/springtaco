package misrraimsp.sia.springtaco.data;

import misrraimsp.sia.springtaco.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
