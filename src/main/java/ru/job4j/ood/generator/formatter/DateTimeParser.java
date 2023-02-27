package ru.job4j.ood.generator.formatter;

public interface DateTimeParser<T> {
    String parse(T t);
}