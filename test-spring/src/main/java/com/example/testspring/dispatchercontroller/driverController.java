package com.example.testspring.dispatchercontroller;
import com.example.testspring.controllers.RideRequest;
import com.example.testspring.controllers.driver_service;
import com.example.testspring.model.DataSystem;
import com.example.testspring.users.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(value = "/Home")
//@RequestMapping
public class driverController
{


    @Autowired
    DataSystem dataSystem;
    Driver d=null;
    driver_service driverS=new driver_service();
    public driverController(DataSystem dataSystem){this.dataSystem = dataSystem;}
    @GetMapping(value = "/hello")
    public static String hello(){return "Hello Huda";}


    @PostMapping(path = "/Driver/register", consumes = "application/json", produces = "application/json")
    public  boolean registerDriver (@RequestBody Person driver)
    {
        boolean b=driverS.register(driver.getUserName(),driver.getPassword(),driver.getEmail(),driver.getMobilNumber(),driver.getNationalId(),driver.getDrivingLicense(),dataSystem);

        return b;
    }
    @GetMapping("/Driver/get")
    public ArrayList<driverInfo> getDrivers(){
        ArrayList<driverInfo> info=new ArrayList<>();
        ArrayList<Driver> drivers =dataSystem.get_allD();;
        for (int i = 0; i < drivers.size(); i++) {
            driverInfo temp=new driverInfo( drivers.get(i).getUserName() , drivers.get(i).getEmail() , drivers.get(i).getMobilNumber(), drivers.get(i).getPassword(),drivers.get(i).getNationalId());
            info.add(temp);
        //    driverInfo(String username,String email,int mobilNumber,String password,String nationalId){
        }


        return info;

    }
    @GetMapping("/Driver/login")
    @ResponseBody
    public Driver login(@RequestParam String name , @RequestParam String password ){       //http://localhost:5050/Home/Driver/login?name=hana5&password=66

        d=dataSystem.loginDriver(name,password);
        if(d==null || d.isSuspendStatus()||d.isPendingState())
            return null;
        else
            driverS.setD(d);
        return d;
    }

    @GetMapping("/Driver/getRideRequests")
    public ArrayList<rideInfo> getRideRequests(){
        ArrayList<rideInfo> info=new ArrayList<>();
        ArrayList<RideRequest> rideRequests = driverS.getD().getRideRequests();
        for (int i = 0; i < rideRequests.size(); i++) {
            System.out.println(i + "- Source: " + rideRequests.get(i).getSource() + "  Destination: " + rideRequests.get(i).getDestination() + "\n");
            rideInfo temp=new rideInfo( rideRequests.get(i).getSource() , rideRequests.get(i).getDestination() , rideRequests.get(i).getNum_Passenger(), rideRequests.get(i).getUser().getUserName());
            info.add(temp);
        }


        return info;
    }

    @GetMapping("/Driver/getAverageRate")
    public double getAverageRate(){
        return  driverS.getD().getRate().getAverageRate();
    }
    @GetMapping("/Driver/AllRates")
    public Map<String,Integer> AllRates(){

        return driverS.getD().getRate().printAllRates();

    }
    @GetMapping("/Driver/AllAreas")
    public ArrayList<String> AllAreas(){

        return driverS.getD().getFavAreas().returnAllAreas();

    }
    @GetMapping("/Driver/AddFavoriteArea")
    @ResponseBody
    public boolean AddFavoriteArea(@RequestParam String area){
        if(d==null)
            return false;
        driverS.getD().getFavAreas().addFavArea(area);
        return true;

    }
    @GetMapping("/Driver/MakeOffer")
    @ResponseBody
    public boolean MakeOffer(@RequestParam double offer,@RequestParam int index){
        ArrayList<RideRequest> rideRequests = driverS.getD().getRideRequests();
        if(d==null)
            return false;
        driverS.makeOffer(rideRequests.get(index).getUser(),rideRequests.get(index).getDestination(), offer,rideRequests.get(index).getNum_Passenger(), dataSystem);
        return true;

    }
    class rideInfo{
       @JsonSerialize
        private String source;
        @JsonSerialize
        private String destination;
        @JsonSerialize
        private int num_Passenger;
        @JsonSerialize
        private String userName;
        rideInfo(){}
        rideInfo(String source,String destination,int num_Passenger,String userName){
            this.source=source;
            this.destination=destination;
            this.num_Passenger=num_Passenger;
            this.userName=userName;
        }
    }
    class driverInfo{
        @JsonSerialize
        private String username;
        @JsonSerialize
        private String email;
        @JsonSerialize
        private String mobilNumber;
        @JsonSerialize
        private String password;
        @JsonSerialize
        private String nationalId;
        driverInfo(){}
        driverInfo(String username,String email,String mobilNumber,String password,String nationalId){
            this.username=username;
            this.email=email;
            this.mobilNumber=mobilNumber;
            this.password=password;
            this.nationalId=nationalId;

        }
    }

}
