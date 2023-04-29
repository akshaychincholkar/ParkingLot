package com.sahaj.parkinglot;

import com.sahaj.parkinglot.entities.Position;
import com.sahaj.parkinglot.entities.Receipt;
import com.sahaj.parkinglot.entities.Ticket;
import com.sahaj.parkinglot.entities.vehicles.Bike;
import com.sahaj.parkinglot.entities.vehicles.Car;
import com.sahaj.parkinglot.entities.vehicles.Truck;
import com.sahaj.parkinglot.entities.Vehicle;
import com.sahaj.parkinglot.exceptions.NoPositionFound;
import com.sahaj.parkinglot.models.FeeModel;
import com.sahaj.parkinglot.models.impl.MallFeeModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;
public class Parking {
    private final List<Position> positions;
    private Integer parkingNumber = 1;
    private Integer receptNumber = 1;
    // Default fee model is mall model
    private FeeModel feeModel = new MallFeeModel();
    public Parking(List<Position> positions,FeeModel feeModel) {
        this.positions = positions;
        this.feeModel = feeModel;
    }
    private Parking(List<Position> positions){
        this.positions = positions;
    }
    public int getPositionsCount(){return this.positions.size();}
    public Ticket park(Vehicle vehicle,Date entryTime){
        Position assigned = null;
        vehicle.setModel(feeModel.getModel());
        for (Position position : positions) {
            if (position.getVehicle().getType().equals(vehicle.getType())) {
                assigned = position;
            }
        }
        if (assigned != null) positions.remove(assigned);
        else throw new NoPositionFound(vehicle);
        return new Ticket(parkingNumber++, assigned, vehicle,entryTime);
    }
    public Receipt unPark(final Ticket ticket,Date exitTime) {
        ticket.setExitTime(exitTime);
        positions.add(ticket.getPosition());
        return feeModel.calculateFare(receptNumber++,ticket);
    }
//    private Receipt calculateFare(Ticket ticket){
//        System.out.println(ticket.getVehicle().calculateFare());
//        return null;
//    }
    public static class Builder {
        public static final int DEFAULT_POSITIONS = 0;
        private Integer SMALL_POSITIONS = DEFAULT_POSITIONS;
        private Integer MEDIUM_POSITIONS = DEFAULT_POSITIONS;
        private Integer LARGE_POSITIONS = DEFAULT_POSITIONS;

        private FeeModel feeModel = new MallFeeModel();
        public Builder smallPositions(Integer count) {
            this.SMALL_POSITIONS = count;
            return this;
        }

        public Builder mediumPositions(Integer count) {
            this.MEDIUM_POSITIONS = count;
            return this;
        }

        public Builder largePositions(Integer count) {
            this.LARGE_POSITIONS = count;
            return this;
        }
        public Builder model(FeeModel feeModel) {
            this.feeModel = feeModel;
            return this;
        }
        public Parking build() {
            List<Position> positions = new ArrayList<>();
            positions.addAll(IntStream.range(0, this.SMALL_POSITIONS).mapToObj(i -> new Position(new Bike())).collect(toSet()));
            positions.addAll(IntStream.range(0, this.MEDIUM_POSITIONS).mapToObj(i -> new Position(new Car())).collect(toSet()));
            positions.addAll(IntStream.range(0, this.LARGE_POSITIONS).mapToObj(i -> new Position(new Truck())).collect(toSet()));
            return new Parking(positions,feeModel);
        }

    }
}
