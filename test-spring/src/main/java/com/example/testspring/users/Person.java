package com.example.testspring.users;

import java.time.LocalDate;

public class Person {

    protected String userName;
    protected String email ;
    protected String mobilNumber ;
    protected String password;
    protected String nationalId;
    protected String drivingLicense;
    protected boolean suspendStatus;
    protected LocalDate birthday ;
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
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilNumber() {
        return mobilNumber;
    }

    public void setMobilNumber(String mobilNumber) {
        this.mobilNumber = mobilNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuspendStatus() {
        return suspendStatus;
    }

    public void setSuspendStatus(boolean suspendStatus) {
        this.suspendStatus = suspendStatus;
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
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}
