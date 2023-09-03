package ru.job4j.ood.dip.violations;

import java.util.ArrayList;

/*
В данном классе продемонстрирован ряд примеров нарушения принципа DIP:
1. основной класс хранит поле конкретного типа БД, что делает класс MediaLibrary - модуль высокого уровня -
зависимым от деталей данной конкретной реализации. Необходимо определить некий интерфейс MediaDB, определяющий
основной функционал абстрактной базы данных, и использовать уже этот интерфейс как поле хранилища;
2. исходя из предыдущего пункта, конструктор в данном классе принимает в качестве аргумента также конкретный
тип объекта БД, что является нарушением DIP;
3. метод saveToDB "работает" с объектом конкретного типа Film, что также потенциально делает нас зависимыми
от его конкретной реализации. Более правильным будет определить абстрактную сущность MediaEntity, которая и будет
использоваться в исходном классе. От неё же и будет наследоваться Film или же любой другой кандидат на хранение
в MediaLibrary;
4. Методы getFromDB и getAll возвращают всё ту же конкретную реализацию Film и ArrayList c объектами Film, что также
является нарушением DIP.
 */
public class MediaLibrary {
    private final SQLMediaDatabase mediaDB;

    public MediaLibrary(SQLMediaDatabase mediaDB) {
        this.mediaDB = mediaDB;
    }

    public Film getFromDB(String id) {
        return null;
    }

    public ArrayList<Film> getAll() {
        return null;
    }

    public boolean saveToDB(Film film) {
        /*
        saving logic;
         */
        return true;
    }

    public static class SQLMediaDatabase {
        /*
        Some database logic
         */
    }

    public static class Film {
        private String id;
        private final String name;

        public Film(String name) {
            this.name = name;
        }

        /*
        Getters, setters, equals/hashcode
         */
    }
}
