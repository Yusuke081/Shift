package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("calendar")
public class CalendarController {

    @RequestMapping
    public String today(Model model) {
        LocalDate today = new LocalDate();
        int year = today.getYear();
        int month = today.getMonthOfYear();
        return month(year, month, model);
    }

    @RequestMapping(value = "month")
    public String month(@RequestParam("year") int year,
            @RequestParam("month") int month, Model model) {
        LocalDate firstDayOfMonth = new LocalDate(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.dayOfMonth()
                .withMaximumValue();

        LocalDate firstDayOfCalendar = firstDayOfMonth.dayOfWeek()
                .withMinimumValue();
        LocalDate lastDayOfCalendar = lastDayOfMonth.dayOfWeek()
                .withMaximumValue();

        List<List<LocalDate>> calendar = new ArrayList<List<LocalDate>>();
        List<LocalDate> weekList = null;
        for (int i = 0; i < 100; i++) {
            LocalDate d = firstDayOfCalendar.plusDays(i);
            if (d.isAfter(lastDayOfCalendar)) {
                break;
            }

            if (weekList == null) {
                weekList = new ArrayList<LocalDate>();
                calendar.add(weekList);
            }

            if (d.isBefore(firstDayOfMonth) || d.isAfter(lastDayOfMonth)) {
                // skip if the day is not in this month
                weekList.add(null);
            } else {
                weekList.add(d);
            }

            int week = d.getDayOfWeek();
            if (week == DateTimeConstants.SUNDAY) {
                weekList = null;
            }
        }

        LocalDate nextMonth = firstDayOfMonth.plusMonths(1);
        LocalDate prevMonth = firstDayOfMonth.minusMonths(1);
        CalendarOutput output = new CalendarOutput();
        output.setCalendar(calendar);
        output.setFirstDayOfMonth(firstDayOfMonth);
        output.setYearOfNextMonth(nextMonth.getYear());
        output.setMonthOfNextMonth(nextMonth.getMonthOfYear());
        output.setYearOfPrevMonth(prevMonth.getYear());
        output.setMonthOfPrevMonth(prevMonth.getMonthOfYear());

        model.addAttribute("output", output);

        return "calendar";
    }
}