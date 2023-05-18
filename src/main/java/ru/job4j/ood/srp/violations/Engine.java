package ru.job4j.ood.srp.violations;

/*
В данном классе, нарушение SRP заключается в наличии у класса нескольких методов/ответственностей,
а значит, более одной причины для изменения класса, что является нарушением SRP. Например, изменение
механизма остановки двигателя (stop()) может повлечь нарушение работы метода start(). В данном случае
объект Engine (двигатель) умеет как заводиться, так и глушиться (что, по сути является его косвенной
ответственностью - отсюда идея реализовать stop() в другом интерфейсе/классе). Также наш двигатель
умеет отображать обороты (showRevs()), что вовсе не входит в его ответственность, этот метод также
необходимо изъять из этого класса.
Плюс ко всему, данный класс не реализует интерфейсов, а определяет функционал сам по себе. Здесь
прослеживается нарушение принципа DIP: при использовании этого класса - конкретной реализации -
другими модулями (уровнем выше и или ниже, к примеру Generator или Car) может возникнуть ситуация,
когда рефакторинг Engine приведет к нарушению работы этих модулей.
 */

public class Engine {
    private int revs;
    private boolean working = false;

    public void start() {
        working = true;
    }

    public void stop() {
        if (working) {
            working = false;
        }
    }

    /*
    Данный метод выводит на консоль обороты двигателя
     */
    public void showRevs() {
        if (working) {
            System.out.println(revs);
        }
    }
}
