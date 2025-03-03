@SuppressWarnings("all")
public class State {
    public static void main(String[] args) {
        // Create a vending machine
        VendingMachine vendingMachine = new VendingMachine(5);
        
        // Test the vending machine in different states
        System.out.println("Initial state: " + vendingMachine.getCurrentStateName());
        
        // Insert coins
        vendingMachine.insertCoin();
        vendingMachine.insertCoin();
        
        // Select product
        vendingMachine.selectProduct();
        
        // Dispense product
        vendingMachine.dispenseProduct();
        
        // Try to select without inserting coins
        vendingMachine.selectProduct();
        
        // Insert coins again
        vendingMachine.insertCoin();
        vendingMachine.insertCoin();
        
        // Select and dispense
        vendingMachine.selectProduct();
        vendingMachine.dispenseProduct();
        
        // Check if machine is out of stock
        System.out.println("\nProducts left: " + vendingMachine.getProductCount());
        
        // Refill the machine
        vendingMachine.refill(3);
        System.out.println("After refill - Products left: " + vendingMachine.getProductCount());
        System.out.println("Current state: " + vendingMachine.getCurrentStateName());
    }
}

// Context from the class diagram
class VendingMachine {
    // States
    private MachineState noCoinState;
    private MachineState hasCoinState;
    private MachineState soldState;
    private MachineState soldOutState;
    
    private MachineState currentState;
    private int productCount;
    
    public VendingMachine(int productCount) {
        // Initialize states
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        
        this.productCount = productCount;
        
        // Set initial state
        if (productCount > 0) {
            currentState = noCoinState;
        } else {
            currentState = soldOutState;
        }
    }
    
    // request() method from the class diagram
    public void insertCoin() {
        currentState.insertCoin();
    }
    
    public void ejectCoin() {
        currentState.ejectCoin();
    }
    
    public void selectProduct() {
        currentState.selectProduct();
    }
    
    public void dispenseProduct() {
        currentState.dispense();
    }
    
    // State transitions
    public void setState(MachineState state) {
        this.currentState = state;
    }
    
    // Helper methods
    public void releaseCoin() {
        System.out.println("Coin returned");
    }
    
    public void releaseProduct() {
        if (productCount > 0) {
            productCount--;
            System.out.println("Product dispensed");
        }
    }
    
    public int getProductCount() {
        return productCount;
    }
    
    public void refill(int count) {
        this.productCount += count;
        System.out.println("Machine refilled with " + count + " products");
        if (currentState == soldOutState && productCount > 0) {
            setState(noCoinState);
        }
    }
    
    // State getters
    public MachineState getNoCoinState() {
        return noCoinState;
    }
    
    public MachineState getHasCoinState() {
        return hasCoinState;
    }
    
    public MachineState getSoldState() {
        return soldState;
    }
    
    public MachineState getSoldOutState() {
        return soldOutState;
    }
    
    public String getCurrentStateName() {
        if (currentState == noCoinState) return "No Coin State";
        if (currentState == hasCoinState) return "Has Coin State";
        if (currentState == soldState) return "Sold State";
        if (currentState == soldOutState) return "Sold Out State";
        return "Unknown State";
    }
}

// State from the class diagram
interface MachineState {
    // handle() method from the class diagram
    void insertCoin();
    void ejectCoin();
    void selectProduct();
    void dispense();
}

// ConcreteStateA from the class diagram
class NoCoinState implements MachineState {
    private VendingMachine vendingMachine;
    
    public NoCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    
    // handle() implementation
    @Override
    public void insertCoin() {
        System.out.println("Coin inserted");
        vendingMachine.setState(vendingMachine.getHasCoinState());
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("No coin to return");
    }
    
    @Override
    public void selectProduct() {
        System.out.println("Please insert a coin first");
    }
    
    @Override
    public void dispense() {
        System.out.println("Please insert a coin first");
    }
}

// ConcreteStateB from the class diagram
class HasCoinState implements MachineState {
    private VendingMachine vendingMachine;
    
    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    
    // handle() implementation
    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted");
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("Returning coin");
        vendingMachine.releaseCoin();
        vendingMachine.setState(vendingMachine.getNoCoinState());
    }
    
    @Override
    public void selectProduct() {
        System.out.println("Product selected");
        vendingMachine.setState(vendingMachine.getSoldState());
    }
    
    @Override
    public void dispense() {
        System.out.println("Please select a product first");
    }
}

// Another ConcreteState
class SoldState implements MachineState {
    private VendingMachine vendingMachine;
    
    public SoldState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    
    // handle() implementation
    @Override
    public void insertCoin() {
        System.out.println("Please wait, dispensing product");
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("Sorry, already dispensing");
    }
    
    @Override
    public void selectProduct() {
        System.out.println("Please wait, dispensing product");
    }
    
    @Override
    public void dispense() {
        vendingMachine.releaseProduct();
        if (vendingMachine.getProductCount() > 0) {
            vendingMachine.setState(vendingMachine.getNoCoinState());
        } else {
            System.out.println("Out of products");
            vendingMachine.setState(vendingMachine.getSoldOutState());
        }
    }
}

// Another ConcreteState
class SoldOutState implements MachineState {
    private VendingMachine vendingMachine;
    
    public SoldOutState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    
    // handle() implementation
    @Override
    public void insertCoin() {
        System.out.println("Machine is sold out, cannot accept coins");
        vendingMachine.releaseCoin();
    }
    
    @Override
    public void ejectCoin() {
        System.out.println("No coin inserted");
    }
    
    @Override
    public void selectProduct() {
        System.out.println("Machine is sold out");
    }
    
    @Override
    public void dispense() {
        System.out.println("No product to dispense");
    }
}
