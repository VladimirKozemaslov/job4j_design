package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractStore implements Store {
    private final List<Food> foodList = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    public Food add(Food food) {
        food.setId(ids++);
        foodList.add(food);
        return food;
    }

    public List<Food> findAll() {
        return List.copyOf(foodList);
    }

    public List<Food> findByName(String key) {
        List<Food> rsl = new ArrayList<>();
        for (Food food : foodList) {
            if (Objects.equals(food.getName(), key)) {
                rsl.add(food);
            }
        }
        return List.copyOf(rsl);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getId() == id) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public Food findById(int id) {
        int index = indexOf(id);
        return index != -1 ? foodList.get(index) : null;
    }

    public boolean replace(int id, Food food) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            food.setId(id);
            foodList.set(index, food);
        }
        return rsl;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            foodList.remove(index);
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {

    }
}
