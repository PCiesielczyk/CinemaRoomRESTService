package com.example.cinema;

import java.util.Objects;

public class Seat {

    private final int row;
    private final int column;
    private final int price;
    private boolean isAvailable;

    public Seat(int row, int column, int price, boolean isAvailable) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

}
