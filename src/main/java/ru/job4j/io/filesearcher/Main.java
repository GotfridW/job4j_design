package ru.job4j.io.filesearcher;

import java.io.IOException;

public class Main {

    /**
     * Ключи:
     * -d - директория, в которой начинать поиск.
     * -n - имя файла, маска, либо регулярное выражение.
     * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
     * -o - результат записать в файл.
     */

    public static void main(String[] args) throws IOException {
        Searcher searcher = new Searcher();
        searcher.run(new String[] {
                "-d=D:/test", "-n=*/**/*.txt", "-t=mask", "-o=result.txt"});
    }
}
