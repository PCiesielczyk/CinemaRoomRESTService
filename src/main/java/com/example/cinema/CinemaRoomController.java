package com.example.cinema;

import com.example.cinema.dto.BookedSeatDTO;
import com.example.cinema.dto.CinemaRoomDTO;
import com.example.cinema.dto.DTOMapper;
import com.example.cinema.models.CinemaRoom;
import com.example.cinema.models.Seat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CinemaRoomController {
    CinemaRoom cinemaRoom;

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
        ObjectMapper mapper = new ObjectMapper();
        Seat bookedSeat = cinemaRoom.bookSeat(seat.getRow(), seat.getColumn());
        bookedSeat.generateUUID();
        return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(DTOMapper.bookedSeatToDTO(bookedSeat));
    }

    @PostMapping("/return")
    public String returnTicket(@RequestBody JsonNode requestBody) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String token = requestBody.get("token").asText();
        Seat returnedSeat = cinemaRoom.returnTicket(token);
        return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(DTOMapper.refundSeatToDTO(returnedSeat));
    }
}
