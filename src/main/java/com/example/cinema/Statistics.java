package com.example.cinema;

import com.example.cinema.models.CinemaRoom;
import com.example.cinema.models.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {

    private int currentIncome;
    @JsonProperty("number_of_available_seats")
    private int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int purchasedTickets;

    public Statistics(CinemaRoom cinemaRoom) {
        currentIncome = 0;
        availableSeats = cinemaRoom.getTotalColumns() * cinemaRoom.getTotalRows();
        purchasedTickets = 0;
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
