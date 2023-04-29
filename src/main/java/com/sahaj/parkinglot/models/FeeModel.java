package com.sahaj.parkinglot.models;

import com.sahaj.parkinglot.entities.Receipt;
import com.sahaj.parkinglot.entities.Ticket;

public interface FeeModel {
    public Receipt calculateFare(Integer receiptNumber,Ticket ticket);
    public String getModel();
}
