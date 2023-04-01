package com.example.cinema;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;

public class CinemaRoom {

    private final int totalRows;
    private final int totalColumns;
    private final ArrayList<Seat> seats;

    public CinemaRoom(@Value("${cinema.cols}") int totalColumns, @Value("${cinema.rows}") int totalRows) {

        seats = new ArrayList<>();

        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                seats.add(new Seat(row, column, true)); // create and add a new Seat object to the list
            }
        }

        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}
