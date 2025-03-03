// AbstractClass from the class diagram
abstract class CaffeineBeverage {
    // templateMethod() from the class diagram
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }
    
    // primitiveOperation1() from the class diagram
    protected abstract void brew();
    
    // primitiveOperation2() from the class diagram
    protected abstract void addCondiments();
    
    // These are concrete operations in the abstract class
    private void boilWater() {
        System.out.println("Boiling water");
    }
    
    private void pourInCup() {
        System.out.println("Pouring into cup");
    }
    
    // Hook method - subclasses can override this
    protected boolean customerWantsCondiments() {
        return true; // Default implementation
    }
}

// ConcreteClass from the class diagram
class Coffee extends CaffeineBeverage {
    // Implementation of primitiveOperation1()
    @Override
    protected void brew() {
        System.out.println("Dripping coffee through filter");
    }
    
    // Implementation of primitiveOperation2()
    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
    
    // Overriding the hook method
    @Override
    protected boolean customerWantsCondiments() {
        return true; // Coffee customers usually want condiments
    }
}

// Another ConcreteClass from the class diagram
class Tea extends CaffeineBeverage {
    // Implementation of primitiveOperation1()
    @Override
    protected void brew() {
        System.out.println("Steeping the tea");
    }
    
    // Implementation of primitiveOperation2()
    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon");
    }
    
    // Overriding the hook method
    @Override
    protected boolean customerWantsCondiments() {
        return false; // Tea customers don't always want condiments
    }
}

// Another ConcreteClass from the class diagram
class HotChocolate extends CaffeineBeverage {
    // Implementation of primitiveOperation1()
    @Override
    protected void brew() {
        System.out.println("Mixing cocoa powder with boiled water");
    }
    
    // Implementation of primitiveOperation2()
    @Override
    protected void addCondiments() {
        System.out.println("Adding whipped cream and marshmallows");
    }
}

@SuppressWarnings("all")
public class TemplateMethod {
    public static void main(String[] args) {
        System.out.println("Making a coffee:");
        CaffeineBeverage coffee = new Coffee();
        coffee.prepareRecipe();
        
        System.out.println("\nMaking a tea:");
        CaffeineBeverage tea = new Tea();
        tea.prepareRecipe();
        
        System.out.println("\nMaking hot chocolate:");
        CaffeineBeverage hotChocolate = new HotChocolate();
        hotChocolate.prepareRecipe();
    }
}