package org.hr.service;

import org.hr.mapper.HolidayMapper;
import org.hr.model.HolidayFlow;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HolidayService {
    @Autowired
    HolidayMapper holidayMapper;

    /**
     * 查询个人假期
     */
    public List<HolidayFlow> getUserHoliday(String userId) {
        return holidayMapper.getUserHoliday(userId);
    }
}
