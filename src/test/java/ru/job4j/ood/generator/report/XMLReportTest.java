package ru.job4j.ood.generator.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.generator.model.Employee;
import ru.job4j.ood.generator.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLReportTest {
    @Test
    public void whenOldGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(2023, Calendar.FEBRUARY, 28);
        Employee worker1 = new Employee("Ivan", date, date, 100);
        Employee worker2 = new Employee("Petr", date, date, 150);
        Employee worker3 = new Employee("Dmitry", date, date, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new XMLReport(store);

        StringBuilder expect = new StringBuilder(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<employees>\n"
                        + "    <employee>\n"
                        + "        <name>Ivan</name>\n"
                        + "        <hired>28.02.2023</hired>\n"
                        + "        <fired>28.02.2023</fired>\n"
                        + "        <salary>100.0</salary>\n"
                        + "    </employee>\n"
                        + "    <employee>\n"
                        + "        <name>Petr</name>\n"
                        + "        <hired>28.02.2023</hired>\n"
                        + "        <fired>28.02.2023</fired>\n"
                        + "        <salary>150.0</salary>\n"
                        + "    </employee>\n"
                        + "    <employee>\n"
                        + "        <name>Dmitry</name>\n"
                        + "        <hired>28.02.2023</hired>\n"
                        + "        <fired>28.02.2023</fired>\n"
                        + "        <salary>200.0</salary>\n"
                        + "    </employee>\n"
                        + "</employees>\n");
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}