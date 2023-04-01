package com.example.cinema;

public class Seat {

    private final int row;
    private final int column;
    private boolean isAvailable;

    public Seat(int row, int column, boolean isAvailable) {
        this.row = row;
        this.column = column;
        this.isAvailable = isAvailable;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
