
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hoda
 */
public class UserController {
      User u=new User();

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
  
      public void register(String userName,String password, String email, String mobilNumber,LocalDate birthday ,DataSystem database){
        u.userName=userName;
        u.email=email;
        u.mobilNumber=mobilNumber;
        u.password=password;
        u.birthday=birthday;
        database.addUser(u);

    }

    public void requestRide(String source , String destination , int num_passenger ,DataSystem database ){
        u.getRideRequests().makeRequest(source,destination,num_passenger,u,database);
    }
    public void rateDriver(Driver driver,int rate){
        driver.getRate().addRate(rate, u);
    }
    public void setOfferStatus(boolean f, RideOffer r){
        r.setOfferStatus(f);
    }
    public void getDriverAverageRate(Driver d){
        System.out.println("Average Rate: "+d.getRate().getAverageRate());

    }

    public void acceptOffer(Driver d,double price ,DataSystem database){
        saveEvent(u, d, price,database);
        int num=d.getNumOfSeats();
        num--;
        d.setNumOfSeats(num);
        if(num<=0)
        {
            d.setAvailability(false);
        }
        
        
    }
    private void saveEvent(User user, Driver aThis , double price, DataSystem database){
        Event e=new Event();
        
        Ride r=database.searchRide(price,aThis);
        e.setRide(r);
        //System.out.println(java.time.LocalTime.now()); 
        e.setEventTime(java.time.LocalTime.now());
        e.setEventName("User "+user.userName+" accepts the captain price ");
        database.setEvent(r, e);
        r.arrivedToLocation(user,aThis,price,database);
    }
    public void update(RideRequest aRequest, RideOffer aOffer) {
       u.update(aRequest, aOffer);
    }
}
