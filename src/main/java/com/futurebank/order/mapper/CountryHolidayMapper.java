package com.futurebank.order.mapper;

import com.futurebank.order.entity.CountryHoliday;
import com.futurebank.order.mapper.base.BaseMapper;

import java.util.List;

public interface CountryHolidayMapper extends BaseMapper<CountryHoliday> {

    /**
     * 新增
     * @date 2025/01/10
     **/
    int insert(CountryHoliday countryHoliday);

    /**
     * 根据年份和国家查询
     * @date 2025/01/10
     **/
    int deleteByYearAndCountry(CountryHoliday countryHoliday);

    /**
     * 根据年份查询
     * @date 2025/01/10
     **/
    List<CountryHoliday> query(CountryHoliday countryHoliday);
}
