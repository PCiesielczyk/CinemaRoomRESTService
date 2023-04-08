package com.example.cinema.controllers;

import com.example.cinema.CinemaRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaRoomController {
    CinemaRoom cinemaRoom;

    public CinemaRoomController(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }
}
