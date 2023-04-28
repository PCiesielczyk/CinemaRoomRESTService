package com.example.cinema;

import com.example.cinema.dto.CinemaRoomDTO;
import com.example.cinema.dto.DTOMapper;
import com.example.cinema.handlers.RequestHandler;
import com.example.cinema.models.CinemaRoom;
import com.example.cinema.models.Seat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaRoomController {
    CinemaRoom cinemaRoom;
    private final RequestHandler requestHandler = new RequestHandler();

    @Autowired
    public CinemaRoomController(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    @GetMapping("/seats")
    public CinemaRoomDTO getCinemaRoom() {
        return DTOMapper.cinemaRoomToDTO(cinemaRoom);
    }

    @PostMapping("/purchase")
    public String buyTicket(@RequestBody Seat seat) throws JsonProcessingException {
        Seat bookedSeat = cinemaRoom.bookSeat(seat.getRow(), seat.getColumn());
        bookedSeat.generateUUID();
        return requestHandler.writeAsString(DTOMapper.bookedSeatToDTO(bookedSeat));
    }

    @PostMapping("/return")
    public String returnTicket(@RequestBody JsonNode requestBody) throws JsonProcessingException {
        String token = requestHandler.getValue(requestBody, "token");
        Seat returnedSeat = cinemaRoom.returnTicket(token);
        return requestHandler.writeAsString(DTOMapper.refundSeatToDTO(returnedSeat));
    }

    @PostMapping("/stats")
    public Statistics statistics(@RequestBody JsonNode requestBody) {
        String password = requestHandler.getValue(requestBody, "password");
        return cinemaRoom.getStatistics(password);
    }
}
