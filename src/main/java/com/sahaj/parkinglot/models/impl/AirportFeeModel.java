package com.sahaj.parkinglot.models.impl;

import com.sahaj.parkinglot.entities.Receipt;
import com.sahaj.parkinglot.entities.Ticket;
import com.sahaj.parkinglot.models.FeeModel;

public class AirportFeeModel implements FeeModel {

    private final String MODEL = "airport";
    @Override
    public Receipt calculateFare(Integer receiptNumber,Ticket ticket) {
        return new Receipt(receiptNumber,calculate(ticket),ticket);
    }
    public int  calculate(Ticket ticket){
        int hrs= ticket.getTotalHours();
        int mins = ticket.getTotalMins();
        int[] fee = ticket.getVehicle().getFees(MODEL);
        int[] timeframe = ticket.getVehicle().getTimeFrames(MODEL);
        for(int i = timeframe.length-2;i>=0;i--){
            if(hrs>=timeframe[i] && hrs<timeframe[i+1])return fee[i];
        }
        int calc = hrs/24;
        int result = 0;
        if(calc>=1){
            result = calc*fee[fee.length-1];
            if(hrs%24>=1) result+=fee[fee.length-1];
        }
        return result;
    }

    @Override
    public String getModel() {
        return MODEL;
    }
}
