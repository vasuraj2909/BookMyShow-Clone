package com.scaler.bookmyshow;

import com.scaler.bookmyshow.controllers.*;
import com.scaler.bookmyshow.dtos.CreateUserRequestDto;
import com.scaler.bookmyshow.dtos.CreateUserResponseDto;
import com.scaler.bookmyshow.models.Language;
import com.scaler.bookmyshow.models.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {

    private UserController userController;
    private CityController cityController;
    private TheatreController theatreController;
    private AuditoriumController auditoriumController;
    private SeatController seatController;
    private ShowController showController;
    private TicketController ticketController;

    @Autowired
    public BookMyShowApplication(UserController userController, CityController cityController, TheatreController theatreController, AuditoriumController auditoriumController, SeatController seatController, ShowController showController, TicketController ticketController) {
        this.userController = userController;
        this.cityController = cityController;
        this.theatreController = theatreController;
        this.auditoriumController = auditoriumController;
        this.seatController = seatController;
        this.showController = showController;
        this.ticketController = ticketController;
    }


    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
        createUserRequestDto.setEmail("vasu@scaler.com");
        CreateUserResponseDto createUserResponseDto =  userController.createUser(createUserRequestDto);
        System.out.println(createUserResponseDto.getUser().getEmail());

        this.cityController.addCity("Chandigarh");

        this.theatreController.createTheatre("PVR", "ABC Road, Chandigarh", 1L);

        this.auditoriumController.addAuditorium(1L, "Audi 1", 123);

        Map<SeatType, Integer> seatsForAudi = new HashMap<>();
        seatsForAudi.put(SeatType.VIP, 20);
        seatsForAudi.put(SeatType.GOLD, 100);

        this.seatController.addSeats(1L, seatsForAudi);

        this.showController.createShow(0L, new Date(), new Date(), 1L, null, Language.ENGLISH);

        this.ticketController.bookTicket(3L, List.of(58L, 59L, 60L), 1L);
    }
}
