package com.example.holidays.controller;

import com.example.holidays.model.Holiday;
import com.example.holidays.repository.HolidayRepository;
import com.example.holidays.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/holidays")
public class HolidaysController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping("/addHoliday")
    public Holiday addHoliday(@RequestBody Holiday holiday) {
        return holidayService.addHoliday(holiday);
    }

    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidaysController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    // GET all holidays
    @GetMapping
    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    // POST create a new holiday
    @PostMapping
    public ResponseEntity<Holiday> createHoliday(@RequestBody Holiday holiday) {
        try {
            Holiday newHoliday = holidayRepository.save(holiday);
            return new ResponseEntity<>(newHoliday, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT update an existing holiday
    @PutMapping("/{id}")
    public ResponseEntity<Holiday> updateHoliday(@PathVariable("id") Long id, @RequestBody Holiday holiday) {
        Optional<Holiday> holidayData = holidayRepository.findById(id);

        if (holidayData.isPresent()) {
            Holiday existingHoliday = holidayData.get();
            existingHoliday.setCountry(holiday.getCountry());
            existingHoliday.setName(holiday.getName());
            existingHoliday.setDate(holiday.getDate());
            return new ResponseEntity<>(holidayRepository.save(existingHoliday), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
