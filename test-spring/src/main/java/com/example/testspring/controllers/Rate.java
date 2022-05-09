package com.example.testspring.controllers;

import com.example.testspring.users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Rate {
    private double averageRate;
    private ArrayList<Integer> rates;
    private  Map<User,Integer> ratesDetails;

    public Rate() {
        rates= new ArrayList<Integer>();
        ratesDetails=new HashMap<User,Integer>();
        averageRate=0.0;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public ArrayList<Integer> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Integer> rates) {
        this.rates = rates;
    }

    public Map<User, Integer> getRatesDetails() {
        return ratesDetails;
    }

    public void setRatesDetails(Map<User, Integer> ratesDetails) {
        this.ratesDetails = ratesDetails;
    }



    public void addRate(int rate , User user){
        ratesDetails.put(user, rate);
        rates.add(rate);
        calcAverage();
    }

    public void calcAverage(){
        int sum=0;
        for(int i=0;i<rates.size();i++){
            sum+=rates.get(i);
        }
        averageRate=sum/rates.size();
    }
    public Map<String,Integer> printAllRates(){
        Map<String ,Integer> result = new HashMap<String,Integer>();
        for(User u:ratesDetails.keySet())
        {
            System.out.println("Rate: "+ratesDetails.get(u).toString()+" --- "
                    + "User Name: "+u.getUserName());
            result.put(u.getUserName(),ratesDetails.get(u));
        }
        return result;
    }
}
