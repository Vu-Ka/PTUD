package com.mycompany.datveui;


public class Ticket {
    public String trainCode;
    public String trainType;       
    public String fromStation;
    public String fromTime;
    public String toStation;
    public String toTime;
    public String duration;
    public String date;
    public int price;
    public int seatsLeft;

    public Ticket(String trainCode, String trainType,
                  String fromStation, String fromTime,
                  String toStation, String toTime,
                  String duration, String date,
                  int price, int seatsLeft) {
        this.trainCode = trainCode;
        this.trainType = trainType;
        this.fromStation = fromStation;
        this.fromTime = fromTime;
        this.toStation = toStation;
        this.toTime = toTime;
        this.duration = duration;
        this.date = date;
        this.price = price;
        this.seatsLeft = seatsLeft;
    }
}
