package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

public class AccountantReport extends ReportEngine {
    public AccountantReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        super(store, dateTimeParser);
    }

    @Override
    protected void addRecord(StringBuilder text, Employee employee) {
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        text.append(employee.getName()).append(" ")
                .append(dateTimeParser.parse(employee.getHired())).append(" ")
                .append(dateTimeParser.parse(employee.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, employee.getSalary(), Currency.USD))
                .append(System.lineSeparator());
    }
}
