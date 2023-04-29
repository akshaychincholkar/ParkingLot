package com.sahaj.parkinglot.models.impl;

import com.sahaj.parkinglot.entities.Receipt;
import com.sahaj.parkinglot.entities.Ticket;
import com.sahaj.parkinglot.models.FeeModel;

public class MallFeeModel implements FeeModel {
    private final String MODEL = "mall";
    @Override
    public Receipt calculateFare(Integer receiptNumber,Ticket ticket) {
        return new Receipt(receiptNumber,calculate(ticket.getTotalHours(),ticket.getTotalMins(),ticket.getVehicle().getFees(MODEL)[0],0),ticket);
    }

    @Override
    public String getModel() {
        return MODEL;
    }

    public static int  calculate(int hrs,int mins, int fee, int fare){
        if(hrs == 0) {
            if(mins == 0)return fare;
            return calculate(hrs,0, fee,fare+fee);
        }
        return calculate(hrs-1,mins, fee,fare+fee);
    }

}
