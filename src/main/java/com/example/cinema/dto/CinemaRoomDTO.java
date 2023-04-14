package com.example.cinema.dto;

import com.example.cinema.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CinemaRoomDTO {

    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;
}
