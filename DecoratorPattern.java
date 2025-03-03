// Component - the abstract base component interface
abstract class Beverage {
    protected String description = "Unknown Beverage";
    
    public String getDescription() {
        return description;
    }
    
    public abstract double cost();
}

// ConcreteComponent - the basic implementation of the Component interface
class BasicCoffee extends Beverage {
    public BasicCoffee() {
        description = "Basic Coffee";
    }
    
    @Override
    public double cost() {
        return 1.99;
    }
}

// Decorator - abstract decorator class that implements Component and has a reference to a Component
abstract class BeverageDecorator extends Beverage {
    protected Beverage wrappedObj; // Reference to the wrapped component
    
    public BeverageDecorator(Beverage beverage) {
        this.wrappedObj = beverage;
    }
    
    @Override
    public String getDescription() {
        return wrappedObj.getDescription();
    }
}

// ConcreteDecoratorA - adds behavior to the component
class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }
    
    @Override
    public String getDescription() {
        return wrappedObj.getDescription() + ", Milk";
    }
    
    @Override
    public double cost() {
        return wrappedObj.cost() + 0.30;
    }
    
    // Additional behavior specific to this decorator
    public void frothMilk() {
        System.out.println("Frothing milk...");
    }
}

// ConcreteDecoratorB - adds different behavior to the component
class SugarDecorator extends BeverageDecorator {
    // Additional state
    private int sugarLevel = 2; // Default sugar level
    
    public SugarDecorator(Beverage beverage) {
        super(beverage);
    }
    
    public SugarDecorator(Beverage beverage, int sugarLevel) {
        super(beverage);
        this.sugarLevel = sugarLevel;
    }
    
    @Override
    public String getDescription() {
        return wrappedObj.getDescription() + ", Sugar (level: " + sugarLevel + ")";
    }
    
    @Override
    public double cost() {
        return wrappedObj.cost() + 0.10;
    }
}

// ConcreteDecoratorC - adds different behavior to the component
class CaramelDecorator extends BeverageDecorator {
    public CaramelDecorator(Beverage beverage) {
        super(beverage);
    }
    
    @Override
    public String getDescription() {
        return wrappedObj.getDescription() + ", Caramel";
    }
    
    @Override
    public double cost() {
        return wrappedObj.cost() + 0.50;
    }
}

// ConcreteDecoratorD - adds different behavior to the component
class WhippedCreamDecorator extends BeverageDecorator {
    public WhippedCreamDecorator(Beverage beverage) {
        super(beverage);
    }
    
    @Override
    public String getDescription() {
        return wrappedObj.getDescription() + ", Whipped Cream";
    }
    
    @Override
    public double cost() {
        return wrappedObj.cost() + 0.70;
    }
}

@SuppressWarnings("all")
public class DecoratorPattern {
    public static void main(String[] args) {
        // Create a basic coffee
        Beverage simpleCoffee = new BasicCoffee();
        System.out.println(simpleCoffee.getDescription() + " $" + simpleCoffee.cost());
        
        // Add milk to our coffee
        Beverage milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println(milkCoffee.getDescription() + " $" + milkCoffee.cost());
        
        // Add sugar to our coffee
        Beverage sugarCoffee = new SugarDecorator(simpleCoffee);
        System.out.println(sugarCoffee.getDescription() + " $" + sugarCoffee.cost());
        
        // Create a complex coffee with multiple decorators
        Beverage fancyCoffee = new WhippedCreamDecorator(
                                new CaramelDecorator(
                                    new MilkDecorator(
                                        new BasicCoffee())));
        System.out.println(fancyCoffee.getDescription() + " $" + fancyCoffee.cost());
    }
}
