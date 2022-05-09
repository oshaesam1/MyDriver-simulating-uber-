package com.example.testspring.users;
import com.example.testspring.controllers.*;
import com.example.testspring.model.FavAreas;

import java.util.ArrayList;


 public class Driver  extends Person implements Observer
{
    private FavAreas favAreas;
    private ArrayList<RideRequest> rideRequests;
    private Rate rate;
    private RideOffer offer;
    public boolean pendingState;
    private boolean availability;
    private int numOfSeats;

    public Driver()
    {
        rideRequests=new ArrayList<RideRequest>();
        favAreas=new FavAreas();
        offer=new RideOffer();
        rate=new Rate();
        pendingState=true;
        availability=true;
        numOfSeats= 3 ;
    }
    //public Driver(){}


    public Driver( String userName,
                   String email
                 , String password
                 , String nationalId
                 ,String mobileNumber
                , String drivingLicense
           )
    {
        this.userName=userName;
        this.email=email;
        this.password=password;
        this.nationalId=nationalId;
        this.mobilNumber=mobileNumber;
        this.drivingLicense=drivingLicense;

    }
    public void set_name(String n){this.userName=n;}
    public String get_name(){return userName;}
    public void set_mail(String n){this.email=n;}
    public String get_mail(){return email;}
    public void set_id(String n){this.nationalId=n;}
    public String get_id(){return nationalId;}
    public void set_mob(String n){this.mobilNumber=n;}
    public String get_mob(String n){return mobilNumber;}
    public void set_pass(String n){this.password=n;}
    public String get_pass(){return password;}

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public void addRideRequest(RideRequest r){
        rideRequests.add(r);
    }
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public FavAreas getFavAreas() {
        return favAreas;
    }

    public void setFavAreas(FavAreas favAreas) {
        this.favAreas = favAreas;
    }

    public ArrayList<RideRequest> getRideRequests() {
        return rideRequests;
    }

    public void setRideRequests(ArrayList<RideRequest> rideRequests) {
        this.rideRequests = rideRequests;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public RideOffer getOffer() {
        return offer;
    }

    public void setOffer(RideOffer offer) {
        this.offer = offer;
    }

    public boolean isPendingState() {
        return pendingState;
    }

    public void setPendingState(boolean pendingState) {
        this.pendingState = pendingState;
    }



    @Override
    public void update(RideRequest aRequest, RideOffer aOffer)
    {
        System.out.println(this.userName+" has new notification \n this new ride request: \n"
                + "Source: "+aRequest.getSource()+" \nDestination: "+aRequest.getDestination());
    }


}
