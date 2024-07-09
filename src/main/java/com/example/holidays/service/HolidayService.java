package com.example.holidays.service;

import com.example.holidays.model.Holiday;
import com.example.holidays.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    public Holiday addHoliday(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

    public Holiday updateHoliday(Long id, Holiday holiday) {
        holiday.setId(id);
        return holidayRepository.save(holiday);
    }

    public List<Holiday> getHolidaysByCountry(String country) {
        return holidayRepository.findByCountry(country);
    }
}
