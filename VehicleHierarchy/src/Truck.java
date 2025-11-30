public class Truck extends Vehicule implements VehiculeOperations{

    private double cargoCapacity;
    private double currentCargo;
    private int numAxles;
    private boolean cargoBoxOpen;
    private boolean headlightsOn;


    public Truck(String brand, String model, int year, String color, double cargoCapacity, int numAxles){
        super(brand,model,year,color);
        this.cargoCapacity=cargoCapacity;
        this.numAxles=numAxles;
        this.headlightsOn=false;
        this.cargoBoxOpen=false;
    }

    @Override
    public void start(){
        System.out.println(getModel()+"Truck has started!");
    }

    @Override
    public void stop(){
        System.out.println(getModel()+"Truck has stopped!");
        setSpeed(0);
    }
    @Override
    public  void accelerate(double speedIncrease) {
        if(getSpeed()+speedIncrease>120) {
            System.out.println("You've reached the maximum speed of 120km/h !");
        }

        setSpeed(getSpeed()+speedIncrease);

        setFuelLevel(getFuelLevel()-(speedIncrease/20));
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
        return "Truck";
    }

    @Override
    public  double calculateFuelConsumption() {
        return (120-getSpeed()/30);

    }

    @Override
    public void honk(){
        System.out.println("Ring! Ring!");
    }

    @Override
    public void turnOnHeadLights(){
        System.out.println("Headlights turned ON!");
        headlightsOn = true;
    }
    @Override
    public void turnOffHeadLights(){
        System.out.println("Headlights turned OFF!");
        headlightsOn=false;
    }

    @Override
    public void openTrunk(){
        System.out.println("Cannot open trunk - bikes have no trunk!");
    }

    @Override
    public void closeTrunk(){
        System.out.println("No trunk to close");
    }


    public void loadCargo(double weight)
    {
        currentCargo+=weight;
        if(currentCargo>cargoCapacity){
            System.out.println("Cannot exceed cargo capacity.");

        }
        System.out.println("Loaded "+weight+" kg.Current cargo : "+currentCargo+" kg.");
    }
    public void unloadCargo(double weight){
        currentCargo-=weight;
        System.out.println("Loaded "+weight+" kg.Current cargo : "+currentCargo+" kg.");

    }

    public void displayTruckInfo(){
        super.displayInfo();
        System.out.println("Capacity: " + cargoCapacity + " | current weight: " + currentCargo );
    }

    public double getRemainingCapacity(){
        return cargoCapacity-currentCargo;
    }
}
