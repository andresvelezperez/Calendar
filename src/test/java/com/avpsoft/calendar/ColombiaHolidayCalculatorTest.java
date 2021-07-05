/*
 * Copyright (C) 2014 andres
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.avpsoft.calendar;

import static com.avpsoft.calendar.Util.createDefaultCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author andres
 */
public class ColombiaHolidayCalculatorTest {

    @Test
    public void Holidays2012() {

        int year = 2012;

        ColombiaHolidayCalculator colombiaHoliday = new ColombiaHolidayCalculator(new Locale("es"));
        List<Holiday> holidays = colombiaHoliday.getHolidaysByYear(year);
        List<Calendar> calendar = new ArrayList<>();
        for (Holiday holiday : holidays) {
            calendar.add(holiday.getHoliday());
        }

        Assert.assertEquals("1 Enero", true, calendar.contains(createDefaultCalendar(year, Calendar.JANUARY, 1)));
        Assert.assertEquals("9 Enero", true, calendar.contains(createDefaultCalendar(year, Calendar.JANUARY, 9)));
        Assert.assertEquals("19 Marzo", true, calendar.contains(createDefaultCalendar(year, Calendar.MARCH, 19)));
        Assert.assertEquals("5 Abril", true, calendar.contains(createDefaultCalendar(year, Calendar.APRIL, 5)));
        Assert.assertEquals("6 Abril", true, calendar.contains(createDefaultCalendar(year, Calendar.APRIL, 6)));
        Assert.assertEquals("1 Mayo", true, calendar.contains(createDefaultCalendar(year, Calendar.MAY, 1)));
        Assert.assertEquals("21 Mayo", true, calendar.contains(createDefaultCalendar(year, Calendar.MAY, 21)));
        Assert.assertEquals("11 Junio", true, calendar.contains(createDefaultCalendar(year, Calendar.JUNE, 11)));
        Assert.assertEquals("18 Junio", true, calendar.contains(createDefaultCalendar(year, Calendar.JUNE, 18)));
        Assert.assertEquals("2 Julio", true, calendar.contains(createDefaultCalendar(year, Calendar.JULY, 2)));
        Assert.assertEquals("20 Julio", true, calendar.contains(createDefaultCalendar(year, Calendar.JULY, 20)));
        Assert.assertEquals("7 Agosto", true, calendar.contains(createDefaultCalendar(year, Calendar.AUGUST, 7)));
        Assert.assertEquals("20 Agosto", true, calendar.contains(createDefaultCalendar(year, Calendar.AUGUST, 20)));
        Assert.assertEquals("15 Octubre", true, calendar.contains(createDefaultCalendar(year, Calendar.OCTOBER, 15)));
        Assert.assertEquals("5 Noviembre", true, calendar.contains(createDefaultCalendar(year, Calendar.NOVEMBER, 5)));
        Assert.assertEquals("12 Noviembre", true, calendar.contains(createDefaultCalendar(year, Calendar.NOVEMBER, 12)));
        Assert.assertEquals("11 Noviembre", false, calendar.contains(createDefaultCalendar(year, Calendar.NOVEMBER, 11)));
        Assert.assertEquals("8 Diciembre", true, calendar.contains(createDefaultCalendar(year, Calendar.DECEMBER, 8)));
        Assert.assertEquals("25 Diciembre", true, calendar.contains(createDefaultCalendar(year, Calendar.DECEMBER, 8)));
    }

    @Test
    public void Holidayi18nTest() {

        int year = 2012;
        ColombiaHolidayCalculator colombiaHoliday = new ColombiaHolidayCalculator(new Locale("fr"));
        List<Holiday> holidays = colombiaHoliday.getHolidaysByYear(year);
        Holiday holiday = holidays.get(4);
        Assert.assertEquals("6 Abril", holiday.getHolidayName(), "Viernes Santo");
        
        colombiaHoliday = new ColombiaHolidayCalculator(new Locale("en"));
        holidays = colombiaHoliday.getHolidaysByYear(year);
        holiday = holidays.get(4);
        Assert.assertEquals("6 Abril", holiday.getHolidayName(), "Holy Friday");
    }

}
