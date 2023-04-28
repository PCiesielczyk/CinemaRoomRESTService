package com.example.cinema;

import com.example.cinema.models.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Statistics {

    private int currentIncome;
    @JsonProperty("number_of_available_seats")
    private int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int purchasedTickets;

    public Statistics(int availableSeats) {
        this.currentIncome = 0;
        this.availableSeats = availableSeats;
        this.purchasedTickets = 0;
    }

    public void ticketSold(Seat seat) {
        currentIncome += seat.getPrice();
        availableSeats--;
        purchasedTickets++;
    }

    public void ticketReturned(Seat seat) {
        currentIncome -= seat.getPrice();
        availableSeats++;
        purchasedTickets--;
    }

}
