package com.sahaj.parkinglot;

import com.sahaj.parkinglot.entities.Receipt;
import com.sahaj.parkinglot.entities.Ticket;
import com.sahaj.parkinglot.entities.vehicles.Bike;
import com.sahaj.parkinglot.entities.vehicles.Car;
import com.sahaj.parkinglot.entities.vehicles.Truck;
import com.sahaj.parkinglot.entities.Vehicle;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Building the parking lot with model : Mall (default)
        Parking.Builder parkingLotBuilder = new Parking.Builder().largePositions(2).mediumPositions(5).smallPositions(10);
//        Stadium model
//        Parking.Builder parkingLotBuilder = new Parking.Builder().withLargeSpots(2).withMediumSpots(5).model(new StadiumFeeModel()).withSmallSpots(10);
//        Airport model
//        Parking.Builder parkingLotBuilder = new Parking.Builder().withLargeSpots(2).withMediumSpots(5).model(new AirportFeeModel()).withSmallSpots(10);

        // gathering the lots
        Parking lot = parkingLotBuilder.build();

        // vehicles creation
        Vehicle bike = new Bike();
        Vehicle car = new Car();
        Vehicle truck = new Truck();

        // parking the vehicles to the corresponding slots with entry log
        Ticket ticket2 = lot.park(bike,new Date("29-May-2022 14:44:07"));
        Ticket ticket = lot.park(car,new Date("29-May-2022 00:00:00"));
        Ticket ticket3 = lot.park(truck,new Date("29-May-2022 00:00:00"));

        // unparking the vehicles with exit timestamp
        Receipt receipt2 = lot.unPark(ticket2,new Date("29-May-2022 15:40:07"));
        Receipt receipt = lot.unPark(ticket,new Date("1-Jun-2022 01:00:00"));
        Receipt receipt3 = lot.unPark(ticket3,new Date("29-May-2022 1:59:00"));

        // Receipt
        System.out.println(receipt2);
        System.out.println(receipt);
        System.out.println(receipt3);
    }
}
