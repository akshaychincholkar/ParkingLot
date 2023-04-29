package com.sahaj.parkinglot.entities;

import java.util.Date;

public class Ticket {
    private int id;
    private Position position;
    private Vehicle vehicle;
    private Date entryTime;
    private Date exitTime;
    private int totalHours;
    private int totalMins;
    private int fare;

    public Ticket(int id, Position position, Vehicle vehicle,Date entryTime) {
        this.id = id;
        this.position = position;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public int getTotalHours() {
        if(exitTime!=null && entryTime.getTime()<exitTime.getTime()) {
            final int MILLI_TO_HOUR = 1000 * 60 * 60;
            totalHours = (int) (exitTime.getTime() - entryTime.getTime()) / MILLI_TO_HOUR;
        }
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getTotalMins() {
        if(exitTime!=null && entryTime.getTime()<exitTime.getTime()) {
            final int MILLI_TO_MINS = 60 * 1000 ;
            totalMins = (int) (exitTime.getTime() - entryTime.getTime()) / MILLI_TO_MINS % 60 ;
        }
        return totalMins;
    }

    public void setTotalMins(int totalMins) {
        this.totalMins = totalMins;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "" +
                "\nvehicle=" + vehicle +
                "\nentryTime=" + entryTime +
                "\nexitTime=" + exitTime +
                '\n';
    }
}
