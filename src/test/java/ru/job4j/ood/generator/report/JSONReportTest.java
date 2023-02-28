package ru.job4j.ood.generator.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.generator.model.Employee;
import ru.job4j.ood.generator.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(2023, Calendar.FEBRUARY, 28);
        Employee worker1 = new Employee("Ivan", date, date, 100);
        Employee worker2 = new Employee("Petr", date, date, 150);
        Employee worker3 = new Employee("Dmitry", date, date, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new JSONReport(store);
        StringBuilder expect = new StringBuilder()
                .append("[")
                .append("{\"name\":\"Ivan\",")
                .append("\"hired\":\"28.02.2023\",")
                .append("\"fired\":\"28.02.2023\",")
                .append("\"salary\":100.0}")
                .append(",")
                .append("{\"name\":\"Petr\",")
                .append("\"hired\":\"28.02.2023\",")
                .append("\"fired\":\"28.02.2023\",")
                .append("\"salary\":150.0}")
                .append(",")
                .append("{\"name\":\"Dmitry\",")
                .append("\"hired\":\"28.02.2023\",")
                .append("\"fired\":\"28.02.2023\",")
                .append("\"salary\":200.0}")
                .append("]");
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}