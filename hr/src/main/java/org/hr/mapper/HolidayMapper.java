package org.hr.mapper;

import org.hr.model.Holiday;
import org.hr.model.HolidayFlow;

import java.util.List;

public interface HolidayMapper {

    public List<HolidayFlow> getUserHoliday(String userId);
}
