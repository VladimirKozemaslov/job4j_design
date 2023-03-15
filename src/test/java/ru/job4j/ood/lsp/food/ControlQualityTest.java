package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.model.Milk;
import ru.job4j.ood.lsp.food.store.*;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ControlQualityTest {

    @Disabled
    @Test
    public void whenDistributeItemToWarehouse() {
        Food milk = new Milk("Happy cow",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("81.8"),
                new BigDecimal("20.5"),
                3.2F);
        milk.getCreateDate().set(2023, Calendar.MARCH, 7, 12, 30);
        milk.getExpiryDate().set(2023, Calendar.MARCH, 17, 10, 15);

        ControlQuality cq = new ControlQuality(new Warehouse(), new Shop(), new Trash());
        cq.distribute(milk);
        Store warehouse = cq.getWarehouse();
        Store shop = cq.getShop();
        Store trash = cq.getTrash();

        assertThat(warehouse.findById(milk.getId())).isNotNull();
        assertThat(shop.findById(milk.getId())).isNull();
        assertThat(trash.findById(milk.getId())).isNull();
    }

    @Test
    public void whenDistributeItemToShop() {
        Food milk = new Milk("Happy cow",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("81.8"),
                new BigDecimal("20.5"),
                3.2F);
        milk.getCreateDate().set(2023, Calendar.MARCH, 1, 12, 30);
        milk.getExpiryDate().set(2023, Calendar.MARCH, 17, 10, 15);

        ControlQuality cq = new ControlQuality(new Warehouse(), new Shop(), new Trash());
        cq.distribute(milk);
        Store warehouse = cq.getWarehouse();
        Store shop = cq.getShop();
        Store trash = cq.getTrash();

        assertThat(warehouse.findById(milk.getId())).isNull();
        assertThat(shop.findById(milk.getId())).isNotNull();
        assertThat(trash.findById(milk.getId())).isNull();
    }

    @Disabled
    @Test
    public void whenDistributeItemToShopWithDiscount() {
        Food milk = new Milk("Happy cow",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("81.8"),
                new BigDecimal("20.5"),
                3.2F);
        milk.getCreateDate().set(2023, Calendar.FEBRUARY, 25, 12, 30);
        milk.getExpiryDate().set(2023, Calendar.MARCH, 10, 10, 15);

        ControlQuality cq = new ControlQuality(new Warehouse(), new Shop(), new Trash());
        cq.distribute(milk);
        Store warehouse = cq.getWarehouse();
        Store shop = cq.getShop();
        Store trash = cq.getTrash();

        assertThat(warehouse.findById(milk.getId())).isNull();
        assertThat(shop.findById(milk.getId())).isNotNull();
        assertThat(trash.findById(milk.getId())).isNull();
        assertThat(milk.getPrice()).isEqualTo(new BigDecimal("61.3"));
    }

    @Test
    public void whenDistributeItemToTrash() {
        Food milk = new Milk("Happy cow",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("81.8"),
                new BigDecimal("20.5"),
                3.2F);
        milk.getCreateDate().set(2023, Calendar.FEBRUARY, 20, 12, 30);
        milk.getExpiryDate().set(2023, Calendar.MARCH, 6, 10, 15);

        ControlQuality cq = new ControlQuality(new Warehouse(), new Shop(), new Trash());
        cq.distribute(milk);
        Store warehouse = cq.getWarehouse();
        Store shop = cq.getShop();
        Store trash = cq.getTrash();

        assertThat(warehouse.findById(milk.getId())).isNull();
        assertThat(shop.findById(milk.getId())).isNull();
        assertThat(trash.findById(milk.getId())).isNotNull();
    }
}