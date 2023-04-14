package com.example.cinema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaRoomController {
    CinemaRoom cinemaRoom;

    @Autowired
    public CinemaRoomController(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public String buyTicket(@RequestBody Seat seat) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(cinemaRoom.bookSeat(seat.getRow(), seat.getColumn()));
    }
}
