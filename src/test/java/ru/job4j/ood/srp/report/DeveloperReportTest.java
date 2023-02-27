package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.generator.formatter.DateTimeParser;
import ru.job4j.ood.generator.formatter.ReportDateTimeParser;
import ru.job4j.ood.generator.model.Employee;
import ru.job4j.ood.generator.report.DeveloperReport;
import ru.job4j.ood.generator.report.Report;
import ru.job4j.ood.generator.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class DeveloperReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 150);
        Employee worker3 = new Employee("Dmitry", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new DeveloperReport(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(",")
                .append(parser.parse(worker1.getHired())).append(",")
                .append(parser.parse(worker1.getFired())).append(",")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(",")
                .append(parser.parse(worker2.getHired())).append(",")
                .append(parser.parse(worker2.getFired())).append(",")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(",")
                .append(parser.parse(worker3.getHired())).append(",")
                .append(parser.parse(worker3.getFired())).append(",")
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}