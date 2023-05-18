package ru.job4j.ood.srp.violations;

public interface Modem {

    void connect();

    void disconnect();

    void send(char c);

    char receive();

    int displaySpeed();
}
