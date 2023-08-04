package ru.job4j.ood.isp.menu;

public class OutputAction implements ActionDelegate {
    private final String output;

    public OutputAction(String output) {
        if (output == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        this.output = output;
    }

    @Override
    public void delegate() {
        System.out.println(output);
    }
}
