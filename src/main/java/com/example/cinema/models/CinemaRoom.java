package com.example.cinema.models;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Component
public class CinemaRoom {

    private final int totalRows;
    private final int totalColumns;
    private final List<Seat> seats;
    @Value("${statistics.password}")
    String chuj;

    public CinemaRoom(@Value("${cinema.cols}") int totalColumns, @Value("${cinema.rows}") int totalRows) {

        seats =  Collections.synchronizedList(new ArrayList<>());
        int standardSeatPrice = 8;
        int premiumSeatPrice = 10;

        for (int row = 1; row <= totalRows; row++) {
            int seatPrice = (row < 5) ? premiumSeatPrice : standardSeatPrice;

            for (int column = 1; column <= totalColumns; column++) {
                seats.add(new Seat(row, column, seatPrice, true));
            }
        }

        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
    }

    public Seat bookSeat(int row, int column) {
        if (row > totalRows || column > totalColumns || row < 1 || column < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The number of a row or a column is out of bounds!");
        }

        Seat bookedSeat = getAvailableSeats().stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The ticket has been already purchased!"));

        bookedSeat.setAvailable(false);

        return bookedSeat;
    }

    public List<Seat> getAvailableSeats() {
        System.out.println(chuj);
        return seats.stream()
                .filter(Seat::isAvailable)
                .toList();
    }

    public Seat returnTicket(String ticketUUIDString) {
        UUID ticketUUID = UUID.fromString(ticketUUIDString);
        Seat seatToRefund = seats.stream()
                .filter(seat -> !seat.isAvailable())
                .filter(seat -> seat.getTicketUUID().equals(ticketUUID))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong token!"));

        seatToRefund.setAvailable(true);
        return seatToRefund;
    }
}
