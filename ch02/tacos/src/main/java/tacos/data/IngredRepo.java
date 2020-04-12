package tacos.data;

import tacos.Ingredient;

public interface IngredRepo {

    Ingredient findById(String id);

    Iterable<Ingredient> findAll();



}
