package com.example.testspring.model;

import com.example.testspring.controllers.*;
import com.example.testspring.users.Driver;
import com.example.testspring.users.User;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataSystem {
    public static ArrayList<User> users;
    public static ArrayList<Driver> drivers;
    private static ArrayList<RideRequest> rideRequests;
    private static ArrayList<Driver> pendingList;
    public static ArrayList<Ride> rides;
    static Map<Ride,ArrayList<Event>> events;
    public static ArrayList<Discount> specialCountries;
    public static ArrayList<LocalDate> publicHolidays;

    public ArrayList<Discount> getSpecialCountries() {
        return specialCountries;
    }

    public ArrayList<Discount> addSpecialCountries(Discount specialCountries) {
        this.specialCountries.add(specialCountries);
        return this.specialCountries;
    }

    private static DataSystem single_instance = null;

    private DataSystem()
    {
        users=new ArrayList<User>();
        drivers=new ArrayList<Driver>();
        rideRequests=new ArrayList<RideRequest>();
        pendingList=new ArrayList<Driver>();
        events=new HashMap<Ride,ArrayList<Event>>();
        rides=new ArrayList<Ride>();
        specialCountries=new ArrayList<Discount>();
        publicHolidays=new ArrayList<LocalDate>();
        fullPublicHoliday();
        fullSomeData();
    }
    private void fullSomeData(){
       // driver_service d1=new driver_service();
        driver_service d1=new driver_service();
        //Driver d1 = new Driver();

        d1.register("Mohamed", "123", "m123", "01123369545", "1235", "69446", this);
        d1.getD().getFavAreas().addFavArea("cairo");
        d1.getD().getFavAreas().addFavArea("haram");
        // Driver d2 = new Driver();
        driver_service d2=new driver_service();
        d2.register("Ramy", "1234", "r123", "01123369545", "654", "6469974", this);
        d2.getD().getFavAreas().addFavArea("helwan");
        d2.getD().getFavAreas().addFavArea("maadi");
        // Driver d3 = new Driver();
        driver_service d3=new driver_service();
        d3.register("Joo", "1235", "jo123", "0102339547", "93", "6932", this);
        d3.getD().getFavAreas().addFavArea("cairo");
        user_service u1=new user_service();
        //User u1 = new User();
        u1.register("Marwa", "000", "mro", "okvgc", LocalDate.parse("1999-05-01"),this);
        u1.rateDriver(d1.getD(), 3);
        u1.rateDriver(d1.getD(), 4);
        u1.rateDriver(d3.getD(), 5);
        //User u2 = new User();
        user_service u2=new user_service();
        u2.register("Salma", "00", "s_12", "oksalma",LocalDate.parse("1998-01-01"), this);
        u2.rateDriver(d3.getD(), 5);
        u2.rateDriver(d2.getD(), 3);


    }
    private void fullPublicHoliday(){
        publicHolidays.add( LocalDate.parse("2022-01-07"));
        publicHolidays.add( LocalDate.parse("2022-01-28"));
        publicHolidays.add( LocalDate.parse("2022-04-25"));
        publicHolidays.add( LocalDate.parse("2022-05-01"));
        publicHolidays.add( LocalDate.parse("2022-05-03"));
        publicHolidays.add( LocalDate.parse("2022-06-30"));
        publicHolidays.add( LocalDate.parse("2022-10-06"));
        publicHolidays.add( LocalDate.parse("2022-03-21"));
    }
    public void addRide(Ride r){
        rides.add(r);
    }
    public Ride searchRide(double price,Driver d){
        Ride ride=null;
        for(int i=0; i<rides.size();i++)
        {
            if(d.getUserName().equals(rides.get(i).getDriver().getUserName()) && d.getMobilNumber().equals(rides.get(i).getDriver().getMobilNumber()) )
            {

                if( rides.get(i).isState())
                {
                    ride=rides.get(i);
                }
            }
        }
        return ride;
    }
    public ArrayList<Ride> getRides() {
        return rides;
    }

    public void setRides(ArrayList<Ride> rides) {
        this.rides = rides;
    }
    public Map<Ride, ArrayList<Event>> getEvents() {
        return events;
    }

    public void setEvent(Ride ride,Event event) {
        ArrayList<Event>e=new ArrayList<>();
        if(events.get(ride)!=null)
            e=events.get(ride);
        e.add(event);
        events.put(ride, e);
    }

    public ArrayList<EventInfo> print_Events() {

        ArrayList<EventInfo> info=new ArrayList<>();
        int ind=0;
        for(Ride r:events.keySet())
        {
            for(int i=0;i<events.get(r).size();i++)
            {
                System.out.println("Ride "+ind+": Event Name : "+events.get(r).get(i).getEventName()+"\n"
                        +"Event Time: "+events.get(r).get(i).getEventTime()+"\n"
                        + "User Name: "+events.get(r).get(i).getRide().getUser().getUserName()+"\n"
                        + "Driver Name: "+events.get(r).get(i).getRide().getDriver().getUserName()+"\n"
                        + "Price: "+events.get(r).get(i).getRide().getPrice());
                EventInfo temp=new EventInfo(events.get(r).get(i).getEventName(),
                        events.get(r).get(i).getEventTime(),
                        events.get(r).get(i).getRide().getUser().getUserName(),
                        events.get(r).get(i).getRide().getDriver().getUserName(),
                        events.get(r).get(i).getRide().getPrice());
                info.add(temp);
            }
            System.out.println();
            ind++;
        }
        return info;
    }
    public static DataSystem getInstance()
    {
        if (single_instance == null)
            single_instance = new DataSystem();

        return single_instance;
    }

    public  void removePendingDrivers(Driver d)
    {
        pendingList.remove(d);
    }
    public  void addPendingDrivers(Driver d )
    {
        pendingList.add(d);
    }
    public ArrayList <Driver> getPendingList ()
    {
        return pendingList;
    }
    public void printAllData(){

        System.out.println("Users");
        for(int i=0;i<users.size();i++)
        {
            System.out.println("User Name: "+users.get(i).getUserName()+" \nUser Email: "+users.get(i).getEmail()
                    +" \n Suspend Status: "+users.get(i).isSuspendStatus());
            ArrayList<RideOffer> rideOffer = users.get(i).getRideOfferses();
            System.out.println("Ride Offer ");
            for(int j=0;j<rideOffer.size();j++){
                System.out.println("Price: "+rideOffer.get(j).getPrice()
                        +" \n Driver Name: "+rideOffer.get(j).getDriver().getUserName());
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Driver");
        for(int i=0;i<drivers.size();i++)
        {
            System.out.println("User Name: "+drivers.get(i).getUserName()+" \nUser Email: "+drivers.get(i).getEmail()
                    +" \n Suspend status: "+drivers.get(i).isSuspendStatus()+"\n"
                    + "Pendind status: "+drivers.get(i).pendingState);
            System.out.println("Fav Areas ");
            FavAreas favA=drivers.get(i).getFavAreas();
            ArrayList<String>favAreas=favA.returnAllAreas() ;
            for(int j=0;j<favAreas.size();j++){
                System.out.println(favAreas.get(j)+"  ");
            }
            ArrayList<RideRequest> rideRequests=drivers.get(i).getRideRequests();
            System.out.println("Ride Requests ");
            for(int j=0;j<rideRequests.size();j++){
                System.out.println("source: "+rideRequests.get(j).getSource()+" \nDestination:  "+rideRequests.get(j).getDestination()
                        +" \n User Name: "+rideRequests.get(j).getUser().getUserName());
            }
        }

    }
    public boolean addUser(User user){
        users.add(user);
        return true;
    }

    public boolean addDriver(Driver Driver){
        drivers.add(Driver);
        return true;

    }

    public User loginUser(String name, String password){
        User user=null;
        for(int i=0;i<users.size();i++)
        {
            if((password.equals(users.get(i).getPassword()))&&(name.equals(users.get(i).getUserName()))){

                user=users.get(i);

            }
        }
        return user;
    }
    public Driver loginDriver(String name, String password){
        Driver driver=null;
        for(int i=0;i<drivers.size();i++)
        {
            if((password.equals(drivers.get(i).getPassword()))&&(name.equals(drivers.get(i).getUserName()))){
                driver=drivers.get(i);

            }
        }
        return driver;
    }

    public ArrayList<Driver> get_allD(){

        return drivers;
    }
    public ArrayList<User> get_allU(){

        return users;
    }


    public void addRideRequest(RideRequest r) {
        rideRequests.add(r);
    }


}
