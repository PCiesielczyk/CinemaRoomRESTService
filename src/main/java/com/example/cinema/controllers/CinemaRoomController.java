package com.example.cinema.controllers;

import com.example.cinema.CinemaRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaRoomController {
    public static final int TOTAL_ROWS = 9;
    public static final int TOTAL_COLUMNS = 9;
    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return new CinemaRoom(TOTAL_ROWS, TOTAL_COLUMNS);
    }
}
