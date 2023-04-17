package com.example.cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Seat {

    private final int row;
    private final int column;
    private final int price;
    @JsonIgnore
    private boolean isAvailable;
    @JsonIgnore
    private UUID ticketUUID;

    public Seat(int row, int column, int price, boolean isAvailable) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public void generateUUID() {
        this.ticketUUID = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

}
