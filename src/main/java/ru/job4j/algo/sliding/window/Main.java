package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс для решения алгоритмической задачи методом плавающего окна.  Условие задачи. Дан массив интервалов, где
 * каждый интервал представлен двумя числами `[start, end]`, которые обозначают начало и конец интервала.
 * Требуется найти границы нового интервала, который перекрывает максимальное количество других интервалов.
 * @author Александр Белянов
 */
public class Main {
    /**
     * Преобразует заданный список интервалов в список объектов {@link Event}, представляющих события начала/конца
     * интервала.
     *
     * @param intervals Заданный список интервалов.
     * @return Список объектов {@link Event}.
     */
    private static List<Event> collectAllEventsOrdered(List<Interval> intervals) {
        List<Event> events = new ArrayList<>();
        for (Interval interval : intervals) {
            events.add(new Event(interval.start, true));
            events.add(new Event(interval.end, false));
        }
        Collections.sort(events);
        return events;
    }

    /**
     * Основной метод класса для нахождения границы нового интервала, который перекрывает максимальное количество
     * других интервалов из данного {@code intervals}.
     *
     * @param intervals Данный список интервалов.
     * @return Массив из двух чисел, представляющих начало и конец нового интервала или [-1, -1], если такой интервал
     * не был найден.
     */
    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        List<Event> events = collectAllEventsOrdered(intervals);
        int maxOverlapping = 0;
        int currOverlapping = 0;
        int maxStart = -1;
        int maxEnd = -1;
        int currStart = -1;
        for (Event event : events) {
            if (event.isStart) {
                currOverlapping++;
                if (currOverlapping > maxOverlapping) {
                    maxOverlapping = currOverlapping;
                    currStart = event.time;
                }
            } else {
                if (currOverlapping == maxOverlapping) {
                    maxStart = currStart;
                    maxEnd = event.time;
                }
                currOverlapping--;
            }
        }
        return new int[]{maxStart, maxEnd};
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: ["
                + result[0] + ", " + result[1] + "]");
    }
}
