package ru.job4j.ood.isp.violations.notification;

/*
Данный пример нарушает принцип ISP за счет объявления методов различных типов уведомления, которые однозначно
будут иметь разную логику работы. При реализации такого интерфейса таким классом, как представленный
WindowsNotifier, мы будем зависеть от всех членов интерфейса, в т.ч. неиспользуемых классом.
Решение - развести интерфейс на несколько и реализовывать уже их.
 */
public interface Notification {
    boolean notifyViaDesktop(String message);
    boolean notifyViaSmartphone(String message);
    boolean notifyViaEmail(String message);
    boolean notifyViaBrowser(String message);
}
