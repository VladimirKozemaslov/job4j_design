package ru.job4j.ood.lsp.preconditions;

public class FirstRule {
    public static void main(String[] args) {
        AutoTransport bus = new Bus(3);
        bus.move(2);
    }
}
