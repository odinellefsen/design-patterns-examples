// Component - the abstract base component interface
abstract class Component {
    public abstract void methodA();
    public abstract void methodB();
}

// ConcreteComponent - the basic implementation of the Component interface
class ConcreteComponent extends Component {
    @Override
    public void methodA() {
        System.out.println("ConcreteComponent.methodA()");
    }
    
    @Override
    public void methodB() {
        System.out.println("ConcreteComponent.methodB()");
    }
}

// Decorator - abstract decorator class that implements Component and has a reference to a Component
abstract class Decorator extends Component {
    protected Component wrappedObj; // Reference to the wrapped component
    
    public Decorator(Component component) {
        this.wrappedObj = component;
    }
    
    // Default behavior is to delegate to the wrapped component
    @Override
    public void methodA() {
        wrappedObj.methodA();
    }
    
    @Override
    public void methodB() {
        wrappedObj.methodB();
    }
}

// ConcreteDecoratorA - adds behavior to the component
class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }
    
    @Override
    public void methodA() {
        System.out.println("ConcreteDecoratorA: before methodA()");
        super.methodA();
        System.out.println("ConcreteDecoratorA: after methodA()");
    }
    
    @Override
    public void methodB() {
        System.out.println("ConcreteDecoratorA: before methodB()");
        super.methodB();
        System.out.println("ConcreteDecoratorA: after methodB()");
    }
    
    // Additional behavior specific to this decorator
    public void newBehavior() {
        System.out.println("ConcreteDecoratorA: new behavior");
    }
}

// ConcreteDecoratorB - adds different behavior to the component
class ConcreteDecoratorB extends Decorator {
    // Additional state
    private String state = "Decorator B State";
    
    public ConcreteDecoratorB(Component component) {
        super(component);
    }
    
    @Override
    public void methodA() {
        System.out.println("ConcreteDecoratorB: before methodA() with " + state);
        super.methodA();
        System.out.println("ConcreteDecoratorB: after methodA()");
    }
    
    @Override
    public void methodB() {
        System.out.println("ConcreteDecoratorB: adding extra behavior to methodB()");
        super.methodB();
    }
}

@SuppressWarnings("all")
public class DecoratorPattern {
    public static void main(String[] args) {
        // Create a basic concrete component
        Component component = new ConcreteComponent();
        System.out.println("Basic component:");
        component.methodA();
        component.methodB();
        
        // Decorate with ConcreteDecoratorA
        System.out.println("\nComponent with Decorator A:");
        Component decoratorA = new ConcreteDecoratorA(component);
        decoratorA.methodA();
        decoratorA.methodB();
        
        // Decorate with ConcreteDecoratorB
        System.out.println("\nComponent with Decorator B:");
        Component decoratorB = new ConcreteDecoratorB(component);
        decoratorB.methodA();
        decoratorB.methodB();
        
        // Stack decorators - first A, then B
        System.out.println("\nComponent with Decorator A and B stacked:");
        Component decoratorAB = new ConcreteDecoratorB(new ConcreteDecoratorA(component));
        decoratorAB.methodA();
        decoratorAB.methodB();
    }
}
