package com.sahaj.parkinglot;

import com.sahaj.parkinglot.entities.Receipt;
import com.sahaj.parkinglot.entities.Ticket;
import com.sahaj.parkinglot.entities.vehicles.Bike;
import com.sahaj.parkinglot.entities.vehicles.Car;
import com.sahaj.parkinglot.entities.vehicles.Truck;
import com.sahaj.parkinglot.exceptions.NoPositionFound;
import com.sahaj.parkinglot.models.impl.AirportFeeModel;
import com.sahaj.parkinglot.models.impl.StadiumFeeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private Parking.Builder parkingLotBuilder;

/*    @BeforeEach
    void setUp() {
        this.parkingLotBuilder = new Parking.Builder().largePositions(2).mediumPositions(5).smallPositions(10);
    }*/

    // Example in assignment: Example #1  (Mall model)
    @Test
    void verifyTestsForAllVehiclesForMallModel() {
        // Building the parking lot with model : Mall (default)
        Parking.Builder parkingLotBuilder = new Parking.Builder().smallPositions(2);

        // gathering the lots
        Parking lot = parkingLotBuilder.build();

        // parking the vehicles to the corresponding slots with entry log
        Ticket ticket2 = lot.park(new Bike(),new Date("29-May-2022 14:44:07"));
        Ticket ticket4=lot.park(new Bike(),new Date("29-May-2022 14:04:07"));

        // Now the positions are full for bike and thus will throw position not
        // available exception

        // Trying to park car
        final Ticket[] ticket = new Ticket[1];
        NoPositionFound thrown = Assertions.assertThrows(NoPositionFound.class, () -> {
             ticket[0] = lot.park(new Car(),new Date("29-May-2022 00:00:00"));
        }, "NumberFormatException was expected");

        Assertions.assertEquals("No position available for car", thrown.getMessage());

        // Trying to park car
        final Ticket[] ticket5 = new Ticket[1];
        NoPositionFound thrown2 = Assertions.assertThrows(NoPositionFound.class, () -> {
            ticket5[0] = lot.park(new Truck(),new Date("29-May-2022 00:00:00"));
        }, "NumberFormatException was expected");

        Assertions.assertEquals("No position available for truck", thrown2.getMessage());

        // Number of positions available
        Assertions.assertEquals(0,lot.getPositionsCount());

        // unparking the vehicles with exit timestamp
        Receipt receipt1 = lot.unPark(ticket2,new Date("29-May-2022 15:40:07"));
        Receipt receipt2 = lot.unPark(ticket4,new Date("29-May-2022 17:44:07"));

        Assertions.assertEquals(10,receipt1.getFare());
        Assertions.assertEquals(40,receipt2.getFare());
        // Number of positions available
        Assertions.assertEquals(2,lot.getPositionsCount());

        // Receipt
        System.out.println(receipt1);
        System.out.println(receipt2);
    }
    // Example in assignment: Example #2  (Mall model)
    @Test
    void verifyTestsForAllVehiclesForMallModelv2() {
        // Building the parking lot with model : Mall (default)
        Parking.Builder parkingLotBuilder = new Parking.Builder().smallPositions(100).mediumPositions(80).largePositions(10);

        // gathering the lots
        Parking lot = parkingLotBuilder.build();

        // parking the vehicles to the corresponding slots with entry log
        Ticket ticket1 = lot.park(new Bike(),new Date("04-Mar-2022 00:00:00"));
        Ticket ticket2=lot.park(new Car(),new Date("04-Mar-2022 0:00:00"));
        Ticket ticket3=lot.park(new Truck(),new Date("04-Mar-2022 0:00:00"));

        // Number of positions available
        Assertions.assertEquals(187,lot.getPositionsCount());

        // unparking the vehicles with exit timestamp
        Receipt receipt1 = lot.unPark(ticket1,new Date("04-Mar-2022 03:30:00"));
        Receipt receipt2 = lot.unPark(ticket2,new Date("04-Mar-2022 06:01:00"));
        Receipt receipt3 = lot.unPark(ticket3,new Date("04-Mar-2022 01:59:00"));

        Assertions.assertEquals(40,receipt1.getFare());
        Assertions.assertEquals(140,receipt2.getFare());
        Assertions.assertEquals(100,receipt3.getFare());
        // Number of positions available
        Assertions.assertEquals(190,lot.getPositionsCount());

        // Receipt
        System.out.println(receipt1);
        System.out.println(receipt2);
        System.out.println(receipt3);
    }
    // Example in assignment: Example #3 (Stadium model)
    @Test
    void verifyTestsForAllVehiclesForStadiumModel() {
        // Building the parking lot with model : Stadium
        Parking.Builder parkingLotBuilder = new Parking.Builder().mediumPositions(1500).model(new StadiumFeeModel()).smallPositions(1000);

        // gathering the lots
        Parking lot = parkingLotBuilder.build();

        // parking the vehicles to the corresponding slots with entry log
        Ticket ticket1 = lot.park(new Bike(),new Date("29-May-2022 00:00:00"));
        Ticket ticket2 = lot.park(new Bike(),new Date("29-May-2022 00:00:00"));
        Ticket ticket3 = lot.park(new Car(),new Date("29-May-2022 00:00:00"));
        Ticket ticket4 = lot.park(new Car(),new Date("29-May-2022 00:00:00"));

        // Trying to park truck
        final Ticket[] ticket5 = new Ticket[1];
        NoPositionFound thrown2 = Assertions.assertThrows(NoPositionFound.class, () -> {
            ticket5[0] = lot.park(new Truck(),new Date("29-May-2022 00:00:00"));
        }, "NumberFormatException was expected");

        Assertions.assertEquals("No position available for truck", thrown2.getMessage());

        // unparking the vehicles with exit timestamp
        Receipt receipt1 = lot.unPark(ticket1,new Date("29-May-2022 03:40:00"));
        Receipt receipt2 = lot.unPark(ticket2,new Date("29-May-2022 14:59:00"));
        Receipt receipt3 = lot.unPark(ticket3,new Date("29-May-2022 11:30:00"));
        Receipt receipt4 = lot.unPark(ticket4,new Date("29-May-2022 13:05:00"));

        Assertions.assertEquals(30,receipt1.getFare());
        Assertions.assertEquals(390,receipt2.getFare());
        Assertions.assertEquals(180,receipt3.getFare());
        Assertions.assertEquals(580,receipt4.getFare());

        // Receipt
        System.out.println(receipt1);
        System.out.println(receipt2);
        System.out.println(receipt3);
        System.out.println(receipt4);
    }
    // Example in assignment: Example #4 (Airport model)
    @Test
    void verifyTestsForAllVehiclesForAirportModel(){
        // Building the parking lot with model : Airport
        Parking.Builder parkingLotBuilder = new Parking.Builder().largePositions(10).mediumPositions(500).model(new AirportFeeModel()).smallPositions(200);

        // gathering the lots
        Parking lot = parkingLotBuilder.build();

        // parking the vehicles to the corresponding slots with entry log
        Ticket ticket1 = lot.park(new Bike(),new Date("29-May-2022 00:00:00"));
        Ticket ticket2 = lot.park(new Bike(),new Date("29-May-2022 00:00:00"));
        Ticket ticket3 = lot.park(new Bike(),new Date("29-May-2022 00:00:00"));
        Ticket ticket4 = lot.park(new Car(),new Date("29-May-2022 00:00:00"));
        Ticket ticket5 = lot.park(new Car(),new Date("29-May-2022 00:00:00"));
        Ticket ticket6 = lot.park(new Car(),new Date("29-May-2022 00:00:00"));

        // unparking the vehicles with exit timestamp
        Receipt receipt1 = lot.unPark(ticket1,new Date("29-May-2022 00:55:00"));
        Receipt receipt2 = lot.unPark(ticket2,new Date("29-May-2022 14:59:00"));
        Receipt receipt3 = lot.unPark(ticket3,new Date("30-May-2022 12:00:00"));
        Receipt receipt4 = lot.unPark(ticket4,new Date("29-May-2022 00:50:00"));
        Receipt receipt5 = lot.unPark(ticket5,new Date("29-May-2022 23:59:00"));
        Receipt receipt6 = lot.unPark(ticket6,new Date("2-Jun-2022 00:01:00"));

        Assertions.assertEquals(0,receipt1.getFare());
        Assertions.assertEquals(60,receipt2.getFare());
        Assertions.assertEquals(160,receipt3.getFare());
        Assertions.assertEquals(60,receipt4.getFare());
        Assertions.assertEquals(80,receipt5.getFare());
        Assertions.assertEquals(400,receipt6.getFare());

        // Receipt
        System.out.println(receipt1);
        System.out.println(receipt2);
        System.out.println(receipt3);
        System.out.println(receipt4);
        System.out.println(receipt5);
        System.out.println(receipt6);
    }
}
