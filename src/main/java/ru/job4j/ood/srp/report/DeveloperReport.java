package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

public class DeveloperReport extends ReportEngine {
    public DeveloperReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        super(store, dateTimeParser);
    }

    @Override
    protected void addHead(StringBuilder text) {
        text.append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator());
    }

    @Override
    protected void addRecord(StringBuilder text, Employee employee) {
        text.append(employee.getName()).append(",")
                .append(dateTimeParser.parse(employee.getHired())).append(",")
                .append(dateTimeParser.parse(employee.getFired())).append(",")
                .append(employee.getSalary())
                .append(System.lineSeparator());
    }
}
