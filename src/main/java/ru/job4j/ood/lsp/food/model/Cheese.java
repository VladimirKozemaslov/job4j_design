package ru.job4j.ood.lsp.food.model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Cheese extends Food {

    public Cheese(String name,
                  Calendar expiryDate,
                  Calendar createDate,
                  BigDecimal price,
                  BigDecimal discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
