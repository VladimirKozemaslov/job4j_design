package ru.job4j.ood.lsp.example3;

public class Officer extends Soldier {
    private boolean isMilitaryEducated;

    public  Officer(int age, boolean isInPhysicalCondition, boolean isMilitaryEducated) {
        super(age, isInPhysicalCondition);
        this.isMilitaryEducated = isMilitaryEducated;
    }

    @Override
    public boolean isMobilisation() {
        return isMilitaryEducated;
    }
}
