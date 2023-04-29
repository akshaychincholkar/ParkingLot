package com.sahaj.parkinglot.entities;

public class Receipt {
    private int id;
    private int fare;
    private Ticket ticket;

    public Receipt(int id,int fare, Ticket ticket) {
        this.id = id;
        this.fare = fare;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public int getFare() {
        return fare;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "--------------------------------------------------------\n"+"Receipt: " +
                "\nreceipt number:" + id +
                 ticket +
                "fare:" + fare +
                "\n--------------------------------------------------------";
    }
}
