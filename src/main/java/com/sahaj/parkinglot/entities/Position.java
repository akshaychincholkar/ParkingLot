package com.sahaj.parkinglot.entities;

public class Position {
    private Vehicle vehicle;
    public Position(final Vehicle vehicle){this.vehicle = vehicle;}
    public Vehicle getVehicle(){
        return vehicle;
    }

    @Override
    public String toString() {
        return "";
    }
}
