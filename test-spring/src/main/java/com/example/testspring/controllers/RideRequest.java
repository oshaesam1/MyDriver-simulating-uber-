package com.example.testspring.controllers;

import com.example.testspring.model.DataSystem;
import com.example.testspring.model.FavAreas;
import com.example.testspring.users.User;

import java.util.ArrayList;


public class RideRequest implements Subject {
    private String source;
    private String destination;
    private int num_Passenger;
    private User user;
    private ArrayList<Observer>observers;

    public RideRequest() {
        observers=new ArrayList<Observer>();
    }

    public int getNum_Passenger() {
        return num_Passenger;
    }

    public void setNum_Passenger(int num_Passenger) {
        this.num_Passenger = num_Passenger;
    }


    public void makeRequest(String source, String destination, int num ,User aThis, DataSystem database) {
        this.source=source;
        this.destination=destination;
        this.user=aThis;
        this.num_Passenger=num;
        database.addRideRequest(this);
        notify(database);
    }


    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }



    @Override
    public void notify(DataSystem dataSystem) {
        observers= new ArrayList<Observer>();
        for(int i=0;i<dataSystem.drivers.size();i++)
        {
            FavAreas favA=dataSystem.drivers.get(i).getFavAreas();
            ArrayList<String>favAreas=favA.returnAllAreas() ;
            for(int j=0;j<favAreas.size();j++)
            {
                if(source.equals(favAreas.get(j)))
                {
                    if(dataSystem.drivers.get(i).isAvailability())
                    {
                        dataSystem.drivers.get(i).addRideRequest(this);
                        subscribe( dataSystem.drivers.get(i));
                       // (Observer)
                    }


                }
            }

        }
        for(Observer ob:observers )
        {
            ob.update(this,null);
        }
    }
}

