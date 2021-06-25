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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author andres
 */
public class ColombiaCalendar extends GregorianCalendar{
    
    private static final TimeZone colombiaTimezone = TimeZone.getTimeZone("GMT-5:00");
    private static final Locale colombiaLocale = new Locale("es","co");
    private volatile Holiday holiday;
    
    public ColombiaCalendar(){
        super(colombiaTimezone, colombiaLocale);
    }
    
    public boolean isHoliday(){
        
        this.holiday = ColombiaHolidayCalculator.searchHoliday(this);
        
        return holiday != null;
    }
    
    public boolean isSunday(){
        return get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY;
    }
    
    public String getHolidayName(){
        
        if(this.isHoliday()){
            return this.holiday.getHolidayName();
        }
        
        return getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, colombiaLocale);
    }
    
}
