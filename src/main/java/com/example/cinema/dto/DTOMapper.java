package com.example.cinema.dto;

import com.example.cinema.CinemaRoom;

public class DTOMapper {
    public static CinemaRoomDTO cinemaRoomToDTO(CinemaRoom cinemaRoom) {
        return new CinemaRoomDTO(cinemaRoom.getTotalRows(),
                cinemaRoom.getTotalColumns(),
                cinemaRoom.getAvailableSeats());
    }
}
