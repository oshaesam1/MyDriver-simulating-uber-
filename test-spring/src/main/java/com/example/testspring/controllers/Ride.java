package com.example.testspring.controllers;


import com.example.testspring.model.*;
import com.example.testspring.users.Driver;
import com.example.testspring.users.User;

import java.time.LocalTime;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hoda
 */
public class Ride {
    private Driver driver;
    private User user;
    private double price;
    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Ride() {
        driver = new Driver();
        user=new User();
        state=true;
    }

    public Ride(Driver driver, User user, double price) {
        this.driver = driver;
        this.user = user;
        this.price = price;
        state=true;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void arrivedToLocation(User user, Driver aThis , double price, DataSystem database)
    {
        Event e=new Event();
        e.setRide(this);
        Random rand = new Random();
        e.setEventTime(java.time.LocalTime.now().plusMinutes(10+rand.nextInt(20)));
        e.setEventName("Captain "+aThis.getUserName()+" arrived to user location");
        aThis.setAvailability(true);
        database.setEvent(this, e);
        arrivedToDestination(e,aThis,database);
    }
    private void arrivedToDestination(Event eu,Driver aThis,DataSystem database)
    {
//        long startTime = System.currentTimeMillis();
//        long maxDurationInMilliseconds = 30 * 60 * 1000;
//
//        while (System.currentTimeMillis() < startTime + maxDurationInMilliseconds) {
//            // carry on running - 30 minutes hasn't elapsed yet
//
//
//        }
        Event e=new Event();
        e.setRide(this);
        Random rand = new Random();
        LocalTime LocalTime = eu.getEventTime();
        e.setEventTime(LocalTime.plusHours(1+rand.nextInt(2)));
        e.setEventName("Captain arrived to user destination");
        aThis.setAvailability(true);
        aThis.setNumOfSeats(3);
        database.setEvent(e.getRide(), e);
        this.state=false;

    }
}
