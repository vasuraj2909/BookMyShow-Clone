package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.models.Theatre;
import com.scaler.bookmyshow.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuditoriumController {
    private AuditoriumService auditoriumService;

    @Autowired
    public AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    public Theatre addAuditorium(Long theatreId, String name, int capacity) {
        return auditoriumService.addAuditorium(theatreId, name, capacity);
    }
}
