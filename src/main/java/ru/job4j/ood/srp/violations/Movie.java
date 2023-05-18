package ru.job4j.ood.srp.violations;

/*
Возможно, не самый удачный пример, т.к. класс является моделью данных. Тем не менее в классе
определен функционал, ответственный за различные операции, а значит, и причин для изменения
класса будет несколько. Это нарушает принцип SRP. Так, механизм проигрывания фильма play()
более логичным будет реализовать в другом интерфейсе/классе, например, кинотеатра или DVD-плеера.
Наиболее вероятно, то же касается и метода отображения информации о фильме. При этом в методе
определена конкретная реализация формата вывода, что по моему мнению, будет нарушением DIP.
Формат вывода, на мой взгляд, также целесообразно выделить отдельным интерфейсом -> классом.
 */

public class Movie {
    private int id;
    private final String title;
    private final int year;

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void displayInfo() {
        System.out.printf(String.format("Id: %s%nTitle: %s%nYear: %s",
                getId(), getTitle(), getYear()));
    }

    public void play() {

    }
}
