package ru.job4j.ood.isp.violations.vehicle;

/*
Данный интерфейс - грубый пример нарушения принципа ISP - определяет контракт работы различных и в основном
несовместимых видов транспорта. Реализация такого интерфейса конкретными классами транспортных очевидно
вынудит нас реализовывать методы, неиспользуемые этими классами, и, соответственно, при изменении интерфейса,
корректировать классы. Решение - определение нескольких интерфейсов по видам транспорта и дальнейшая их
реализация, возможно перекрёстная.
 */

public interface Vehicle {
    void drive();

    void fly();

    void sail();

    void dive();
}
