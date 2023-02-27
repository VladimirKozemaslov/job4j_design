package ru.job4j.ood.generator.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}