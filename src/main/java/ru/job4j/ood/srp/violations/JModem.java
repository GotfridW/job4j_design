package ru.job4j.ood.srp.violations;

/*
В данном классе, описывающем работу модема, определена группа методов выполняющих различные обязанности,
а следовательно этот класс имеет несколько причин для изменения, что является нарушением SRP.
Закономерным будет развести эти ответственности на отдельные интерфейсы/классы установки соединения, передачи
данных и отображения данных (скорости в данном случае).
*/

public class JModem implements Modem {
    private final String modem;
    private int speed;

    public JModem(String modem) {
        this.modem = modem;
    }

    @Override
    public void connect() {
    }

    @Override
    public void disconnect() {
    }

    @Override
    public void send(char c) {
    }

    @Override
    public char receive() {
        return 0;
    }

    @Override
    public int displaySpeed() {
        return speed;
    }
}
