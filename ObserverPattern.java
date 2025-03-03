// Observer interface - defines the update method that all concrete observers must implement
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// Subject interface - defines methods for registering, removing, and notifying observers
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// DisplayElement interface - defines a display method that all display elements must implement
interface DisplayElement {
    void display();
}

// ConcreteSubject - implements the Subject interface and maintains the state
// that is of interest to observers
class WeatherStation implements Subject {
    private float temperature;
    private float humidity;
    private float pressure;
    private java.util.ArrayList<Observer> observers;
    
    public WeatherStation() {
        observers = new java.util.ArrayList<Observer>();
    }
    
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    
    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }
    
    // Method called when measurements change
    public void measurementsChanged() {
        notifyObservers();
    }
    
    // Method to set measurements and trigger updates
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

// ConcreteObserver 1 - implements the Observer interface to receive updates
// and DisplayElement to display its state
class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
    
    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

// ConcreteObserver 2 - another implementation of Observer and DisplayElement
class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings = 0;
    
    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;
        
        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
        
        if (temperature < minTemp) {
            minTemp = temperature;
        }
        
        display();
    }
    
    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}

// ConcreteObserver 3 - another implementation of Observer and DisplayElement
class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f;
    private float lastPressure;
    
    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }
    
    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }
}

@SuppressWarnings("all")
public class ObserverPattern {
    public static void main(String[] args) {
        // Create subject
        WeatherStation weatherStation = new WeatherStation();
        
        // Create observers
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();
        
        // Register observers with the subject
        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticsDisplay);
        weatherStation.registerObserver(forecastDisplay);
        
        // Simulate weather changes
        System.out.println("Weather update 1:");
        weatherStation.setMeasurements(80, 65, 30.4f);
        
        System.out.println("\nWeather update 2:");
        weatherStation.setMeasurements(82, 70, 29.2f);
        
        // Remove an observer
        weatherStation.removeObserver(forecastDisplay);
        
        System.out.println("\nWeather update 3 (after removing forecast display):");
        weatherStation.setMeasurements(78, 90, 29.2f);
    }
}