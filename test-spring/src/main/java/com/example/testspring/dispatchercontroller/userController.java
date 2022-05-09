package com.example.testspring.dispatchercontroller;
import com.example.testspring.controllers.RideOffer;
import com.example.testspring.controllers.user_service;
import com.example.testspring.model.DataSystem;
import com.example.testspring.users.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/Home")
public class userController {

    @Autowired
    DataSystem dataSystem;
    User u=null;
    user_service userS=new user_service();

    public userController(DataSystem dataSystem){this.dataSystem = dataSystem;}

    @PostMapping(path="/User/register", consumes = "application/json", produces = "application/json")
    public boolean registerUser(@RequestBody Person user)
    {

        boolean b=userS.register(user.getUserName(),user.getPassword(),user.getEmail(),user.getMobilNumber(),user.getBirthday(),dataSystem);

        return b;
    }

    @GetMapping("/User/get")
    public ArrayList<userInfo> getUsers(){

        ArrayList<userInfo> info=new ArrayList<>();
        ArrayList<User> users =dataSystem.get_allU();
        for (int i = 0; i < users.size(); i++) {
            userInfo temp=new userInfo( users.get(i).getUserName() , users.get(i).getEmail() , users.get(i).getMobilNumber(), users.get(i).getPassword());
            info.add(temp);

        }


        return info;

    }
    @GetMapping("/User/login")
    @ResponseBody
    public User login(@RequestParam String name , @RequestParam String password ){       //http://localhost:5050/Home/User/login?name=huda5&password=66

        u=dataSystem.loginUser(name,password);
        if(u==null || u.isSuspendStatus())
            return null;
        else
            userS.setU(u);
        return u;
    }
    @GetMapping("/User/requestRide")
    @ResponseBody
    public boolean requestRide(@RequestParam String source , @RequestParam String destinaton, @RequestParam int numPassenger){
        userS.requestRide(source, destinaton,numPassenger ,dataSystem);
        return true;
    }
    @GetMapping("/User/RideOffer")
    public ArrayList<offer> RideOffer(){
        ArrayList<offer> o=new ArrayList<offer>();
        ArrayList<RideOffer> rideOfferses = userS.getU().getRideOfferses();
        for (int i = 0; i < rideOfferses.size(); i++) {
            System.out.println(i + " - "+rideOfferses.get(i).getMsg()+" --> Price: "  + rideOfferses.get(i).getPrice() + "\n"
                    + "Driver Name: " + rideOfferses.get(i).getDriver().getUserName());
            offer temp=new offer(rideOfferses.get(i).getMsg(),rideOfferses.get(i).getPrice() ,rideOfferses.get(i).getDriver().getUserName());
            o.add(temp);
        }
        return o;
    }
    @GetMapping("/User/DriverAverageRate")
    @ResponseBody
    public double DriverAverageRate(@RequestParam int index){
        ArrayList<RideOffer> rideOfferses = userS.getU().getRideOfferses();
        rideOfferses.get(index).getDriver().getRate().getAverageRate();
        return rideOfferses.get(index).getDriver().getRate().getAverageRate();
    }
    @GetMapping("/User/RateDriver")
    @ResponseBody
    public boolean RateDriver(@RequestParam int index,@RequestParam int rate){
        if(u==null)
            return false;
        ArrayList<RideOffer> rideOfferses = userS.getU().getRideOfferses();
        userS.rateDriver(rideOfferses.get(index).getDriver(), rate);
        return true;
    }
    @GetMapping("/User/AcceptOffer")
    @ResponseBody
    public boolean AcceptOffer(@RequestParam int index){
        if(u==null)
            return false;
        ArrayList<RideOffer> rideOfferses = userS.getU().getRideOfferses();
        userS.getU().addDrivers(rideOfferses.get(index).getDriver());
        userS.getU().getRideOfferses().get(index).setOfferStatus(true);
        ArrayList <RideOffer> temp= new ArrayList<>();
        userS.getU().setRideOfferses(temp);
        userS.acceptOffer(rideOfferses.get(index).getDriver(), rideOfferses.get(index).getPrice(), dataSystem);
        return true;
    }

    class offer{
        @JsonSerialize
        double price;
        @JsonSerialize
        String driverName;
        @JsonSerialize
        String msq;
        offer(){

        }
        offer(String msq,double price,String driverName)
        {
            this.price=price;
            this.driverName=driverName;
            this.msq=msq;
        }
    }

    class userInfo{
        @JsonSerialize
        private String username;
        @JsonSerialize
        private String email;
        @JsonSerialize
        private String mobilNumber;
        @JsonSerialize
        private String password;

        userInfo(){}
        userInfo(String username,String email,String mobilNumber,String password){
            this.username=username;
            this.email=email;
            this.mobilNumber=mobilNumber;
            this.password=password;

        }
    }
}
