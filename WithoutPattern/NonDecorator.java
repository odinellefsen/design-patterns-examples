package WithoutPattern;
/**
 * This file demonstrates how we might implement coffee with additions
 * without using the Decorator pattern.
 */

// Approach 1: Using inheritance (subclass explosion)
class Coffee {
    protected double basePrice = 1.99;
    
    public String getDescription() {
        return "Basic Coffee";
    }
    
    public double cost() {
        return basePrice;
    }
}

// We need a separate class for each combination
class CoffeeWithMilk extends Coffee {
    @Override
    public String getDescription() {
        return "Coffee with Milk";
    }
    
    @Override
    public double cost() {
        return super.cost() + 0.30;
    }
}

class CoffeeWithSugar extends Coffee {
    private int sugarLevel = 2;
    
    public CoffeeWithSugar() {
        // Default constructor
    }
    
    public CoffeeWithSugar(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }
    
    @Override
    public String getDescription() {
        return "Coffee with Sugar (level: " + sugarLevel + ")";
    }
    
    @Override
    public double cost() {
        return super.cost() + 0.10;
    }
}

class CoffeeWithMilkAndSugar extends Coffee {
    private int sugarLevel = 2;
    
    public CoffeeWithMilkAndSugar() {
        // Default constructor
    }
    
    public CoffeeWithMilkAndSugar(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }
    
    @Override
    public String getDescription() {
        return "Coffee with Milk and Sugar (level: " + sugarLevel + ")";
    }
    
    @Override
    public double cost() {
        return super.cost() + 0.30 + 0.10;
    }
}

// Approach 2: Using a single class with boolean flags
class ConfigurableCoffee {
    private boolean hasMilk = false;
    private boolean hasSugar = false;
    private boolean hasCaramel = false;
    private boolean hasWhippedCream = false;
    private int sugarLevel = 2;
    
    public ConfigurableCoffee() {
        // Default constructor - just basic coffee
    }
    
    public void addMilk() {
        this.hasMilk = true;
    }
    
    public void addSugar() {
        this.hasSugar = true;
    }
    
    public void addSugar(int level) {
        this.hasSugar = true;
        this.sugarLevel = level;
    }
    
    public void addCaramel() {
        this.hasCaramel = true;
    }
    
    public void addWhippedCream() {
        this.hasWhippedCream = true;
    }
    
    public String getDescription() {
        StringBuilder description = new StringBuilder("Coffee");
        
        if (hasMilk) {
            description.append(" with Milk");
        }
        
        if (hasSugar) {
            description.append(" with Sugar (level: ").append(sugarLevel).append(")");
        }
        
        if (hasCaramel) {
            description.append(" with Caramel");
        }
        
        if (hasWhippedCream) {
            description.append(" with Whipped Cream");
        }
        
        return description.toString();
    }
    
    public double cost() {
        double totalCost = 1.99; // Base price
        
        if (hasMilk) {
            totalCost += 0.30;
        }
        
        if (hasSugar) {
            totalCost += 0.10;
        }
        
        if (hasCaramel) {
            totalCost += 0.50;
        }
        
        if (hasWhippedCream) {
            totalCost += 0.70;
        }
        
        return totalCost;
    }
}

public class NonDecorator {
    public static void main(String[] args) {
        System.out.println("Approach 1: Using inheritance");
        
        // Create a basic coffee
        Coffee simpleCoffee = new Coffee();
        System.out.println(simpleCoffee.getDescription() + " $" + simpleCoffee.cost());
        
        // Coffee with milk
        Coffee milkCoffee = new CoffeeWithMilk();
        System.out.println(milkCoffee.getDescription() + " $" + milkCoffee.cost());
        
        // Coffee with sugar
        Coffee sugarCoffee = new CoffeeWithSugar();
        System.out.println(sugarCoffee.getDescription() + " $" + sugarCoffee.cost());
        
        // Coffee with milk and sugar
        Coffee milkAndSugarCoffee = new CoffeeWithMilkAndSugar();
        System.out.println(milkAndSugarCoffee.getDescription() + " $" + milkAndSugarCoffee.cost());
        
        System.out.println("\nApproach 2: Using a single class with flags");
        
        // Create a basic coffee
        ConfigurableCoffee basicCoffee = new ConfigurableCoffee();
        System.out.println(basicCoffee.getDescription() + " $" + basicCoffee.cost());
        
        // Coffee with milk
        ConfigurableCoffee coffeeWithMilk = new ConfigurableCoffee();
        coffeeWithMilk.addMilk();
        System.out.println(coffeeWithMilk.getDescription() + " $" + coffeeWithMilk.cost());
        
        // Coffee with sugar
        ConfigurableCoffee coffeeWithSugar = new ConfigurableCoffee();
        coffeeWithSugar.addSugar(3); // Custom sugar level
        System.out.println(coffeeWithSugar.getDescription() + " $" + coffeeWithSugar.cost());
        
        // Complex coffee with multiple additions
        ConfigurableCoffee fancyCoffee = new ConfigurableCoffee();
        fancyCoffee.addMilk();
        fancyCoffee.addSugar();
        fancyCoffee.addCaramel();
        fancyCoffee.addWhippedCream();
        System.out.println(fancyCoffee.getDescription() + " $" + fancyCoffee.cost());
    }
} 