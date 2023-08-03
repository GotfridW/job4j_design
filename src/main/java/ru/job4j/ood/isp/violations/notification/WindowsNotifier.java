package ru.job4j.ood.isp.violations.notification;

public class WindowsNotifier implements Notification {

    @Override
    public boolean notifyViaDesktop(String message) {
        System.out.println(message);
        return true;
    }

    @Override
    public boolean notifyViaSmartphone(String message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean notifyViaEmail(String message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean notifyViaBrowser(String message) {
        throw new UnsupportedOperationException();
    }
}
