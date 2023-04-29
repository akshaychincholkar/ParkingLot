package com.sahaj.parkinglot.entities.vehicles;

import com.sahaj.parkinglot.entities.Vehicle;

import java.util.stream.Stream;

public class Car implements Vehicle {
    private final String type = "car";

    @Override
    public int[] getFees(String feeModel) {
        int[] fees = Stream.of((setConfig().getProperty(type+"."+feeModel+"."+"fees")).split(",")).mapToInt(Integer::parseInt).toArray();
        return fees;
    }
    @Override
    public int[] getTimeFrames(String feeModel){
        int[] timeFrames = Stream.of((setConfig().getProperty(type+"."+feeModel+"."+"timeframes")).split(",")).mapToInt(Integer::parseInt).toArray();
        return timeFrames;
    }

    public String getType(){return type;}

    @Override
    public String getModel() {
        return null;
    }

    @Override
    public void setModel(String model) {

    }
    @Override
    public String toString() {
        return getType();
    }
}
