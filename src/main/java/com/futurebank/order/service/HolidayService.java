package com.futurebank.order.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author ben
 * @date 2024/5/27 22:08
 * 判断是否为节假日
 **/
@Service
public class HolidayService {
    /**
     * 判断是否是节假日
     * ture 节假日
     * false 非节假日
     *
     * @param currentDate
     * @return
     */
    public boolean isHoliday(LocalDate currentDate) {

        if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY)
            return true;

        if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY)
            return true;

        return false;
    }
}
