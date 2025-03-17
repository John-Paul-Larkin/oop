// John Paul Larkin
// c00001754
// 13/3/25
// OOP CA 2

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ABSTRACT CLASS - Vehicle
// Demonstrates abstraction (this classs cannot be directly instantiated)
// and encapsulation (private fields with public getters/setters)
// contains an abstract method that child classes(Car and Truck) must implement.
abstract class Vehicle {

    // Vehicles have a make, the year in which they were made, and mileage.
    // these private fields have public getters/setters
    private String make;
    private int year;
    private int mileage;
 
    // constructor - to initialise the fields
    public Vehicle(String make, int year, int mileage) {
        this.make = make;
        this.year = year;
        this.mileage = mileage;
    }

    // getters for the private fields
    public String getMake() {
        return make;
    }
    public int getYear() {
        return year;
    }
    public int getMileage() {
        return mileage;
    }

    // setters for the private fields
    public void setMake(String make) {
        this.make = make;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    // abstract method (must be implemented by child classes)  - demonstrates polymorphism
    public abstract int getNumberOfWheels();

    // This method can be overridden by car and Truck subclasses 
    // not abstract because it has a concrete implementation - does not need to be overrideen in child classes
    // although it is overridden in the examples
    public String getVehicleType() {
        return "Generic Vehicle";
    }

    // helper method to check if vehicle is older than 10 years 
    public boolean isOlderThanTenYears() {
        return (2025 - this.year) > 10;
    }
}

// child calss - Car
// Demonstrates inheritance and polymorphism
// inherits from Vehicle class and implements the abstract method from Vehicle
// overrides the getVehicleType method from Vehicle
class Car extends Vehicle {

    private int numberOfDoors;

    // constructor - to initialise the fields
    public Car(String make, int year, int mileage, int numberOfDoors) {
        // super() is used to call the constructor of the vehicle class 
        super(make, year, mileage);
        this.numberOfDoors = numberOfDoors;
    }

    // getter for the field
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    // setter for the field
    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    // overrides the absract method from Vehicle class
    @Override
    public int getNumberOfWheels() {
        return 4;
    }

    // overrides the concrete method from Vehicle class - override return of  "generic vehicle"
    @Override
    public String getVehicleType() {
        return "Car";
    }
}

// CHILD CLASS - Truck
// Similar to Car class - demonstrates inheritance and polymorphism
// inherits from Vehicle class and implements the abstract method from Vehicle
// overrides the getVehicleType method from Vehicle
class Truck extends Vehicle {

    // specific to trucks
    private int loadCapacity;

    // constructor 
    public Truck(String make, int year, int mileage, int loadCapacity) {
        super(make, year, mileage);
        this.loadCapacity = loadCapacity;
    }

    // get and set private field
    public int getLoadCapacity() {
        return loadCapacity;
    }
    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public int getNumberOfWheels() {
        return 6;
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }
}

//  Driver class demonstrates encapsulation through private attributes
//  and corresponding getter/setter methods.
class Driver {

    // Drivers have a name, birth date, and license ID
    private String name;
    private String birthDate;
    private String licenseID;

    // Drivers have a list of vehicles
    private List<Vehicle> vehicles = new ArrayList<>();

    // constructor - to initialise the fields
    public Driver(String name, String birthDate, String licenseID) {
        this.name = name;
        this.birthDate = birthDate;
        this.licenseID = licenseID;
    }

    // getters for the private fields
    public String getName() {
        return name;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public String getLicenseID() {
        return licenseID;
    }

    // setters for the private fields
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }

    // register a vehicle - add a vehicle to the list of vehicles
    public void registerVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // returns the list of vehicles for this driver
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}

// Main application class
public class VehicleRegister {

    // main method - program entry point
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // create a new VehicleRegistry object
        VehicleRegistry vehicleRegistry = new VehicleRegistry();

