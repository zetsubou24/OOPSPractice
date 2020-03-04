import java.util.ArrayList;

//Interface with a default method
interface Gaming {
    default void playGame(){
        System.out.println("Playing a game");
    }
}

//Parent class
abstract class Laptop{
    private int LaptopID;
    protected String Laptopname;
    private int RAMSize;
    private boolean switchedOn = false;

    private int generateRandomID(){
        return (int)Math.random();
    }

    //Compile-time Polymorphism - Constructor Overloading
    public Laptop(){
        this.LaptopID = generateRandomID();
        this.Laptopname = "Unnamed";
    }

    //Compile-time Polymorphism - Constructor Overloading
    public Laptop(int LaptopID, String LaptopName){
        this.LaptopID = LaptopID;
        this.Laptopname = LaptopName;
    }

    public void bootScreen(){
        System.out.println("Welcome to the system");
    }

    //Encapsulation - Getter Method
    public boolean isSwitchedOn() {
        return switchedOn;
    }

    //Encapsulation - Setter Method
    public void setSwitchedOn(boolean switchedOn) {
        this.switchedOn = switchedOn;
    }

    //Abstraction
    public void turnOn(){
        if(isSwitchedOn() == false) {
            setSwitchedOn(true);
            System.out.println("System with ID:" + LaptopID + " has been booted");
            bootScreen();
        }
        else {
            System.out.println("System with ID:" + LaptopID + " is already switched on");
        }
    }

    //Abstraction
    public void turnOff(){
        if(isSwitchedOn() == true) {
            setSwitchedOn(false);
            System.out.println("System with ID:" + LaptopID + " has been turned off");
        }
        else {
            System.out.println("System with ID:" + LaptopID + " is already switched off");
        }
    }
}

//Inheritance
class DELL extends Laptop implements Gaming{
    public DELL(int LaptopID, String LaptopName){
        super(LaptopID, LaptopName);
    }

    //Runtime Polymorphism - Method Overriding;
    @Override
    public void bootScreen() {
        System.out.println("Welcome to DELL");
    }

    @Override
    public void playGame() {
        if(isSwitchedOn()) {
            System.out.println("Playing a game on " + this.Laptopname);
        }
        else {
            System.out.println(this.Laptopname + " is switched off, can't play a game");
        }
    }

}

//Inheritance
class Mac extends Laptop{
    public Mac(int LaptopID, String LaptopName){
        super(LaptopID, LaptopName);
    }

    //Runtime Polymorphism - Method Overriding;
    @Override
    public void bootScreen() {
        System.out.println("Welcome to Mac");
    }
}

//Factory
class LaptopFactory{
    public static Laptop createLaptop(String type, int LaptopID, String LaptopName){
        switch(type){
            case "DELL" : return new DELL(LaptopID, LaptopName);
            case "Mac" : return new Mac(LaptopID, LaptopName);
        }
        return null;
    }
}

//Aggregation + Singleton
class LaptopList {
    private static LaptopList instance;
    private static ArrayList<Laptop> LaptopArrayList = new ArrayList<>();

    private LaptopList(){
    }

    public static LaptopList getInstance(){
        if(instance==null){
            instance = new LaptopList();
        }
        return instance;
    }
    public static void addLaptop(Laptop LaptopObject){
        LaptopArrayList.add(LaptopObject);
    }

    public static ArrayList<Laptop> getLaptops(){
        return LaptopArrayList;
    }

    public static void shutdownAllLaptops(){
        System.out.println("Shutting down all laptops");

        for (Laptop LaptopObject: LaptopArrayList) {
            LaptopObject.turnOff();
        }
    }

    public static void turnOnAllLaptops(){
        System.out.println("Turning on all laptops");

        for (Laptop LaptopObject: LaptopArrayList) {
            LaptopObject.turnOn();
        }
    }

}

//Main Driver Class
public class OOPSDemo {
    public static void main(String args[]) {
        LaptopList UserLaptops = LaptopList.getInstance();

        UserLaptops.addLaptop(LaptopFactory.createLaptop("DELL",1, "MyDELL"));
        UserLaptops.addLaptop(LaptopFactory.createLaptop("Mac",2, "MyMac"));

        UserLaptops.turnOnAllLaptops();
        ((Gaming)UserLaptops.getLaptops().get(0)).playGame();
        UserLaptops.shutdownAllLaptops();
    }
}

