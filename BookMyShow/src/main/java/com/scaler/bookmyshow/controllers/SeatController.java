package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.models.SeatType;
import com.scaler.bookmyshow.repositories.SeatRepository;
import com.scaler.bookmyshow.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class SeatController {
    private SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    public void addSeats(Long auditoriumId, Map<SeatType, Integer> seatCount) {
        seatService.addSeats(auditoriumId, seatCount);
    }
}
