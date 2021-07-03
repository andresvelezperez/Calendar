/*
 * Copyright (C) 2015 andres
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
public class ColombiaHolidayTest {

    @Test
    public void LeyEmiliani() {

        ColombiaHoliday holiday = new ColombiaHoliday("Test", 2012, Calendar.JANUARY, 6, true);
        Assert.assertEquals(9, holiday.getHoliday().get(Calendar.DATE));
    }

    @Test
    public void LeyEmilianiAscencionJesus2012() {

        int year = 2012;

        Calendar computus = Computus.AnonymousGregorianAlgorithm(year);
        Calendar originalDayAssensioJesus = new ColombiaHoliday(null, computus, 39, false).getHoliday();
        Assert.assertEquals("Ascension de Jesus", 17, originalDayAssensioJesus.get(Calendar.DATE));
        Calendar assensioJesus = new ColombiaHoliday(null, computus, 39, true).getHoliday();
        Assert.assertEquals("Ascension de Jesus", 21, assensioJesus.get(Calendar.DATE));
    }

    @Test
    public void SemanaSanta() {

        int year = 2012;

        Calendar computus = Computus.AnonymousGregorianAlgorithm(year);

        Assert.assertEquals("Domingo de Pascua", 8, computus.get(Calendar.DATE));

        Holiday juevesSanto = new ColombiaHoliday("Jueves Santo", computus, -3, false);
        Holiday viernesSanto = new ColombiaHoliday("Viernes Santo", computus, -2, false);

        Assert.assertEquals("Jueves Santo", 5, juevesSanto.getHoliday().get(Calendar.DATE));
        Assert.assertEquals("Viernes Santo", 6, viernesSanto.getHoliday().get(Calendar.DATE));
    }

    @Test
    public void LeyEmilianiAscencionJesus2010() {

        int year = 2010;

        Calendar computus = Computus.AnonymousGregorianAlgorithm(year);
        Calendar originalDayAssensioJesus = new ColombiaHoliday(null, computus, 39, false).getHoliday();
        Assert.assertEquals("Ascension de Jesus", 13, originalDayAssensioJesus.get(Calendar.DATE));
        Calendar assensioJesus = new ColombiaHoliday(null, computus, 39, true).getHoliday();
        Assert.assertEquals("Ascension de Jesus", 17, assensioJesus.get(Calendar.DATE));
    }

    @Test
    public void Holidays() {

        ColombiaHolidayCalculator colombiaHoliday = new ColombiaHolidayCalculator(new Locale("es"));
        List<Holiday> holidays = colombiaHoliday.getHolidaysByYear(2013);

        List<Calendar> calendar = new ArrayList<>();
        for (Holiday holiday : holidays) {
            calendar.add(holiday.getHoliday());
        }

        Assert.assertEquals("13 Mayo de 2013", true, calendar.contains(createDefaultCalendar(2013, Calendar.MAY, 13)));

        holidays = colombiaHoliday.getHolidaysByYear(2014);
        calendar.clear();
        for (Holiday holiday : holidays) {
            calendar.add(holiday.getHoliday());
        }
        Assert.assertEquals("13 Octubre de 2014", true, calendar.contains(createDefaultCalendar(2014, Calendar.OCTOBER, 13)));
    }

    @Test
    public void NoLeyEmiliani2012() {

        int year = 2012;

        final Calendar computus = Computus.AnonymousGregorianAlgorithm(year);

//        Mayo 17
        Calendar originalDayAssensioJesus = new ColombiaHoliday(null, computus, 39, false).getHoliday();
        Assert.assertEquals("Ascension de Jesus", 17, originalDayAssensioJesus.get(Calendar.DATE));
//        Junio 7
        Calendar originalDayCorpusChristi = new ColombiaHoliday(null, computus, 60, false).getHoliday();
        Assert.assertEquals("Corpus Christi", 7, originalDayCorpusChristi.get(Calendar.DATE));
//        Junio 15
        Calendar originalDaySagradoCorazonJesus = new ColombiaHoliday(null, computus, 68, false).getHoliday();
        Assert.assertEquals("Sagrado Corazon de Jesus", 15, originalDaySagradoCorazonJesus.get(Calendar.DATE));
    }

}
