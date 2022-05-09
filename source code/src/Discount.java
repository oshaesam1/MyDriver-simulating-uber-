/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hoda
 */
public class Discount {
    private String city;
    private double discount;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    Discount(String country, double discount) {
        this.city=country;
        this.discount=discount;
        msg="Congrats you have "+discount+"% discount on this ride ";
    }

    public Discount() {
         msg=" ";
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getDiscount() {
        return discount;
        
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
