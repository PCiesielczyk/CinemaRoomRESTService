package com.example.cinema.models;

import com.example.cinema.Statistics;
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
    private final Statistics statistics;
    private final List<Seat> seats;
    @Value("${statistics.password}")
    private String statisticsPassword;

    public CinemaRoom(@Value("${cinema.cols}") int totalColumns, @Value("${cinema.rows}") int totalRows) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.seats =  Collections.synchronizedList(new ArrayList<>());
        int standardSeatPrice = 8;
        int premiumSeatPrice = 10;

        for (int row = 1; row <= totalRows; row++) {
            int seatPrice = (row < 5) ? premiumSeatPrice : standardSeatPrice;

            for (int column = 1; column <= totalColumns; column++) {
                seats.add(new Seat(row, column, seatPrice, true));
            }
        }
        this.statistics = new Statistics(totalColumns * totalColumns);
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
        statistics.ticketSold(bookedSeat);

        return bookedSeat;
    }

    public Seat returnTicket(String ticketUUIDString) {
        UUID ticketUUID = UUID.fromString(ticketUUIDString);
        Seat seatToRefund = seats.stream()
                .filter(seat -> !seat.isAvailable())
                .filter(seat -> seat.getTicketUUID().equals(ticketUUID))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong token!"));

        seatToRefund.setAvailable(true);
        statistics.ticketReturned(seatToRefund);
        return seatToRefund;
    }

    public List<Seat> getAvailableSeats() {
        return seats.stream()
                .filter(Seat::isAvailable)
                .toList();
    }

    public Statistics getStatistics(String password) {
        if (!statisticsPassword.equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is wrong!");
        }
        return statistics;
    }
}
