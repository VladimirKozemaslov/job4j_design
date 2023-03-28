package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.model.Bread;
import ru.job4j.ood.lsp.food.model.Cheese;
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

    @Disabled
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

    @Test
    public void whenResortItems() {
        Food milk = new Milk("Happy cow",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("81.8"),
                new BigDecimal("20.5"),
                3.2F);
        milk.getCreateDate().set(2023, Calendar.FEBRUARY, 20, 12, 30);
        milk.getExpiryDate().set(2023, Calendar.MARCH, 6, 10, 15);
        Food milk2 = new Milk("Happy cow",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("81.8"),
                new BigDecimal("20.5"),
                3.2F);
        milk2.getCreateDate().set(2023, Calendar.MARCH, 20, 9, 15);
        milk2.getExpiryDate().set(2023, Calendar.MARCH, 30, 9, 0);
        Food milk3 = new Milk("Happy cow",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("81.8"),
                new BigDecimal("20.5"),
                3.2F);
        milk3.getCreateDate().set(2023, Calendar.MARCH, 28, 9, 15);
        milk3.getExpiryDate().set(2023, Calendar.APRIL, 9, 9, 0);
        Food bread = new Bread("White",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("55.2"),
                new BigDecimal("10.3"),
                true);
        bread.getCreateDate().set(2023, Calendar.FEBRUARY, 20, 12, 30);
        bread.getExpiryDate().set(2023, Calendar.FEBRUARY, 25, 10, 15);
        Food bread2 = new Bread("White",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("55.2"),
                new BigDecimal("10.3"),
                true);
        bread2.getCreateDate().set(2023, Calendar.MARCH, 26, 10, 40);
        bread2.getExpiryDate().set(2023, Calendar.MARCH, 31, 10, 15);
        Food bread3 = new Bread("White",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("55.2"),
                new BigDecimal("10.3"),
                true);
        bread3.getCreateDate().set(2023, Calendar.MARCH, 28, 9, 5);
        bread3.getExpiryDate().set(2023, Calendar.APRIL, 3, 10, 20);
        Food cheese = new Cheese("Lamber",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("455.6"),
                new BigDecimal("110.2"));
        cheese.getCreateDate().set(2023, Calendar.FEBRUARY, 15, 11, 10);
        cheese.getExpiryDate().set(2023, Calendar.MARCH, 16, 11, 20);
        Food cheese2 = new Cheese("Lamber",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("455.6"),
                new BigDecimal("110.2"));
        cheese2.getCreateDate().set(2023, Calendar.MARCH, 5, 11, 10);
        cheese2.getExpiryDate().set(2023, Calendar.MARCH, 30, 11, 20);
        Food cheese3 = new Cheese("Lamber",
                Calendar.getInstance(),
                Calendar.getInstance(),
                new BigDecimal("455.6"),
                new BigDecimal("110.2"));
        cheese3.getCreateDate().set(2023, Calendar.MARCH, 28, 11, 10);
        cheese3.getExpiryDate().set(2023, Calendar.APRIL, 25, 11, 20);

        ControlQuality cq = new ControlQuality(new Warehouse(), new Shop(), new Trash());
        Store warehouse = cq.getWarehouse();
        Store shop = cq.getShop();
        Store trash = cq.getTrash();
        warehouse.add(milk);
        warehouse.add(milk2);
        warehouse.add(milk3);
        warehouse.add(bread);
        warehouse.add(bread2);
        warehouse.add(bread3);
        warehouse.add(cheese);
        warehouse.add(cheese2);
        warehouse.add(cheese3);
        cq.resort();
        assertThat(warehouse.findAll().size()).isEqualTo(3);
        assertThat(warehouse.findByName("Happy cow")).contains(milk3);
        assertThat(warehouse.findByName("White")).contains(bread3);
        assertThat(warehouse.findByName("Lamber")).contains(cheese3);
        assertThat(shop.findAll().size()).isEqualTo(3);
        assertThat(shop.findByName("White")).contains(bread2);
        assertThat(shop.findByName("Happy cow")).contains(milk2);
        assertThat(shop.findByName("Lamber")).contains(cheese2);
        assertThat(trash.findAll().size()).isEqualTo(3);
        assertThat(trash.findByName("Happy cow")).contains(milk);
        assertThat(trash.findByName("White")).contains(bread);
        assertThat(trash.findByName("Lamber")).contains(cheese);
    }
}