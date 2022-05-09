import java.util.ArrayList;


public class User extends Person implements Observer{
    private RideRequest rideRequests;
    private ArrayList<RideOffer> rideOfferses;
    private ArrayList<String> MyDrivers ;

   
    
    
    
    public RideRequest getRideRequests() {
        return rideRequests;
    }

    public void setRideRequests(RideRequest rideRequests) {
        this.rideRequests = rideRequests;
    }

    public ArrayList<RideOffer> getRideOfferses() {
        return rideOfferses;
    }

    public void setRideOfferses(ArrayList<RideOffer> rideOfferses) {
        this.rideOfferses = rideOfferses;
    }
    public void addDrivers(Driver driver){
        MyDrivers.add(driver.userName);
    }
    public ArrayList<String> get_MyDriver()
    {
        return MyDrivers;
    }


    public User(){
        rideRequests=new RideRequest();
        rideOfferses=new ArrayList<RideOffer>();
        MyDrivers=new ArrayList<String>();
    }
    public void addRideOffer(RideOffer r){
        rideOfferses.add(r);
    }

  @Override
    public void update(RideRequest aRequest, RideOffer aOffer) {
        System.out.println(this.userName+" has new Offer \n"
                + "Price: "+aOffer.getPrice()+" \nDriver Name: "+aOffer.getDriver().userName);
    }


}
