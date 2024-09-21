package ru.neostudy.calculator.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class ProductionCalendarService {

    private static final int CURRENT_YEAR = LocalDate.now().getYear();

    private static final Set<LocalDate> HOLIDAYS = new HashSet<>();

    static {
        // Russian public holidays for 2024
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 1, 1));  // New Year's Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 1, 2));  // New Year's Holiday
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 1, 3));  // New Year's Holiday
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 1, 4));  // New Year's Holiday
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 1, 5));  // New Year's Holiday
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 1, 8));  // New Year's Holiday
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 2, 23)); // Defender of the Fatherland Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 3, 8));  // International Women's Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 4, 30)); // Spring and Labor Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 5, 1));  // Spring and Labor Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 5, 9));  // Victory Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 5, 10));  // Victory Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 6, 12)); // Russia Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 11, 4)); // National Unity Day
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 12, 30)); // New Year's Eve
        HOLIDAYS.add(LocalDate.of(CURRENT_YEAR, 12, 31)); // New Year's Eve
    }

    public boolean isWorkDay(LocalDate date) {
        return !HOLIDAYS.contains(date) && !(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    public boolean isWeekend(LocalDate date) {
        return (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
    }


    public Long getPaidVacationDays(LocalDate beginOfVacation, LocalDate endOfVacation){
        Predicate<LocalDate> paidDays = day -> isWorkDay(day) || isWeekend(day);
        return  Stream.iterate(beginOfVacation, nextDate -> nextDate.plusDays(1)).limit(ChronoUnit.DAYS.between(beginOfVacation,endOfVacation.plusDays(1)))
                .filter(paidDays).count();


    }

}
