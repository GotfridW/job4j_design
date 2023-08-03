package ru.job4j.ood.isp.violations.allinoneprinter;

import java.util.List;

public class ScanningDevice implements AllInOnePrinter {
    @Override
    public void print(List<String> printList) {
    }

    @Override
    public void scan() {
        System.out.println("Scanning....");
    }

    @Override
    public void copy() {
    }
}
