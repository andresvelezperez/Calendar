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
import java.util.Calendar;

/**
 *
 * @author andres
 */
public class Computus {

    /**
     * Computus (Latin for "computation") is the calculation of the date of
     * Easter in terms of, first, the Julian and, later, the Gregorian calendar.
     *
     * @see http://en.wikipedia.org/wiki/Computus
     *
     * @param year
     * @return
     */
    public static Calendar AnonymousGregorianAlgorithm(int year) {
        int a = (int) year % 19;
        int b = (int) Math.floor(year / 100);
        int c = (int) year % 100;
        int d = (int) Math.floor(b / 4);
        int e = (int) b % 4;
        int f = (int) Math.floor((b + 8) / 25);
        int g = (int) Math.floor((b - f + 1) / 3);
        int h = (int) (19 * a + b - d - g + 15) % 30;
        int i = (int) Math.floor(c / 4);
        int k = (int) c % 4;
        int l = (int) (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (int) Math.floor((a + 11 * h + 22 * l) / 451);
        int n = h + l - 7 * m + 114;
        int mes = n / 31;
        int dia = 1 + n % 31;
        Calendar calendar = createDefaultCalendar(year, mes - 1, dia);
        return calendar;
    }

}
