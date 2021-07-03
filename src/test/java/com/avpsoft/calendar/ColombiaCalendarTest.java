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

import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author andres
 */
public class ColombiaCalendarTest {
    
     @Test
    public void Holiday(){
        
        ColombiaCalendar fechaHoliday = new ColombiaCalendar();
        fechaHoliday.set(2015, Calendar.JANUARY, 12);
        
        Assert.assertTrue("Es Festivo 2015-01-12", fechaHoliday.isHoliday());
        
    }
    
    @Test
    public void NoHoliday(){
        
        ColombiaCalendar fechaHoliday = new ColombiaCalendar();
        fechaHoliday.set(2015, Calendar.JANUARY, 13);
        
        Assert.assertFalse("No es Festivo 2015-01-13", fechaHoliday.isHoliday());
        
    }
    
}
