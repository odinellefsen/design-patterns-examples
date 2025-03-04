// Client from the class diagram
class Client {
    private Button button;
    private Checkbox checkbox;
    private UIFactory factory;
    
    public Client(UIFactory factory) {
        this.factory = factory;
    }
    
    public void createUI() {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }
    
    public void renderUI() {
        button.render();
        checkbox.render();
    }
}

// AbstractFactory from the class diagram
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// ConcreteFactory1 from the class diagram
class ModernUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new ModernButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new ModernCheckbox();
    }
}

// ConcreteFactory2 from the class diagram
class VintageUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new VintageButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new VintageCheckbox();
    }
}

// AbstractProductA from the class diagram
interface Button {
    void render();
}

// ProductA1 from the class diagram
class ModernButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a sleek, modern button with rounded corners");
    }
}

// ProductA2 from the class diagram
class VintageButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a classic, vintage button with a beveled edge");
    }
}

// AbstractProductB from the class diagram
interface Checkbox {
    void render();
}

// ProductB1 from the class diagram
class ModernCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering a minimalist modern checkbox with animation");
    }
}

// ProductB2 from the class diagram
class VintageCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering a traditional square checkbox with a thick border");
    }
}

@SuppressWarnings("all")
public class AbstractFactory {
    public static void main(String[] args) {
        // Create a modern UI factory
        UIFactory modernFactory = new ModernUIFactory();
        
        // Create a client with the modern factory
        Client client1 = new Client(modernFactory);
        client1.createUI();
        client1.renderUI();
        
        System.out.println("\n------------------------\n");
        
        // Create a vintage UI factory
        UIFactory vintageFactory = new VintageUIFactory();
        
        // Create a client with the vintage factory
        Client client2 = new Client(vintageFactory);
        client2.createUI();
        client2.renderUI();
    }
}