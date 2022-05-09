import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSystem {
    ArrayList<User> users;
    ArrayList<Driver> drivers;
    ArrayList<RideRequest> rideRequests;
    ArrayList<Driver> pendingList;
    ArrayList<Ride> rides;
    Map<Ride,ArrayList<Event>> events;
    ArrayList<Discount> specialCountries;
    ArrayList<LocalDate> publicHolidays;

    public ArrayList<Discount> getSpecialCountries() {
        return specialCountries;
    }

    public void addSpecialCountries(Discount specialCountries) {
        this.specialCountries.add(specialCountries);
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
            if(d.userName.equals(rides.get(i).getDriver().userName) && d.mobilNumber.equals(rides.get(i).getDriver().mobilNumber) )
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
    public void print_Events() {
        int ind=0;
        for(Ride r:events.keySet())
        {
            for(int i=0;i<events.get(r).size();i++)
            {
                System.out.println("Ride "+ind+": Event Name : "+events.get(r).get(i).getEventName()+"\n"
                        +"Event Time: "+events.get(r).get(i).getEventTime()+"\n"
                                + "User Name: "+events.get(r).get(i).getRide().getUser().userName+"\n"
                                        + "Driver Name: "+events.get(r).get(i).getRide().getDriver().userName+"\n"
                                                + "Price: "+events.get(r).get(i).getRide().getPrice());
            }
            System.out.println();
            ind++;
        }
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
            System.out.println("User Name: "+users.get(i).userName+" \nUser Email: "+users.get(i).email
                    +" \n Suspend Status: "+users.get(i).suspendStatus);
            ArrayList<RideOffer> rideOffer = users.get(i).getRideOfferses();
            System.out.println("Ride Offer ");
            for(int j=0;j<rideOffer.size();j++){
                System.out.println("Price: "+rideOffer.get(j).getPrice()
                        +" \n Driver Name: "+rideOffer.get(j).getDriver().userName);
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Driver");
        for(int i=0;i<drivers.size();i++)
        {
            System.out.println("User Name: "+drivers.get(i).userName+" \nUser Email: "+drivers.get(i).email
                    +" \n Suspend status: "+drivers.get(i).suspendStatus+"\n"
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
                        +" \n User Name: "+rideRequests.get(j).getUser().userName);
            }
        }

    }
    public void addUser(User user){
        users.add(user);
    }
    public void addDriver(Driver Driver){
        drivers.add(Driver);
    }
    public User loginUser(String name, String password){
        User user=null;
        for(int i=0;i<users.size();i++)
        {
            if((password.equals(users.get(i).password))&&(name.equals(users.get(i).userName))){

                    user=users.get(i);

            }
        }
        return user;
    }
    public Driver loginDriver(String name, String password){
        Driver driver=null;
        for(int i=0;i<drivers.size();i++)
        {
            if((password.equals(drivers.get(i).password))&&(name.equals(drivers.get(i).userName))){
                    driver=drivers.get(i);

            }
        }
        return driver;
    }



    public void addRideRequest(RideRequest r) {
        rideRequests.add(r);
    }

}
