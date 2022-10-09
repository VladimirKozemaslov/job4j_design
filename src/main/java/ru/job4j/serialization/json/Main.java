package ru.job4j.serialization.json;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {

        Contact contact = new Contact("+1234567");
        Person person = new Person(false, 30, contact, new String[] {"Worker", "Married"});
        Conscript conscript = new Conscript(true, "Driver", 837, person, new String[]{"Driving", "Swimming"});

        JSONObject jsonContact = new JSONObject(contact);
        JSONObject jsonPerson = new JSONObject(person);
        JSONObject jsonConscript = new JSONObject(conscript);

        System.out.println(jsonContact);
        System.out.println(jsonPerson);
        System.out.println(jsonConscript);
    }
}