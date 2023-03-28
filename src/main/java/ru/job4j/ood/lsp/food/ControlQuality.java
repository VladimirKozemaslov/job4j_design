package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.store.Store;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class ControlQuality {
    private final Store warehouse;
    private final Store shop;
    private final Store trash;

    public ControlQuality(Store warehouse, Store shop, Store trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void distribute(Food food) {
        double expire = (double) food.getExpiryDate().getTimeInMillis();
        double create = (double) food.getCreateDate().getTimeInMillis();
        double now = Calendar.getInstance().getTimeInMillis();
        int expirationPercent = (int) Math.ceil(((now - create) / (expire - create)) * 100);
        if (expirationPercent < 25) {
            warehouse.add(food);
        } else if (expirationPercent < 75) {
            shop.add(food);
        } else if (expirationPercent < 100) {
            food.setPrice(food.getPrice().subtract(food.getDiscount()));
            shop.add(food);
        } else {
            trash.add(food);
        }
    }

    public void resort() {
        List<Food> foodList = new LinkedList<>();
        foodList.addAll(warehouse.findAll());
        foodList.addAll(shop.findAll());
        clear(warehouse);
        clear(shop);
        clear(trash);

        for (Food food : foodList) {
            distribute(food);
        }
    }

    private void clear(Store store) {
        for (Food food : store.findAll()) {
            store.delete(food.getId());
        }
    }

    public Store getWarehouse() {
        return warehouse;
    }

    public Store getShop() {
        return shop;
    }

    public Store getTrash() {
        return trash;
    }
}
