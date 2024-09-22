package ru.neostudy.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neostudy.calculator.config.properties.HolidaysProperties;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductionCalendarService {

    private final HolidaysProperties properties;

    public boolean isWorkDay(LocalDate date) {
        return !properties.getHolidays().contains(date.toString()) && !isWeekend(date);
    }

    public boolean isWeekend(LocalDate date) {
        return (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    public long getPaidVacationDays(LocalDate beginOfVacation, LocalDate endOfVacation) {
        Predicate<LocalDate> paidDays = day -> isWorkDay(day) || isWeekend(day);
        return Stream.iterate(beginOfVacation, nextDate -> nextDate.plusDays(1))
                .limit(ChronoUnit.DAYS.between(beginOfVacation, endOfVacation.plusDays(1)))
                .filter(paidDays)
                .count();
    }
}
