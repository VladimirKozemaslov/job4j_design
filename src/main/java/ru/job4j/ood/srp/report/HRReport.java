package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class HRReport implements Report {
    private final Store store;

    public HRReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        employees.forEach(e -> addRecord(text, e));
        return text.toString();
    }

    private void addRecord(StringBuilder text, Employee employee) {
        text.append(employee.getName()).append(" ")
                .append(employee.getSalary())
                .append(System.lineSeparator());
    }
}
