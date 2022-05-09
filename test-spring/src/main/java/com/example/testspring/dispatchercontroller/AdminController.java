package com.example.testspring.dispatchercontroller;

import com.example.testspring.controllers.Discount;
import com.example.testspring.model.DataSystem;
import com.example.testspring.model.EventInfo;
import com.example.testspring.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/Admin")
public class AdminController {

    @Autowired
    DataSystem dataSystem;
    public AdminController(DataSystem dataSystem){this.dataSystem = dataSystem;}

    @GetMapping("/login")
    @ResponseBody
    public Admin login(@RequestParam String id , @RequestParam String password ){
        Admin a=new Admin();
        if(id.equals(a.id)&&password.equals(a.password))
            return a;
        else
            return null;
    }

    @GetMapping("/suspend")
    @ResponseBody
    public String suspend(@RequestParam String email ){
        Admin a=new Admin();
        int flag=0;
        for (int i = 0; i < dataSystem.drivers.size(); i++) {
            if (email.equals(dataSystem.drivers.get(i).getEmail())) {
                a.suspend(dataSystem.drivers.get(i));
                flag=1;
            }
        }
        for (int i = 0; i < dataSystem.users.size(); i++) {
            if (email.equals(dataSystem.users.get(i).getEmail())) {
                a.suspend(dataSystem.users.get(i));
                flag=1;
            }
        }
        if(flag==1)
            return " Suspended";
        else
            return " Email Not Founded ";

    }
    @GetMapping("/get_pendingList")
    public ArrayList<Driver> pendList(){
        Admin a=new Admin();
        return dataSystem.getPendingList();

    }
    @GetMapping("/verify")
    public String verify(@RequestParam String nationalId ){
        Admin a=new Admin();
        int flag=0;
        for (int i = 0; i < dataSystem.getPendingList().size(); i++) {
            if (nationalId.equals(dataSystem.getPendingList().get(i).getNationalId())) {
                a.verifyDriverRegistration(dataSystem.getPendingList().get(i));
                // dataSystem.pendingList.remove(dataSystem.pendingList.get(i));
                dataSystem.removePendingDrivers(dataSystem.getPendingList().get(i));
                flag=1;
            }
        }
        if(flag==1)
           return (" verified! ");
        else
            return (" National ID Not Founded ");

    }


    @GetMapping("/UnSuspend")
    @ResponseBody
    public String UnSuspend(@RequestParam String email ){
        Admin a=new Admin();
        int flag=0;
        for (int i = 0; i < dataSystem.drivers.size(); i++) {
            if (email.equals(dataSystem.drivers.get(i).getEmail())) {
                a.unSuspend(dataSystem.drivers.get(i));
                flag=1;
            }
        }
        for (int i = 0; i < dataSystem.users.size(); i++) {
            if (email.equals(dataSystem.users.get(i).getEmail())) {
                a.unSuspend(dataSystem.users.get(i));
                flag=1;
            }
        }
        if(flag==1)
            return "UnSuspended";
        else
            return " Email Not Founded ";

    }

    @GetMapping("/ShowEvents")
    public ArrayList<EventInfo> ShowEvents(){
        Admin a=new Admin();
        return a.printEvents(dataSystem);
    }

    @PostMapping(path="/AddDiscounts", consumes = "application/json", produces = "application/json")
    public ArrayList<Discount> AddDiscounts(@RequestBody Discount dis)
    {
        Admin a=new Admin();

        return a.makeDiscount(dis.getCity(),dis.getDiscount(),dataSystem);
    }

}
