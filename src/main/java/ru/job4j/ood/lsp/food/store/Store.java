package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.model.Food;

import java.util.List;

public interface Store extends AutoCloseable {

    Food add(Food food);

    boolean replace(int id, Food food);

    boolean delete(int id);

    List<Food> findAll();

    List<Food> findByName(String key);

    Food findById(int id);
}
