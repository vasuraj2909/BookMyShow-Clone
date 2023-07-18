package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.Auditorium;
import com.scaler.bookmyshow.models.Seat;
import com.scaler.bookmyshow.models.SeatType;
import com.scaler.bookmyshow.repositories.AuditoriumRepository;
import com.scaler.bookmyshow.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SeatService {

    private SeatRepository seatRepository;
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository, AuditoriumRepository auditoriumRepository) {
        this.seatRepository = seatRepository;
        this.auditoriumRepository = auditoriumRepository;
    }

    public void addSeats(Long auditoriumId, Map<SeatType, Integer> seatCount) {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId).get();

        List<Seat> seats = new ArrayList<>();

        for (Map.Entry<SeatType, Integer> entry : seatCount.entrySet()) {
            for (int i = 0; i < entry.getValue(); ++i) {
                Seat seat = new Seat();
                seat.setSeatType(entry.getKey());
                seat.setSeatNumber(entry.getKey().toString() + Integer.toString(i + 1));
                seats.add(seat);
            }
        }

        List<Seat> savedSeats = seatRepository.saveAll(seats);

        auditorium.setSeats(savedSeats);

        auditoriumRepository.save(auditorium);
    }
}
