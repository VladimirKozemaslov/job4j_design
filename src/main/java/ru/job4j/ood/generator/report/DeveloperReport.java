package ru.job4j.ood.generator.report;

import ru.job4j.ood.generator.formatter.DateTimeParser;
import ru.job4j.ood.generator.model.Employee;
import ru.job4j.ood.generator.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class DeveloperReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public DeveloperReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(",")
                    .append(dateTimeParser.parse(employee.getHired())).append(",")
                    .append(dateTimeParser.parse(employee.getFired())).append(",")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
