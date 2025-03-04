// Creator from the class diagram
abstract class LogisticsCreator {
    // Factory method
    public abstract Transport createTransport();
    
    // An operation that uses the factory method
    public void planDelivery() {
        // Create the transport
        Transport transport = createTransport();
        
        // Use the transport in some operations
        System.out.println("Planning delivery using: " + transport.getClass().getSimpleName());
        transport.deliver();
    }
}

// ConcreteCreator from the class diagram
class RoadLogistics extends LogisticsCreator {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

// Another ConcreteCreator
class SeaLogistics extends LogisticsCreator {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}

// Another ConcreteCreator
class AirLogistics extends LogisticsCreator {
    @Override
    public Transport createTransport() {
        return new Airplane();
    }
}

// Product from the class diagram
interface Transport {
    void deliver();
}

// ConcreteProduct from the class diagram
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by land in a truck");
    }
}

// Another ConcreteProduct
class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by sea in a ship");
    }
}

// Another ConcreteProduct
class Airplane implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by air in an airplane");
    }
}

@SuppressWarnings("all")
public class FactoryMethod {
    public static void main(String[] args) {
        // Create different types of logistics
        LogisticsCreator roadLogistics = new RoadLogistics();
        LogisticsCreator seaLogistics = new SeaLogistics();
        LogisticsCreator airLogistics = new AirLogistics();
        
        // Use the factory method to create transport objects
        Transport truck = roadLogistics.createTransport();
        Transport ship = seaLogistics.createTransport();
        Transport airplane = airLogistics.createTransport();
        
        // Deliver using different transport types
        System.out.println("Road delivery:");
        truck.deliver();
        
        System.out.println("\nSea delivery:");
        ship.deliver();
        
        System.out.println("\nAir delivery:");
        airplane.deliver();
        
        // Demonstrate the operation method in the creator
        System.out.println("\nPlanning logistics:");
        roadLogistics.planDelivery();
        seaLogistics.planDelivery();
        airLogistics.planDelivery();
    }
}