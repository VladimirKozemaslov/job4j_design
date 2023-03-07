package ru.job4j.ood.lsp.food.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.model.Bread;
import ru.job4j.ood.lsp.food.model.Cheese;
import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.model.Milk;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrashTest {
    @Test
    public void whenSuccessfullyAddFood() {
        try (Store warehouse = new Warehouse()) {
            Food bread = new Bread("French baguette",
                    Calendar.getInstance(),
                    Calendar.getInstance(),
                    new BigDecimal("65"),
                    new BigDecimal("15.5"),
                    true);
            warehouse.add(bread);
            assertThat(warehouse.findById(bread.getId())).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindAllAddedFood() {
        try (Store warehouse = new Warehouse()) {
            Food bread = new Bread("French baguette",
                    Calendar.getInstance(),
                    Calendar.getInstance(),
                    new BigDecimal("65"),
                    new BigDecimal("15.5"),
                    true);
            Food milk = new Milk("Happy cow",
                    Calendar.getInstance(),
                    Calendar.getInstance(),
                    new BigDecimal("81.8"),
                    new BigDecimal("20.5"),
                    3.2F);
            Food cheese = new Cheese("Cheddar",
                    Calendar.getInstance(),
                    Calendar.getInstance(),
                    new BigDecimal("285"),
                    new BigDecimal("44")
            );
            warehouse.add(bread);
            warehouse.add(milk);
            warehouse.add(cheese);
            assertThat(warehouse.findAll()).isEqualTo(List.of(bread, milk, cheese));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindFoodById() {
        try (Store warehouse = new Warehouse()) {
            Food bread = new Bread("French baguette",
                    Calendar.getInstance(),
                    Calendar.getInstance(),
                    new BigDecimal("65"),
                    new BigDecimal("15.5"),
                    true);
            Food milk = new Milk("Happy cow",
                    Calendar.getInstance(),
                    Calendar.getInstance(),
                    new BigDecimal("81.8"),
                    new BigDecimal("20.5"),
                    3.2F);

            warehouse.add(bread);
            warehouse.replace(bread.getId(), milk);
            assertThat(warehouse.findById(bread.getId())).isNotEqualTo(bread);
            assertThat(warehouse.findById(milk.getId())).isEqualTo(milk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReplaceFood() {
        try (Store warehouse = new Warehouse()) {
            Food cheese = new Cheese("Cheddar",
                    Calendar.getInstance(),
                    Calendar.getInstance(),
                    new BigDecimal("285"),
                    new BigDecimal("44")
            );

            warehouse.add(cheese);
            assertThat(warehouse.findById(cheese.getId())).isEqualTo(cheese);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteFood() {
        try (Store warehouse = new Warehouse()) {
            Food cheese = new Cheese("Cheddar",
                    Calendar.getInstance(),
                    Calendar.getInstance(),
                    new BigDecimal("285"),
                    new BigDecimal("44")
            );

            warehouse.add(cheese);
            warehouse.delete(cheese.getId());
            assertThat(warehouse.findById(cheese.getId())).isNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}