package com.example.holidays;


import com.example.holidays.controller.HolidaysController;
import com.example.holidays.model.Holiday;
import com.example.holidays.repository.HolidayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class HolidayControllerTest {

    @Mock
    private HolidayRepository holidayRepository;

    @InjectMocks
    private HolidaysController holidayController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testGetAllHolidays() {
//        Holiday holiday1 = new Holiday();
//        holiday1.setId(1L);
//        holiday1.setCountry("Country1");
//        holiday1.setName("Holiday1");
//        holiday1.setDate("2024-01-01");
//
//        Holiday holiday2 = new Holiday();
//        holiday2.setId(2L);
//        holiday2.setCountry("Country2");
//        holiday2.setName("Holiday2");
//        holiday2.setDate("2024-02-01");
//
//        List<Holiday> holidays = Arrays.asList(holiday1, holiday2);
//
//        when(holidayRepository.findAll()).thenReturn(holidays);
//
//        List<Holiday> result = holidayController.getAllHolidays();
//
//        assertEquals(2, result.size());
//        assertEquals("Country1", result.get(0).getCountry());
//        assertEquals("Holiday2", result.get(1).getName());
//    }

    @Test
    public void testCreateHoliday() {
        Holiday holiday = new Holiday();
        holiday.setCountry("Country1");
        holiday.setName("Holiday1");
        holiday.setDate("2024-01-01");

        when(holidayRepository.save(any(Holiday.class))).thenReturn(holiday);

        ResponseEntity<Holiday> response = holidayController.createHoliday(holiday);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Country1", response.getBody().getCountry());
    }

    @Test
    public void testUpdateHoliday_Success() {
        Long holidayId = 1L;
        Holiday existingHoliday = new Holiday();
        existingHoliday.setId(holidayId);
        existingHoliday.setCountry("Country1");
        existingHoliday.setName("Holiday1");
        existingHoliday.setDate("2024-01-01");

        Holiday updatedHoliday = new Holiday();
        updatedHoliday.setCountry("Country2");
        updatedHoliday.setName("Holiday2");
        updatedHoliday.setDate("2024-02-01");

        when(holidayRepository.findById(anyLong())).thenReturn(Optional.of(existingHoliday));
        when(holidayRepository.save(any(Holiday.class))).thenReturn(updatedHoliday);

        ResponseEntity<Holiday> response = holidayController.updateHoliday(holidayId, updatedHoliday);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Country2", response.getBody().getCountry());
        assertEquals("Holiday2", response.getBody().getName());
    }

    @Test
    public void testUpdateHoliday_NotFound() {
        Long holidayId = 1L;
        Holiday updatedHoliday = new Holiday();
        updatedHoliday.setCountry("Country2");
        updatedHoliday.setName("Holiday2");
        updatedHoliday.setDate("2024-02-01");

        when(holidayRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Holiday> response = holidayController.updateHoliday(holidayId, updatedHoliday);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
