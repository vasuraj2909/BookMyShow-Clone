package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.Auditorium;
import com.scaler.bookmyshow.models.Theatre;
import com.scaler.bookmyshow.repositories.AuditoriumRepository;
import com.scaler.bookmyshow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumService {
    private AuditoriumRepository auditoriumRepository;
    private TheatreRepository theatreRepository;

    @Autowired
    public AuditoriumService(AuditoriumRepository auditoriumRepository, TheatreRepository theatreRepository) {
        this.auditoriumRepository = auditoriumRepository;
        this.theatreRepository = theatreRepository;
    }

    public Theatre addAuditorium(Long theatreId, String name, int capacity) {

        Theatre theatre = theatreRepository.findById(theatreId).get();

        Auditorium auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium.setCapacity(capacity);
        auditorium.setTheatre(theatre);

        Auditorium savedAuditorium = auditoriumRepository.save(auditorium);

        theatre.getAuditoriums().add(savedAuditorium);

        return theatreRepository.save(theatre);
    }
}
