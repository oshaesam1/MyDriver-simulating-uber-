package com.example.testspring.users;

import com.example.testspring.controllers.Discount;
import com.example.testspring.model.DataSystem;
import com.example.testspring.model.EventInfo;

import java.util.ArrayList;

public class Admin {
    public String id;
    public String password;

    public Admin()
    {
        id="admin";
        password="admin";
    }

    public void verifyDriverRegistration(Driver d){
        d.setPendingState(false);
    }

    public void suspend(Person p){
        p.suspendStatus=true;
    }
    public void unSuspend(Person p){
        p.suspendStatus=false;
    }
    public ArrayList<EventInfo> printEvents(DataSystem dataSystem){
        return dataSystem.print_Events();
    }
    public ArrayList<Discount> makeDiscount(String country, double discount, DataSystem dataSystem){
        Discount d=new Discount(country,discount);
        return dataSystem.addSpecialCountries(d);
    }
}
