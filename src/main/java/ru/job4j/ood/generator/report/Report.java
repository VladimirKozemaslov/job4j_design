package ru.job4j.ood.generator.report;

import ru.job4j.ood.generator.model.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}