package ru.job4j.ood.generator.report;

import ru.job4j.ood.generator.model.Employee;
import ru.job4j.ood.generator.store.MemStore;
import ru.job4j.ood.generator.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {
    private final Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Employees {
        @XmlElement(name = "employee")
        private List<Employee> employees;

        public Employees() { }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setUsers(List<Employee> employees) {
            this.employees = employees;
        }
    }

    public static class CalendarAdapter extends XmlAdapter<String, Calendar> {
        private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

        @Override
        public Calendar unmarshal(String s) throws Exception {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DATE_FORMAT.parse(s));
            return calendar;
        }

        @Override
        public String marshal(Calendar calendar) throws Exception {
            return DATE_FORMAT.format(calendar.getTime());
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            List<Employee> employees = store.findBy(filter);
            StringWriter writer = new StringWriter();
            marshaller.marshal(new Employees(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report report = new XMLReport(store);
        System.out.println(report.generate(em -> true));
    }
}