        boolean isExit = false;
        // loop until the user enters 5 and isExit is set to true
        while(!isExit) {
            // print the menu
            System.out.println("1) Add driver");
            System.out.println("2) Regster a new vehicle");
            System.out.println("3) Get the list of vehicles for a driver");
            System.out.println("4) Get the names of all drivers that have vehicles older than 10 years");
            System.out.println("5) Exit");
            // get the users input
            String userInput = scanner.nextLine();


            switch(userInput) {
                case "1":
                    // Add a new driver
                    System.out.print("Name :");
                    String name = scanner.nextLine();
                    System.out.print("Date of birth ");
                    String dob = scanner.nextLine();
                    System.out.print("License ID: ");
                    String licenseID = scanner.nextLine();

                    // create a new Driver object
                    Driver newDriver = new Driver(name, dob, licenseID);
                    // add the new driver to the registry   
                    vehicleRegistry.addDriver(newDriver);
                    break;

                case "2":
                    // Register a new vehicle for a driver
                    System.out.print("Enter driver name ");
                    String driverName = scanner.nextLine();
                    // find the driver in the registry - findDriverByName() is a public method of the VehicleRegistry class
                    // it returns a Driver object if found, otherwise it returns null
                    Driver driver = vehicleRegistry.findDriverByName(driverName);

                    // if the driver is not found, print a message and break
                    if(driver == null) {
                        System.out.println("not found.");
                        break;
                    }

                    // prompt the user to enter the vehicle type
                    System.out.println("Choose vehicle type: (1) Car, (2) Truck");
                    String vehicleType = scanner.nextLine();

                    // prompt the user to enter the vehicle details
                    System.out.print("Enter vehicle make: ");
                    String make = scanner.nextLine();

                    System.out.print("Enter vehicle year ");
                    int year = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter vehcile mileage: ");
                    int mileage = Integer.parseInt(scanner.nextLine());

                    if(vehicleType.equals("1")) {
                        // Car 
                        System.out.print("Enter number of doors: ");
                        int doors = Integer.parseInt(scanner.nextLine());
                        // create a new Car object
                        Car car = new Car(make, year, mileage, doors);
                        // register the car with the driver
                        // registerVehicle() is a public method of the Driver class
                        driver.registerVehicle(car);
                    } else if(vehicleType.equals("2")) {
                        // Truck
                        System.out.print("What is the load capacity of the truck? ");
                        int loadCapacity = Integer.parseInt(scanner.nextLine());
                        Truck truck = new Truck(make, year, mileage, loadCapacity);
                        driver.registerVehicle(truck);
                    } else {
                        System.out.println("Invalid vehicle type.");
                    }
                    break;

                case "3":
                    // Get the list of vehicles for a driver
                    System.out.print("Enter driver name: ");
                    String dName = scanner.nextLine();
                    List<Vehicle> vehicles = vehicleRegistry.getVehiclesForDriver(dName);
                    if(vehicles.isEmpty()) {
                        System.out.println("No vehicles found for driver " + dName);
                    } else {
                        System.out.println("Vehicles for driver " + dName + ":");
                        for(Vehicle v : vehicles) {
                            System.out.println(" - " + v.getVehicleType() +
                                    " | Make: " + v.getMake() +
                                    " | Year: " + v.getYear() +
                                    " | Mileage: " + v.getMileage());
                        }
                    }
                    break;

                case "4":
                    // Get names of drivers who have vehicles older than 10 years
                    List<String> driverNames = vehicleRegistry.getDriversWithVehiclesOlderThanTenYears();
                    if(driverNames.isEmpty()) {
                        System.out.println("No drivers found");
                    } else {
                        System.out.println("Drivers with vehicles older than 10 years:");
                        for(String dn : driverNames) {
                            System.out.println(" - " + dn);
                        }
                    }
                    break;

                case "5":
                    isExit = true;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();

    }
}


//  VehicleRegistry - this helper class has public methods
// that are used by the main class to manage the list of drivers and their vehicles
class VehicleRegistry {

    // private list of drivers
    private List<Driver> drivers = new ArrayList<>();

    // Adds a driver to the list 
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    // Finds a driver by name
    public Driver findDriverByName(String name) {
        // iterate through the list of drivers
        for(Driver driver : drivers) {
            // if the name matches, return the driver
            if(driver.getName().equalsIgnoreCase(name)) {
                return driver;
            }
        }
        // if no driver is found, return null
        return null;
    }

    // returns the list of vehicles for a specified driver
    public List<Vehicle> getVehiclesForDriver(String driverName) {
        Driver driver = findDriverByName(driverName);
        if(driver != null) {
            return driver.getVehicles();
        }
        return new ArrayList<>();
    }

    public List<String> getDriversWithVehiclesOlderThanTenYears() {

        List<String> driverNames = new ArrayList<>();
        // iterate through the list of drivers
        for(Driver d : drivers) {
            // iterate all drivers vehicles
            for(Vehicle vehicle : d.getVehicles()) {
                // if older than 10
                if(vehicle.isOlderThanTenYears()) {
                    driverNames.add(d.getName());
                    break;
                }
            }
        }
        return driverNames;
    }
}