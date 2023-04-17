package com.example.cinema.dto;

import com.example.cinema.models.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BookedSeatDTO {

    @JsonProperty("token")
    private UUID ticketUUID;
    @JsonProperty("ticket")
    private Seat seat;
}
