package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "conscript")
@XmlAccessorType(XmlAccessType.FIELD)
public class Conscript {
    @XmlAttribute
    private boolean hasHigherEducation;
    @XmlAttribute
    private String speciality;
    private int stockCategory;
    private Person person;
    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private String[] skills;

    public Conscript() { }

    public Conscript(boolean hasHigherEducation, String speciality, int stockCategory, Person person, String[] skills) {
        this.hasHigherEducation = hasHigherEducation;
        this.speciality = speciality;
        this.stockCategory = stockCategory;
        this.person = person;
        this.skills = skills;
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
                        "Married", "Worker"),
                new String[] {"Driving", "Swimming"}
        );
        final Gson gson = new GsonBuilder().create();
        String conscriptJson = gson.toJson(conscript);
        System.out.println(gson.toJson(conscript));

        Conscript conscFromJson = gson.fromJson(conscriptJson, Conscript.class);
        System.out.println(conscFromJson);
    }
}
