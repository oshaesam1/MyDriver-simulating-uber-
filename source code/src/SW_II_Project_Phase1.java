import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

import static sun.swing.MenuItemLayoutHelper.max;


public class SW_II_Project_Phase1 {


    public static void main(String[] args) {
        DataSystem dataSystem = DataSystem.getInstance();
        Admin admin = new Admin();

        DriverController d1=new DriverController();
        //Driver d1 = new Driver();

        d1.register("Mohamed", "123", "m123", "01123369545", "1235", "69446", dataSystem);
        d1.getD().getFavAreas().addFavArea("cairo");
        d1.getD().getFavAreas().addFavArea("haram");
       // Driver d2 = new Driver();
        DriverController d2=new DriverController();
        d2.register("Ramy", "1234", "r123", "01123369545", "654", "6469974", dataSystem);
        d2.getD().getFavAreas().addFavArea("helwan");
        d2.getD().getFavAreas().addFavArea("maadi");
       // Driver d3 = new Driver();
        DriverController d3=new DriverController();
        d3.register("Joo", "1235", "jo123", "0102339547", "93", "6932", dataSystem);
        d3.getD().getFavAreas().addFavArea("cairo");

        UserController u1=new UserController();
        //User u1 = new User();
        u1.register("Marwa", "000", "mro", "okvgc", LocalDate.parse("1999-05-01"),dataSystem);
        u1.rateDriver(d1.getD(), 3);
        u1.rateDriver(d1.getD(), 4);
        u1.rateDriver(d3.getD(), 5);
        //User u2 = new User();
        UserController u2=new UserController();
        u2.register("Salma", "00", "s_12", "oksalma",LocalDate.parse("1998-01-01"), dataSystem);
        u2.rateDriver(d3.getD(), 5);
        u2.rateDriver(d2.getD(), 3);
        //dataSystem.printAllData();

        while (true) {
            System.out.println("1-user 2-driver 3-admin");
            Scanner in = new Scanner(System.in);
            String choose = in.next();
            ///////////////////User manu//////////////////
            if ("1".equals(choose)) {
                UserController userC=new UserController();
                User user = null;
                System.out.println("1-login 2-register 3-End");
                Scanner in2 = new Scanner(System.in);
                String choose2 = in2.next();
                if ("1".equals(choose2)) {
                    System.out.println("Enter Name and Password");
                    Scanner in3 = new Scanner(System.in);
                    String name = in3.nextLine();
                    String password = in3.nextLine();
                    user = dataSystem.loginUser(name, password);
                    userC.setU(user);

                } else if ("2".equals(choose2)) {
                    System.out.println("Enter Name and Password and Email and Mobile Number and Your Birthday Date");
                    Scanner in3 = new Scanner(System.in);
                    String name = in3.nextLine();
                    String password = in3.nextLine();
                    String email = in3.nextLine();
                    String number = in3.nextLine();
                    String date = in3.nextLine(); //"2011-09-01"
                    user = new User();
                    userC.register(name, password, email, number, LocalDate.parse(date),dataSystem);
                } else {
                    continue;
                }
                // dataSystem.printAllData();
                if (user != null) {
                    if (userC.getU().suspendStatus == true) {
                        System.out.println("Sorry you are suspended");
                        continue;
                    } else {
                        System.out.println("\n\nHello " + userC.getU().userName);
                        while (true) {
                            System.out.println("\n************Please Choice Operation");
                            System.out.println("0-End \n1-Request Ride \n"
                                    + "2-Get Offers");
                            String choose3 = in2.next();
                            if ("0".equals(choose3))
                                break;
                            if ("1".equals(choose3)) {
                                System.out.println("Enter Source and Destination");
                                Scanner in3 = new Scanner(System.in);
                                String source = in3.nextLine();
                                String destinaton = in3.nextLine();
                                System.out.println("Enter number of passenger in this ride ");
                                Scanner in3_1 = new Scanner(System.in);
                                int num = in3_1.nextInt();
                                userC.requestRide(source, destinaton,num ,dataSystem);
                            }
                            if ("2".equals(choose3)) {
                                ArrayList<RideOffer> rideOfferses = userC.getU().getRideOfferses();
                                for (int i = 0; i < rideOfferses.size(); i++) {
                                    System.out.println(i + " - "+rideOfferses.get(i).getMsg()+" --> Price: "  + rideOfferses.get(i).getPrice() + "\n"
                                            + "Driver Name: " + rideOfferses.get(i).getDriver().userName);
                                }
                                if (rideOfferses.size() >= 1) {
                                    System.out.println("choose offer");
                                    Scanner input = new Scanner(System.in);
                                    int index_offer = input.nextInt();
                                    while (true) {
                                        System.out.println("0- cancel \n"
                                                + "1-Accept Offer \n"
                                                + "2-Driver Average Rate\n");
                                        input = new Scanner(System.in);
                                        String Uchoose = input.next();
                                        if ("1".equals(Uchoose)) {
                                            userC.getU().addDrivers(rideOfferses.get(index_offer).getDriver());
                                            userC.getU().getRideOfferses().get(index_offer).setOfferStatus(true);
                                            ArrayList <RideOffer> temp= new ArrayList<>();
                                            userC.getU().setRideOfferses(temp);
                                            userC.acceptOffer(rideOfferses.get(index_offer).getDriver(), rideOfferses.get(index_offer).getPrice(), dataSystem);
                                            
                                            System.out.println(" 0-Rate your Driver : ");
                                            Scanner inp = new Scanner(System.in);
                                            String st = inp.nextLine();
                                            if ("0".equals(st)) {
                                                System.out.println(userC.getU().get_MyDriver());
                                                System.out.println(" Enter rate : ");
                                                Scanner in1 = new Scanner(System.in);
                                                int rate = in1.nextInt();
                                                userC.rateDriver(rideOfferses.get(index_offer).getDriver(), rate);

                                            } else {
                                                break;
                                            }
                                        }
                                        if ("2".equals(Uchoose)) {
                                            System.out.println("*Average Rate: " + rideOfferses.get(index_offer).getDriver().getRate().getAverageRate());
                                        } else
                                            break;
                                    }
                                } else {
                                    System.out.println("No Offer Yet");
                                    continue;
                                }


                            }
                        }

                    }
                }
            }
            //////////////////Driver menu/////////////
            if ("2".equals(choose)) {
                DriverController driverC=new DriverController();
                Driver driver = null;
                System.out.println("1-login 2-register 3-End");
                Scanner in2 = new Scanner(System.in);
                String choose2 = in2.next();
                if ("1".equals(choose2)) {
                    System.out.println("Enter Name and Password");
                    Scanner in3 = new Scanner(System.in);
                    String name = in3.nextLine();
                    String password = in3.nextLine();
                    driver = dataSystem.loginDriver(name, password);
                    driverC.setD(driver);

                } else if ("2".equals(choose2)) {
                    System.out.println("Enter Name and Password and Email and Mobile Number and National Id and License");
                    Scanner in3 = new Scanner(System.in);
                    String name = in3.nextLine();
                    String password = in3.nextLine();
                    String email = in3.nextLine();
                    String number = in3.nextLine();
                    String nationalId = in3.nextLine();
                    String license = in3.nextLine();
                    driver = new Driver();
                    driverC.register(name, password, email, number, nationalId, license, dataSystem);
                }
                else if("3".equals(choose2))
                    continue;
                else {System.out.println(" Enter valid input!! ");
                continue;}

                //dataSystem.printAllData();

                if (driver != null ) {
                    if (driverC.getD().suspendStatus == true || driverC.getD().isPendingState()) {
                        System.out.println("Sorry you are suspended or still pended");
                        continue;
                    } else {
                        System.out.println("\n\nHello " + driverC.getD().userName);
                        while (true) {
                            System.out.println("\n************Please Choice Operation");
                            System.out.println("0-End \n1-Requested Rides\n"
                                    + "2-Add Favorite Area\n"
                                    + "3-Your Average Rate\n"
                                    + "4-Get All Rates\n"
                                    + "5-Get All Favorite Area");
                            String choose3 = in2.next();
                            if ("0".equals(choose3))
                                break;
                            if ("1".equals(choose3)) {
                                ArrayList<RideRequest> rideRequests = driverC.getD().getRideRequests();
                                for (int i = 0; i < rideRequests.size(); i++) {
                                    System.out.println(i + "- Source: " + rideRequests.get(i).getSource() + "  Destination: " + rideRequests.get(i).getDestination() + "\n");
                                }
                                if (rideRequests.size() >= 1) {
                                    System.out.println("choose Request to make offer");
                                    Scanner input = new Scanner(System.in);
                                    int index_RideRequest = input.nextInt();
                                    System.out.println("0-cancel \n1-Make Offer");
                                    input = new Scanner(System.in);
                                    String Dchoose = input.next();
                                    if ("1".equals(Dchoose)) {
                                        System.out.println("Enter Offer");
                                        input = new Scanner(System.in);
                                        int offer = input.nextInt();
                                        driverC.makeOffer(rideRequests.get(index_RideRequest).getUser(),rideRequests.get(index_RideRequest).getDestination(), offer,rideRequests.get(index_RideRequest).getNum_Passenger(), dataSystem);
                                    } else
                                        continue;
                                } else {
                                    System.out.println("No Ride Request Yet");
                                    continue;
                                }
                            }
                            if ("2".equals(choose3)) {
                                System.out.println("Enter Area");
                                Scanner in3 = new Scanner(System.in);
                                String area = in3.nextLine();
                                driverC.getD().getFavAreas().addFavArea(area);
                            }
                            if ("3".equals(choose3)) {
                                System.out.println("Your Average Rate IS : " + driverC.getD().getRate().getAverageRate());
                            }
                            if ("4".equals(choose3)) {
                                driverC.getD().getRate().printAllRates();
                            }
                            if ("5".equals(choose3)) {
                                driverC.getD().getFavAreas().returnAllAreas();
                            }
                            if("0".equals(choose3))
                                continue;
                            else System.out.println(" Enter valid input!! ");
                        }


                    }
                }
            }

            //**************admin menu*************
            if ("3".equals(choose)) {
                System.out.println("enter username and password");
                Scanner input = new Scanner(System.in);
                String username = input.nextLine();
                String password = input.nextLine();
                if (username.equals(admin.id) && password.equals(admin.password)) {
                    System.out.println("choose : 1-Suspend 2-Verify 3-UnSuspend 4-Show Ride Events 5-Add Discounts");
                    Scanner Admin_in = new Scanner(System.in);
                    String st = Admin_in.nextLine();
                    //****suspended menu********
                    if ("1".equals(st)) {
                        System.out.println(" Enter E-mail of the Suspended ");
                        Scanner sus = new Scanner(System.in);
                        String p = sus.nextLine();
                        int flag=0;
                        for (int i = 0; i < dataSystem.drivers.size(); i++) {
                            if (p.equals(dataSystem.drivers.get(i).email)) {
                                admin.suspend(dataSystem.drivers.get(i));
                                flag=1;
                            }
                        }
                        for (int i = 0; i < dataSystem.users.size(); i++) {
                            if (p.equals(dataSystem.users.get(i).email)) {
                                admin.suspend(dataSystem.users.get(i));
                                flag=1;
                            }
                        }
                        if(flag==1)
                            System.out.println(" Suspended");
                        else
                            System.out.println(" Email Not Founded ");

                    }
                    //****verify menu********
                    else if ("2".equals(st)) {
                         for (int i=0 ; i<dataSystem.pendingList.size();i++)
                         {
                             System.out.println(dataSystem.pendingList.get(i).userName+" "+dataSystem.getPendingList().get(i).getNationalId());

                         }
                        System.out.println(" Enter national-Id of verified ");
                        Scanner verify = new Scanner(System.in);
                        String id = verify.nextLine();
                        int flag=0;
                        for (int i = 0; i < dataSystem.pendingList.size(); i++) {
                            if (id.equals(dataSystem.pendingList.get(i).getNationalId())) {
                                admin.verifyDriverRegistration(dataSystem.pendingList.get(i));
                               // dataSystem.pendingList.remove(dataSystem.pendingList.get(i));
                                dataSystem.removePendingDrivers(dataSystem.pendingList.get(i));
                                flag=1;
                            }
                        }
                        if(flag==1)
                            System.out.println(" verified! ");
                        else
                            System.out.println(" National ID Not Founded ");
                        

                    }
                    //**** un suspended menu********
                    if ("3".equals(st)) {
                        System.out.println(" Enter E-mail of the Suspended ");
                        Scanner sus = new Scanner(System.in);
                        String p = sus.nextLine();
                        int flag=0;
                        for (int i = 0; i < dataSystem.drivers.size(); i++) {
                            if (p.equals(dataSystem.drivers.get(i).email)) {
                                admin.unSuspend(dataSystem.drivers.get(i));
                                flag=1;
                            }
                        }
                        for (int i = 0; i < dataSystem.users.size(); i++) {
                            if (p.equals(dataSystem.users.get(i).email)) {
                                admin.unSuspend(dataSystem.users.get(i));
                                flag=1;
                            }
                        }
                        if(flag==1)
                            System.out.println("UnSuspended");
                        else
                            System.out.println(" Email Not Founded ");

                    }
                    //else System.out.println(" Enter valid input!! ");
                    //**** show event********
                    if ("4".equals(st)) {
                        
                        admin.printEvents(dataSystem);
                    }
                    if ("5".equals(st)) {
                        System.out.println(" Enter country: ");
                        Scanner inputA = new Scanner(System.in);
                        String country=inputA.nextLine();
                        System.out.println(" Enter discount: ");
                        Scanner inputA2 = new Scanner(System.in);
                        double discount=inputA2.nextDouble();
                        admin.makeDiscount(country,discount,dataSystem);
                    }
                }

            }
           // else System.out.println(" Enter valid input!! ");
        }
    }


}