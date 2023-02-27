package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.generator.currency.Currency;
import ru.job4j.ood.generator.currency.CurrencyConverter;
import ru.job4j.ood.generator.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.generator.formatter.DateTimeParser;
import ru.job4j.ood.generator.formatter.ReportDateTimeParser;
import ru.job4j.ood.generator.model.Employee;
import ru.job4j.ood.generator.report.AccountantReport;
import ru.job4j.ood.generator.report.Report;
import ru.job4j.ood.generator.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountantReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Report report = new AccountantReport(store, parser, converter);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}