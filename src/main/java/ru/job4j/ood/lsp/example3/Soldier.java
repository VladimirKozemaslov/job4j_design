package ru.job4j.ood.lsp.example3;

public class Soldier {
    protected int age;

    protected boolean isInPhysicalCondition;

    public Soldier(int age, boolean isInPhysicalCondition) {
        this.age = age;
        this.isInPhysicalCondition = isInPhysicalCondition;
    }

    public boolean isMobilisation() {
        return age >= 18 && age < 45 && isInPhysicalCondition;
    }
}
