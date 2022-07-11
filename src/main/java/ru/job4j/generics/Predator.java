package ru.job4j.generics;

import java.util.List;

public class Predator extends Animal {
    private String weapon;

    public Predator(String name, String weapon) {
        super(name);
        this.weapon = weapon;
    }

    public String getWeapon() {
        return weapon;
    }

    @Override
    public String toString() {
        return "Predator{"
                + "weapon='" + weapon + '\''
                + '}';
    }
}
