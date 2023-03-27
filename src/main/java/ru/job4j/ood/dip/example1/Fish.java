package ru.job4j.ood.dip.example1;

import java.util.Objects;

public abstract class Fish {
    protected int id;
    protected String name;

    public Fish(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fish fish = (Fish) o;
        return id == fish.id && Objects.equals(name, fish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
