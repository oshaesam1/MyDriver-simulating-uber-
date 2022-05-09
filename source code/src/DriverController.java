/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hoda
 */
public class DriverController {
    
    Driver d=new Driver();
       public void register(String userName, String password, String email, String mobilNumber, String nationalId, String license, DataSystem database){
        d.userName=userName;
        d.email=email;
        d.mobilNumber=mobilNumber;
        d.password=password;
        d.setNationalId(nationalId);
        d.setDrivingLicense(license);
        database.addPendingDrivers(d);
        database.addDriver(d);


    }

    public Driver getD() {
        return d;
    }

    public void setD(Driver d) {
        this.d = d;
    }
    public void makeOffer(User user,String des,double price,int num_passenger,DataSystem database)
    {
        saveEvent(user, d, price,database);
        d.getOffer().makeOffer(user , d, price, des,num_passenger,database);
        

    }
     private void saveEvent(User user, Driver aThis , double price, DataSystem database){
        Event e=new Event();
        Ride r=new Ride(aThis,user,price);
        e.setRide(r);
        //System.out.println(java.time.LocalTime.now()); 
        e.setEventTime(java.time.LocalTime.now());
        e.setEventName("Captain "+aThis.userName+" put a price to the ride ");
        database.setEvent(r, e);
        database.addRide(r);
    }
//     public void arrivedToLocation(User user, Driver aThis , double price, DataSystem database,Ride r)
//    {
//        Event e=new Event();
//        e.setRide(r);
//        e.setEventTime(java.time.LocalTime.now());
//        e.setEventName("User "+user.userName+" accepts the captain price ");
//        database.setEvent(r, e);
//    }
    public void update(RideRequest aRequest, RideOffer aOffer) {
        update(aRequest, aOffer);
    }
    
}
