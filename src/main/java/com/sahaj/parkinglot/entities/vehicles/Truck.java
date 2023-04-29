package com.sahaj.parkinglot.entities.vehicles;

import com.sahaj.parkinglot.entities.Vehicle;

import java.util.stream.Stream;

public class Truck implements Vehicle {
    private final String type = "truck";
    private String model;

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
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }
    @Override
    public String toString() {
        return getType();
    }
}
