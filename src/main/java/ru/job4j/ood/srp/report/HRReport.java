package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class HRReport extends ReportEngine {
    public HRReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        super(store, dateTimeParser);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        addHead(text);
        List<Employee> employees = store.findBy(filter);
        employees.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        employees.forEach(e -> addRecord(text, e));
        return text.toString();
    }

    @Override
    protected void addHead(StringBuilder text) {
        text.append("Name; Salary;")
                .append(System.lineSeparator());
    }

    @Override
    protected void addRecord(StringBuilder text, Employee employee) {
        text.append(employee.getName()).append(" ")
                .append(employee.getSalary())
                .append(System.lineSeparator());
    }
}
