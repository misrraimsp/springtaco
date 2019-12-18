package misrraimsp.sia.springtaco.data;

import misrraimsp.sia.springtaco.domain.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
