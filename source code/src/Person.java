
import java.time.LocalDate;

public class Person {
    protected String userName;
    protected String email;
    protected String mobilNumber;
    protected String password;
    protected boolean suspendStatus;
    protected LocalDate birthday;
    // protected DB database;

    public Person(String userName, String email,String mobilNumber,String password){
        this.userName=userName;
        this.mobilNumber=mobilNumber;
        this.password=password;
        this.email=email;
        suspendStatus=false;
    }
    public  Person(){
        suspendStatus=false;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
    

}
