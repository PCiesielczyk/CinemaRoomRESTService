package com.example.cinema.dto;

import com.example.cinema.models.CinemaRoom;
import com.example.cinema.models.Seat;

public class DTOMapper {
    public static CinemaRoomDTO cinemaRoomToDTO(CinemaRoom cinemaRoom) {
        return new CinemaRoomDTO(cinemaRoom.getTotalRows(),
                cinemaRoom.getTotalColumns(),
                cinemaRoom.getAvailableSeats());
    }

    public static BookedSeatDTO bookedSeatToDTO(Seat seat) {
        return new BookedSeatDTO(seat.getTicketUUID(), seat);
    }

    public static RefundSeatDTO refundSeatToDTO(Seat seat) {
        return new RefundSeatDTO(seat);
    }
}
