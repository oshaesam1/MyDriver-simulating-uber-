import java.util.ArrayList;


public class Driver  extends Person implements Observer {
    private String nationalId;
    private String drivingLicense;
    private FavAreas favAreas;
    private ArrayList<RideRequest> rideRequests;
    private Rate rate;
    private RideOffer offer;
    public boolean pendingState;
    private boolean availability;
    private int numOfSeats;

    public Driver() {
        rideRequests=new ArrayList<RideRequest>();
        favAreas=new FavAreas();
        offer=new RideOffer();
        rate=new Rate();
        pendingState=true;
        availability=true;
        numOfSeats=3;
    }

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
    public void update(RideRequest aRequest, RideOffer aOffer) {
        System.out.println(this.userName+" has new notification \n this new ride request: \n"
                + "Source: "+aRequest.getSource()+" \nDestination: "+aRequest.getDestination());
    }

}
