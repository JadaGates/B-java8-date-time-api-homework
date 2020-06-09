package com.thoughtworks.capability.gtb;

import java.time.LocalDate;
import java.time.Period;

/**
 * 计算任意日期与下一个劳动节相差多少天
 *
 * @author itutry
 * @create 2020-05-15_16:33
 */
public class Practice1 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, 5, 1);
        boolean isAhead = isTheYearAheadOfNow(date);
        if (isAhead) {
            System.out.println("The year you choose is ahead the real year.");
        } else {
            long result = getDaysBetweenNextLaborDay(date);
            outputTheResult(result);
        }
    }

    public static long getDaysBetweenNextLaborDay(LocalDate date) {
        long daysBetween;
        int year = date.getYear();
        LocalDate thisYearLaborDay = LocalDate.of(year, 5, 1);

        boolean isBefore = date.isBefore(thisYearLaborDay);

        if (isBefore) {
            daysBetween = thisYearLaborDay.toEpochDay() - date.toEpochDay();
        } else {
            LocalDate nextYearLaborDay = thisYearLaborDay.plusYears(1);
            daysBetween = nextYearLaborDay.toEpochDay() - date.toEpochDay();
        }
        return daysBetween;
    }

    private static boolean isTheYearAheadOfNow(LocalDate date) {
        int year = date.getYear();
        int thisYear = LocalDate.now().getYear();
        if (year < thisYear) {
            return true;
        }
        return false;
    }

    private static void outputTheResult(long result) {
        if (result != 0) {
            System.out.println("There are still " + result + " to the next Labor day. Back to work! NOW!!!");
        } else {
            System.out.println("Congratulations, Today is the Labor Day!");
        }
    }
}
