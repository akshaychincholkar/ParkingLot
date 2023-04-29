package com.sahaj.parkinglot.models.impl;

import com.sahaj.parkinglot.entities.Receipt;
import com.sahaj.parkinglot.entities.Ticket;
import com.sahaj.parkinglot.models.FeeModel;

public class StadiumFeeModel implements FeeModel {
    private final String MODEL = "stadium";
    @Override
    public Receipt calculateFare(Integer receiptNumber,Ticket ticket) {
        return new Receipt(receiptNumber,calculate(ticket),ticket);
    }
    public int calculate(Ticket ticket){
        int hrs= ticket.getTotalHours();
        int mins = ticket.getTotalMins();
        int[] fee = ticket.getVehicle().getFees(MODEL);
        int[] timeframe = ticket.getVehicle().getTimeFrames(MODEL);
        int fare = 0;
        boolean isInRange = false;
        for(int i = timeframe.length-1;i>=0;i--){
            int calc= hrs/timeframe[i];
            if(calc > 0){
                if(i==timeframe.length-1) {
                    isInRange=true;
                    fare += (hrs%timeframe[i]) * fee[i];
                }
                if(!isInRange){
                    fare += fee[i];
                    hrs = hrs/timeframe[i];
                }
            }
            if(isInRange){
                fare += fee[i];
            }
            hrs = hrs % timeframe[i];
        }

        return fare;
    }

    @Override
    public String getModel() {
        return MODEL;
    }
}
