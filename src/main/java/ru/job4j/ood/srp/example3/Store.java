package ru.job4j.ood.srp.example3;

public interface Store {
    void save(Product product);

    Product search(String code);

}
