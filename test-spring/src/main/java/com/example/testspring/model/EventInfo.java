package com.example.testspring.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

public class EventInfo {
    @JsonSerialize
    private String eventName;
    @JsonSerialize
    private LocalTime eventTime;
    @JsonSerialize
    private String userName;
    @JsonSerialize
    private String driverName;
    @JsonSerialize
    private double price;

    public EventInfo(){

    }
    public EventInfo(String Name, LocalTime Time, String userName, String driverName, double price){
        this.eventName=Name;
        this.eventTime=Time;
        this.userName=userName;
        this.driverName=driverName;
        this.price=price;

    }
}
