import java.time.LocalDate;
import java.util.ArrayList;
import javax.lang.model.element.NestingKind;



class RideOffer implements Subject{
    private double price;
    private Driver driver;
    private boolean offerStatus;
    private ArrayList<Observer>observers;
    private String city;
    private String msg;

    public RideOffer() {
        offerStatus=false;
        observers=new ArrayList<Observer>();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg=msg;
    }
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public boolean isOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(boolean offerStatus) {
        this.offerStatus = offerStatus;
    }
    public void makeOffer(User user, Driver aThis , double price, String des, int num_passenger,DataSystem database) {
        driver=aThis;
        
        double discount=haveDiscount(user, aThis,des, database);
        discount+=haveDiscount(num_passenger);
        this.price=price-((discount/100)*price);
        
        notifyUser(user,database);
    }
     private double haveDiscount(int num_passenger){
         double discount =0;
         
         if(num_passenger>=2)
         {  
             discount=5;
             msg+="\nyou got 5% discount";
         }
         return discount;
     }
     
    private double haveDiscount(User user, Driver aThis ,String des,DataSystem database){
        double discount =0;
        msg="";
        for(int i=0;i<database.specialCountries.size();i++)
        {
            if(des.equals(database.specialCountries.get(i).getCity()))
            {
                //System.out.println("RideOffer.haveDiscount()");
                discount=database.specialCountries.get(i).getDiscount();
                msg=database.specialCountries.get(i).getMsg();
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////
        if(database.rides.size()<=0)
        {
            discount+=10;
            msg+="\nCongrats it is first ride for you, you got 10% discount on this ride";
        }
        for(int i=0;i<database.rides.size();i++)
        {
            if(user.email.equals(database.rides.get(i).getUser().email) && aThis.email.equals(database.rides.get(i).getDriver().email))
            {
             
                if(database.rides.get(i).isState()) //false if ride complete
                {
                    discount+=10;
                    msg+="\nCongrats it is first ride for you, you got 10% discount on this ride";
                    
                }
            }
            if(!user.email.equals(database.rides.get(i).getUser().email) && !aThis.email.equals(database.rides.get(i).getDriver().email))
            {
      
                if(database.rides.get(i).isState()) //false if ride complete
                {
                    discount+=10;
                    msg+="\nCongrats it is first ride for you, you got 10% discount on this ride";
                    
                }
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////
        LocalDate d=LocalDate.now();
        //System.out.println("RideOffer.haveDiscount()"+user.birthday.getDayOfMonth()+"  "+user.birthday.getMonthValue());
        if(user.birthday.getDayOfMonth()==d.getDayOfMonth() && user.birthday.getMonthValue()==d.getMonthValue())
        {
            discount+=10;
            msg+="\nHappy Birtday you got 10% discount";
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        for(int i=0;i<database.publicHolidays.size();i++)
        {
            if(database.publicHolidays.get(i).getDayOfMonth()==d.getDayOfMonth() && database.publicHolidays.get(i).getMonthValue()==d.getMonthValue())
            {
                discount+=5;
                msg+="\nIt is public holiday, you got 5% discount";
                break;
            }
        }
        return discount;
    }
    public void notifyUser(User user,DataSystem dataSystem){
        observers= new ArrayList<Observer>();
        for(int i=0;i<dataSystem.users.size();i++)
        {
            if((user.email.equals(dataSystem.users.get(i).email)) &&(user.mobilNumber.equals(dataSystem.users.get(i).mobilNumber))){
                dataSystem.users.get(i).addRideOffer(this);
         
                subscribe(dataSystem.users.get(i));

            }
        }
        notify(dataSystem);

    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(DataSystem dataSystem) {
        for(Observer ob:observers )
        {
            ob.update(null,this);
        }
    }


}

