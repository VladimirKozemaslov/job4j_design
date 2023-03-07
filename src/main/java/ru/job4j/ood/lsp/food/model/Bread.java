package ru.job4j.ood.lsp.food.model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Bread extends Food {
    private boolean isContainsGluten;

    public Bread(String name,
                 Calendar expiryDate,
                 Calendar createDate,
                 BigDecimal price,
                 BigDecimal discount,
                 boolean isContainsGluten) {
        super(name, expiryDate, createDate, price, discount);
        this.isContainsGluten = isContainsGluten;
    }

    public boolean isContainsGluten() {
        return isContainsGluten;
    }

    public void setContainsGluten(boolean containsGluten) {
        isContainsGluten = containsGluten;
    }
}
