import java.util.ArrayList;

//Interface with a default method
interface Gaming {
    default void playGame(){
        System.out.println("Playing a game");
    }
}

//Parent class
class Laptop{
    private int LaptopID;
    private String Laptopname;
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

    public void BootScreen(){
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
            System.out.println("System with ID:" + LaptopID + "has been booted");
            BootScreen();
        }
        else {
            System.out.println("System with ID:" + LaptopID + "is already switched on");
        }
    }

    //Abstraction
    public void turnOff(){
        if(isSwitchedOn() == true) {
            setSwitchedOn(false);
            System.out.println("System with ID:" + LaptopID + " has been turned off");
        }
        else {
            System.out.println("System with ID:" + LaptopID + "is already switched off");
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
    public void BootScreen() {
        System.out.println("Welcome to DELL");
    }
}

//Inheritance
class Mac extends Laptop{
    public Mac(int LaptopID, String LaptopName){
        super(LaptopID, LaptopName);
    }

    //Runtime Polymorphism - Method Overriding;
    @Override
    public void BootScreen() {
        System.out.println("Welcome to Mac");
    }
}

//Aggregation
class LaptopList {
    private static ArrayList<Laptop> LaptopArrayList = new ArrayList<>();

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
        LaptopList UserLaptops = new LaptopList();


        DELL dellObject = new DELL(1, "MyDELL");
        Mac macObject = new Mac(2, "MyMac");

        UserLaptops.addLaptop(dellObject);
        UserLaptops.addLaptop(macObject);

        dellObject.turnOn();
        macObject.turnOn();

        dellObject.playGame();

        UserLaptops.shutdownAllLaptops();
    }
}

