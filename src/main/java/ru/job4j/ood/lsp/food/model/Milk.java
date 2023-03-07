package ru.job4j.ood.lsp.food.model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Milk extends Food {
    private float fatContent;

    public Milk(String name,
                Calendar expiryDate,
                Calendar createDate,
                BigDecimal price,
                BigDecimal discount,
                float fatContent) {
        super(name, expiryDate, createDate, price, discount);
        this.fatContent = fatContent;
    }

    public float getFatContent() {
        return fatContent;
    }

    public void setFatContent(float fatContent) {
        this.fatContent = fatContent;
    }
}
