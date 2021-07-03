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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author andres
 */
public final class ColombiaHolidayCalculator {

    private static ColombiaHolidayCalculator colombiaHoliday;// = new ColombiaHolidayCalculator();
    private static Map<Integer, List<Holiday>> holidayMap = new HashMap<Integer, List<Holiday>>();
    private final Locale locale;

    protected ColombiaHolidayCalculator(Locale locale) {
        this.locale = locale;
    }

    public static ColombiaHolidayCalculator getInstance(Locale locale) {
        if (colombiaHoliday == null) {
            colombiaHoliday = new ColombiaHolidayCalculator(locale);
        }
        return colombiaHoliday;
    }

    public List<Holiday> getHolidaysByYear(int year) {
        return getHolidays(year);
    }

    private List<Holiday> getHolidays(int year) {

        List<Holiday> holidays = new ArrayList<>();
        final Calendar easterDay = Computus.AnonymousGregorianAlgorithm(year);

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("i18nHolidays", locale);
        } catch (MissingResourceException ignore) {
            bundle = ResourceBundle.getBundle("i18nHolidays", new Locale("es"));
        }

        holidays.add(new ColombiaHoliday(bundle.getString("holiday_new_year"), year, Calendar.JANUARY, 1, false));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_three_kings"), year, Calendar.JANUARY, 6, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_saint_joseph"), year, Calendar.MARCH, 19, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_holy_thursday"), easterDay, -3, false));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_holy_friday"), easterDay, -2, false));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_labor"), year, Calendar.MAY, 1, false));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_ascension_of_jesus"), easterDay, 39, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_corpus_christi"), easterDay, 60, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_sacred_heart_of_jesus"), easterDay, 68, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_saint_peter_and_paul"), year, Calendar.JUNE, 29, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_cry_of_independence"), year, Calendar.JULY, 20, false));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_boyaca_batttle"), year, Calendar.AUGUST, 7, false));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_assumptio_of_the_virgin"), year, Calendar.AUGUST, 15, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_day_of_race"), year, Calendar.OCTOBER, 12, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_all_saints"), year, Calendar.NOVEMBER, 1, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_independence_of_cartagena"), year, Calendar.NOVEMBER, 11, true));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_immaculate_conception"), year, Calendar.DECEMBER, 8, false));
        holidays.add(new ColombiaHoliday(bundle.getString("holiday_christmas"), year, Calendar.DECEMBER, 25, false));

        return holidays;
    }

    public static Holiday searchHoliday(Calendar calendar, Locale locale) {

        List<Holiday> holidaysByYear = null;
        int year = calendar.get(Calendar.YEAR);

        if (!holidayMap.containsKey(year)) {
            holidaysByYear = getInstance(locale).getHolidaysByYear(year);
            holidayMap.put(year, holidaysByYear);
        } else {
            holidaysByYear = holidayMap.get(year);
        }

        return searchHoliday(calendar, holidaysByYear);
    }

    public static Holiday searchHoliday(Calendar calendar, List<Holiday> holidays) {

        Calendar clone = (Calendar) calendar.clone();
        clone.set(Calendar.HOUR, 0);
        clone.set(Calendar.MINUTE, 0);
        clone.set(Calendar.SECOND, 0);
        clone.set(Calendar.MILLISECOND, 0);

        for (Holiday holiday : holidays) {
            if (clone.compareTo(holiday.getHoliday()) == 0) {
                return holiday;
            }
        }
        return null;
    }
}
