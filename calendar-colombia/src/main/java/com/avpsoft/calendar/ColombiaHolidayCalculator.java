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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andres
 */
public final class ColombiaHolidayCalculator {

    private static ColombiaHolidayCalculator colombiaHoliday = new ColombiaHolidayCalculator();
    private static Map<Integer,List<Holiday>> holidayMap = new HashMap<Integer,List<Holiday>>();

    protected ColombiaHolidayCalculator() {
        
    }

    public static ColombiaHolidayCalculator getInstance() {
        return colombiaHoliday;
    }
    
    public List<Holiday> getHolidaysByYear(int year){
        return getHolidays(year);
    }

    private List<Holiday> getHolidays(int year) {

        List<Holiday> holidays = new ArrayList<Holiday>();
        
        final Calendar easterDay = Computus.AnonymousGregorianAlgorithm(year);

        holidays.add(new ColombiaHoliday("Día de Año Nuevo", year, Calendar.JANUARY, 1, false) );
        holidays.add(new ColombiaHoliday("Día de los Reyes Magos", year, Calendar.JANUARY, 6,true));
        holidays.add(new ColombiaHoliday("San Jose",year, Calendar.MARCH, 19,true));
        holidays.add(new ColombiaHoliday("Jueves Santo",easterDay, -3,false));
        holidays.add(new ColombiaHoliday("Viernes Santo",easterDay, -2, false));
        holidays.add(new ColombiaHoliday("Día del trabajo",year, Calendar.MAY, 1,false));
        holidays.add(new ColombiaHoliday("Ascensión de Jesús",easterDay,39,true));
        holidays.add(new ColombiaHoliday("Corpus Christi",easterDay,60,true));
        holidays.add(new ColombiaHoliday("Sagrado Corazón de Jesús",easterDay,68,true));
        holidays.add(new ColombiaHoliday("San Pedro y San Pablo",year, Calendar.JUNE, 29,true));
        holidays.add(new ColombiaHoliday("Grito de Independencia",year, Calendar.JULY, 20,false));
        holidays.add(new ColombiaHoliday("Batalla de Boyacá",year, Calendar.AUGUST, 7,false));
        holidays.add(new ColombiaHoliday("Asunción de la Virgen",year, Calendar.AUGUST, 15,true));
        holidays.add(new ColombiaHoliday("Día de la Raza",year, Calendar.OCTOBER, 12,true));
        holidays.add(new ColombiaHoliday("Todos los Santos",year, Calendar.NOVEMBER, 1,true));
        holidays.add(new ColombiaHoliday("Independencia de Cartagena",year, Calendar.NOVEMBER, 11,true));
        holidays.add(new ColombiaHoliday("Inmaculada Concepción",year, Calendar.DECEMBER, 8,false));
        holidays.add(new ColombiaHoliday("Navidad (pascua)",year, Calendar.DECEMBER, 25,false));

        return holidays;
    }
    
    public static Holiday searchHoliday(Calendar calendar){
        
        List<Holiday> holidaysByYear = null;
        int year = calendar.get(Calendar.YEAR);
        
        if(!holidayMap.containsKey(year)){
            holidaysByYear = getInstance().getHolidaysByYear(year);
            holidayMap.put(year, holidaysByYear);
        }else{
            holidaysByYear = holidayMap.get(year);
        }
        
        return searchHoliday(calendar, holidaysByYear);
    }

    public static Holiday searchHoliday(Calendar calendar,List<Holiday> holidays){
        
        Calendar clone = (Calendar) calendar.clone();
        clone.set(Calendar.HOUR, 0);
        clone.set(Calendar.MINUTE, 0);
        clone.set(Calendar.SECOND, 0);
        clone.set(Calendar.MILLISECOND, 0);
        
        for(Holiday holiday : holidays){
            if(clone.compareTo(holiday.getHoliday())==0){
                return holiday;
            }
        }
        return null;
    }
}
