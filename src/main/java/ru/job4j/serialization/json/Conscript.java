package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Conscript {
    private final boolean hasHigherEducation;
    private final String speciality;
    private final int stockCategory;
    private final Person person;
    private final String[] skills;

    public Conscript(boolean hasHigherEducation, String speciality, int stockCategory, Person person, String[] skills) {
        this.hasHigherEducation = hasHigherEducation;
        this.speciality = speciality;
        this.stockCategory = stockCategory;
        this.person = person;
        this.skills = skills;
    }

    public boolean isHasHigherEducation() {
        return hasHigherEducation;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getStockCategory() {
        return stockCategory;
    }

    public Person getPerson() {
        return person;
    }

    public String[] getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Conscript{"
                + "hasHigherEducation=" + hasHigherEducation
                + ", speciality='" + speciality + '\''
                + ", stockCategory=" + stockCategory
                + ", person=" + person
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }

    public static void main(String[] args) {
        Conscript conscript = new Conscript(
                true,
                "Driver",
                837,
                new Person(
                        true,
                        25,
                        new Contact("+123456"),
                        new String[] {"Married", "Worker"}),
                new String[] {"Driving", "Swimming"}
                );
        final Gson gson = new GsonBuilder().create();
        String conscriptJson = gson.toJson(conscript);
        System.out.println(gson.toJson(conscript));

        Conscript conscFromJson = gson.fromJson(conscriptJson, Conscript.class);
        System.out.println(conscFromJson);
    }
}
