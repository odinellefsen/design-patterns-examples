// Receiver from the class diagram
@SuppressWarnings("all")
class Light {
    private String location;
    private boolean isOn = false;
    
    public Light(String location) {
        this.location = location;
    }
    
    // action() method from the class diagram
    public void turnOn() {
        isOn = true;
        System.out.println(location + " light is now ON");
    }
    
    public void turnOff() {
        isOn = false;
        System.out.println(location + " light is now OFF");
    }
}

// Another Receiver
class Thermostat {
    private String location;
    private int temperature;
    
    public Thermostat(String location) {
        this.location = location;
        this.temperature = 70; // Default temperature
    }
    
    // action() method from the class diagram
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println(location + " thermostat set to " + temperature + " degrees");
    }
    
    public int getTemperature() {
        return temperature;
    }
}

// Command interface from the class diagram
interface ICommand {
    void execute();
    void undo();
}

// ConcreteCommand from the class diagram
class LightOnCommand implements ICommand {
    private Light light; // reference to the receiver
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOn(); // calls receiver.action()
    }
    
    @Override
    public void undo() {
        light.turnOff();
    }
}

// Another ConcreteCommand
class LightOffCommand implements ICommand {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOff();
    }
    
    @Override
    public void undo() {
        light.turnOn();
    }
}

// Another ConcreteCommand
class ThermostatSetCommand implements ICommand {
    private Thermostat thermostat;
    private int temperature;
    private int previousTemperature;
    
    public ThermostatSetCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }
    
    @Override
    public void execute() {
        previousTemperature = thermostat.getTemperature();
        thermostat.setTemperature(temperature);
    }
    
    @Override
    public void undo() {
        thermostat.setTemperature(previousTemperature);
    }
}

// A composite command (Macro command)
class MacroCommand implements ICommand {
    private ICommand[] commands;
    
    public MacroCommand(ICommand[] commands) {
        this.commands = commands;
    }
    
    @Override
    public void execute() {
        for (ICommand command : commands) {
            command.execute();
        }
    }
    
    @Override
    public void undo() {
        // Undo commands in reverse order
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}

// Invoker from the class diagram
class RemoteControl {
    private ICommand command;
    
    // setCommand() method from the class diagram
    public void setCommand(ICommand command) {
        this.command = command;
    }
    
    public void buttonPressed() {
        command.execute();
    }
    
    public void undoButtonPressed() {
        command.undo();
    }
}

@SuppressWarnings("all")
public class Command {
    public static void main(String[] args) {
        // Create the receiver
        Light livingRoomLight = new Light("Living Room");
        
        // Create concrete commands with the receiver
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        
        // Create another receiver
        Thermostat homeThermostat = new Thermostat("Home");
        
        // Create commands for the thermostat
        ThermostatSetCommand thermostatSet = new ThermostatSetCommand(homeThermostat, 72);
        
        // Create the invoker
        RemoteControl remote = new RemoteControl();
        
        // Execute commands
        System.out.println("--- Testing basic commands ---");
        remote.setCommand(livingRoomLightOn);
        remote.buttonPressed();
        
        remote.setCommand(livingRoomLightOff);
        remote.buttonPressed();
        
        remote.setCommand(thermostatSet);
        remote.buttonPressed();
        
        // Test undo
        System.out.println("\n--- Testing undo ---");
        remote.setCommand(livingRoomLightOn);
        remote.buttonPressed();
        remote.undoButtonPressed();
        
        // Create a macro command
        System.out.println("\n--- Testing macro command ---");
        ICommand[] partyOn = {livingRoomLightOn, thermostatSet};
        MacroCommand partyMode = new MacroCommand(partyOn);
        
        remote.setCommand(partyMode);
        remote.buttonPressed();
        remote.undoButtonPressed();
    }
}
