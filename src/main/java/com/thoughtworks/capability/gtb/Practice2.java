package com.thoughtworks.capability.gtb;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 对任意日期获取下一个工作日, 不考虑节假日
 *
 * @author itutry
 * @create 2020-05-15_17:20
 */
public class Practice2 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, 5, 1);
        LocalDate nextWorkDay = getNextWorkDate(date);
        System.out.println(nextWorkDay);
    }

    public static LocalDate getNextWorkDate(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayToAdd = calculateDaysToAdd(dayOfWeek);
        LocalDate nextWorkDay = date.plusDays(dayToAdd);
        return nextWorkDay;
    }

    private static Integer calculateDaysToAdd(DayOfWeek dayOfWeek) {
        int dayToAdd = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            dayToAdd = 3;
        }
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            dayToAdd = 2;
        }
        return dayToAdd;
    }
}
