package org.hr.controller;

import org.hr.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 假期管理
 */
@RestController
public class HolidayController {
    @Autowired
    HolidayService holidayService;


}
