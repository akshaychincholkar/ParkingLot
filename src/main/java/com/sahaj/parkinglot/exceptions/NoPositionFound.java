package com.sahaj.parkinglot.exceptions;

import com.sahaj.parkinglot.entities.Vehicle;

public class NoPositionFound extends RuntimeException{
    public NoPositionFound(Vehicle vehicle) {
        super("No position available for "+vehicle);
    }
}
