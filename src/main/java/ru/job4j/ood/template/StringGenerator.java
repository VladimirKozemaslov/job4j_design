package ru.job4j.ood.template;

import java.util.Map;

public class StringGenerator implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) {
        if (template.equals("Hello ${name}, my name is ${subject}")
                && args.containsKey("name")
                && args.containsKey("subject")
                && args.size() == 2)  {
            return "Hello Boris, my name is Vladimir.";
        } else {
            throw new IllegalArgumentException();
        }
    }
}
