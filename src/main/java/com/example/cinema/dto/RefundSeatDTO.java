package com.example.cinema.dto;

import com.example.cinema.models.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RefundSeatDTO {
    @JsonProperty("returned_ticket")
    private Seat seat;
}
