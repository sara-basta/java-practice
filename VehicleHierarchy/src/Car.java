public class Car extends Vehicule implements VehiculeOperations{
    private int numDoors;
    private String transmission;
    private boolean trunkOpen;
    private boolean headlightsOn;

    public Car(String brand, String model, int year, String color,int numDoors, String transmission) {
        super(brand, model, year, color);
        this.numDoors = numDoors;
        this.transmission = transmission;
        this.trunkOpen = false;
        this.headlightsOn = false;
    }

    @Override
    public void start(){
        System.out.println(getModel()+"Car has started!");
    }

    @Override
    public void stop(){
        System.out.println(getModel()+"Car has stopped!");
        setSpeed(0);
    }
    @Override
    public  void accelerate(double speedIncrease) {
        if(getSpeed()+speedIncrease>200) {
            System.out.println("You've reached the maximum speed of 200km/h !");
        }
        setSpeed(getSpeed()+speedIncrease);

        setFuelLevel(getFuelLevel()-(speedIncrease/50));
    }

    @Override
    public void brake(double speedDecrease) {
        if(speedDecrease<0) {
            System.out.println("Invalid. Input should be >0.");
            return;
        }
        setSpeed(getSpeed()-speedDecrease);
    }

    @Override
    public  String getVehicleType(){
        return "Car";
    }

    @Override
    public  double calculateFuelConsumption() {
        return (200-getSpeed()/50);

    }
    @Override
    public void honk(){
        System.out.println("Beep! Beep!");
    }

    @Override
    public void turnOnHeadLights(){
        System.out.println("Headlights turned ON!");
        headlightsOn=true;
    }
    @Override
    public void turnOffHeadLights(){
        System.out.println("Headlights turned OFF!");
        headlightsOn=false;
    }

    @Override
    public void openTrunk(){
        System.out.println("Trunk OPEN!");
        trunkOpen=true;
    }

    @Override
    public void closeTrunk(){
        System.out.println("Trunk CLOSED!");
        trunkOpen=false;
    }

    public void displayCarInfo(){
        super.displayInfo();
        System.out.println("Number of doors : "+numDoors+" Transmission : "+transmission);
    }


}
