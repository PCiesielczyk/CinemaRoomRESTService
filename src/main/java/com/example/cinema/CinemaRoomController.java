package com.example.cinema;

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
    public Seat buyTicket(@RequestBody Seat seat) {
        return cinemaRoom.bookSeat(seat.getRow(), seat.getColumn());
    }
}
