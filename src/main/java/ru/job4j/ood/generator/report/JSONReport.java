package ru.job4j.ood.generator.report;

import com.google.gson.*;
import ru.job4j.ood.generator.model.Employee;
import ru.job4j.ood.generator.store.MemStore;
import ru.job4j.ood.generator.store.Store;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {
    private final Store store;
    private final Gson gson;

    public JSONReport(Store store) {
        this.store = store;
        gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Calendar.class, new CalendarSerializer())
                .create();
    }

    private static class CalendarSerializer implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
        private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

        @Override
        public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(DATE_FORMAT.format(src.getTime()));
        }

        @Override
        public Calendar deserialize(JsonElement json, Type typeOfT,  JsonDeserializationContext context) throws JsonParseException {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(json.getAsJsonPrimitive().getAsLong());
            return cal;
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return gson.toJson(employees);
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
                MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report report = new JSONReport(store);
        System.out.println(report.generate(em -> true));
    }
}
