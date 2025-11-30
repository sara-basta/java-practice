import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManagementApp {
    private ArrayList<Vehicule> vehicles;
    private Scanner scanner;

    public VehicleManagementApp() {
        this.vehicles = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        VehicleManagementApp app = new VehicleManagementApp();
        app.run();
    }

    public void run() {
        while(true) {
            displayMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            if(option==13) {
                System.out.println("Thank you for your visit!");
                scanner.close();
                break;
            }
            handleMenuOption(option);

        }
    }

    private void displayMenu() {
        System.out.println("\n========== Vehicle Management System ==========");
        System.out.println("1. Create Car");
        System.out.println("2. Create Bike");
        System.out.println("3. Create Truck");
        System.out.println("4. Start Vehicle");
        System.out.println("5. Stop Vehicle");
        System.out.println("6. Accelerate Vehicle");
        System.out.println("7. Brake Vehicle");
        System.out.println("8. Display Vehicle Info");
        System.out.println("9. Refuel Vehicle");
        System.out.println("10. Car/Bike Features");
        System.out.println("11. Truck Features");
        System.out.println("12. List All Vehicles");
        System.out.println("13. Exit");
        System.out.println("===============================================");
        System.out.print("Choose an option: ");
    }

    private void handleMenuOption(int option) {
        switch (option) {
            case 1: createCar(); break;
            case 2: createBike(); break;
            case 3: createTruck(); break;
            case 4: startVehicle(); break;
            case 5: stopVehicle(); break;
            case 6: accelerateVehicle(); break;
            case 7: brakeVehicle(); break;
            case 8: displayVehicleInfo(); break;
            case 9: refuelVehicle(); break;
            case 10: carBikeFeatures(); break;
            case 11: truckFeatures(); break;
            case 12: listAllVehicles(); break;
            default: System.out.println("Invalid option!");

        }
    }

    private void createCar() {
        System.out.print("Enter car brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter number of doors: ");
        int numDoors = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter transmission (Manual/Automatic): ");
        String transmission = scanner.nextLine();

        Car car = new Car(brand,model,year,color,numDoors,transmission);
        vehicles.add(car);

        System.out.println("Car created successfully!");
    }

    private void createBike() {
        System.out.print("Enter bike brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter type (Sport/Cruiser/Touring): ");
        String type = scanner.nextLine();
        System.out.print("Enter number of gears: ");
        int gears = scanner.nextInt();
        scanner.nextLine();

        Bike bike = new Bike(brand, model, year, color, type, gears);
        vehicles.add(bike);
        System.out.println("Bike created successfully!");
    }

    private void createTruck() {
        System.out.print("Enter truck brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter cargo capacity (kg): ");
        double cargoCapacity = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter number of axles: ");
        int numAxles = scanner.nextInt();
        scanner.nextLine();

        Truck truck = new Truck(brand, model, year, color, cargoCapacity, numAxles);
        vehicles.add(truck);
        System.out.println("Truck created successfully!");
    }

    private Vehicule selectVehicle() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return null;
        }
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicule v = vehicles.get(i);
            System.out.println((i + 1) + ". " + v.getBrand() + " " + v.getModel() +
                    " (" + v.getVehicleType() + ")");
        }
        System.out.println("Enter the vehicle you want to display : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice<1 || choice>vehicles.size()) return null;
        return vehicles.get(choice-1);
    }

    private void startVehicle() {
        Vehicule v = selectVehicle();
        if (v != null) {
            v.start();
        }
    }

    private void stopVehicle() {
        Vehicule v = selectVehicle();
        if(v != null) v.stop();
    }

    private void accelerateVehicle() {
        Vehicule v = selectVehicle();
        if (v == null) return;

        System.out.print("Enter speed increase (km/h): ");
        double speedIncrease = scanner.nextDouble();
        scanner.nextLine();

        v.accelerate(speedIncrease);
        System.out.printf("Current speed: %.1f km/h | Fuel: %.1f%%\n", v.getSpeed(), v.getFuelLevel());
    }

    private void brakeVehicle() {
        Vehicule v = selectVehicle();
        if (v == null) return;

        System.out.print("Enter speed decrease (km/h): ");
        double speedDecrease = scanner.nextDouble();
        scanner.nextLine();

        v.brake(speedDecrease);
        System.out.printf("Current speed: %.1f km/h\n", v.getSpeed());

    }

    private void displayVehicleInfo() {
        Vehicule v = selectVehicle();
        if (v == null) return;

        v.displayInfo();

        if (v instanceof Car) {
            ((Car) v).displayCarInfo();
        } else if (v instanceof Bike) {
            ((Bike) v).displayBikeInfo();
        } else if (v instanceof Truck) {
            ((Truck) v).displayTruckInfo();
        }
    }

    private void refuelVehicle() {
        Vehicule v = selectVehicle();
        if (v != null) {
            v.refuel();
        }
    }

    private void carBikeFeatures() {
        Vehicule v = selectVehicle();
        if (v == null) return;

        if (v instanceof Car) {
            carSpecificFeatures((Car) v);
        } else if (v instanceof Bike) {
            bikeSpecificFeatures((Bike) v);
        } else {
            System.out.println("This vehicle is not a Car or Bike!");
        }
    }

    private void carSpecificFeatures(Car car) {
        System.out.println("\n--- Car Features ---");
        System.out.println("1. Honk");
        System.out.println("2. Turn ON Headlights");
        System.out.println("3. Turn OFF Headlights");
        System.out.println("4. Open Trunk");
        System.out.println("5. Close Trunk");
        System.out.print("Choose: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: car.honk(); break;
            case 2: car.turnOnHeadLights(); break;
            case 3: car.turnOffHeadLights(); break;
            case 4: car.openTrunk(); break;
            case 5: car.closeTrunk(); break;
            default: System.out.println("Invalid!");
        }
    }

    private void bikeSpecificFeatures(Bike bike) {
        System.out.println("\n--- Bike Features ---");
        System.out.println("1. Honk");
        System.out.println("2. Turn ON Headlight");
        System.out.println("3. Turn OFF Headlight");
        System.out.println("4. Wheelie");
        System.out.println("5. Attach Sidecar");
        System.out.println("6. Detach Sidecar");
        System.out.print("Choose: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: bike.honk(); break;
            case 2: bike.turnOnHeadLights(); break;
            case 3: bike.turnOffHeadLights(); break;
            case 4: bike.wheelie(); break;
            case 5: bike.attachSidecar(); break;
            case 6: bike.detachSidecar(); break;
            default: System.out.println("Invalid!");
        }
    }

    private void truckFeatures() {
        Vehicule v = selectVehicle();
        if (v == null) return;

        if (v instanceof Truck) {
            truckSpecificFeatures((Truck) v);
        } else {
            System.out.println("This vehicle is not a Truck!");
        }
    }

    private void truckSpecificFeatures(Truck truck) {
        System.out.println("\n--- Truck Features ---");
        System.out.println("1. Load Cargo");
        System.out.println("2. Unload Cargo");
        System.out.println("3. Check Cargo");
        System.out.println("4. Honk");
        System.out.print("Choose: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter weight to load (kg): ");
                double loadWeight = scanner.nextDouble();
                scanner.nextLine();
                truck.loadCargo(loadWeight);
                break;
            case 2:
                System.out.print("Enter weight to unload (kg): ");
                double unloadWeight = scanner.nextDouble();
                scanner.nextLine();
                truck.unloadCargo(unloadWeight);
                break;
            case 3:
                System.out.printf("Remaining capacity: %.1f kg\n", truck.getRemainingCapacity());
                break;
            case 4:
                truck.honk();
                break;
            default:
                System.out.println("Invalid!");
        }
    }

    private void listAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        System.out.println("\n========== All Vehicles ==========");
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicule v = vehicles.get(i);
            System.out.printf("%d. %s %s (%s) - Speed: %.1f km/h, Fuel: %.1f%%\n",
                    i + 1, v.getBrand(), v.getModel(), v.getVehicleType(),
                    v.getSpeed(), v.getFuelLevel());
        }
        System.out.println("==================================");
    }
}
