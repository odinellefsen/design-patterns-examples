// this is a pull based observer pattern. this means that the observer is responsible for pulling the data from the subject.
// if you used a push based observer pattern, the subject would push the data to the observer via the update method.
// Observer interface - now with no parameters for update
interface Observer {
    void update();
}

// Subject interface remains the same
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// DisplayElement interface remains the same
interface DisplayElement {
    void display();
}

// ConcreteSubject - implements the Subject interface and maintains the state.
// Note: Getter methods are added for observers to pull data.
class WeatherStation implements Subject {
    private float temperature;
    private float humidity;
    private float pressure;
    private java.util.ArrayList<Observer> observers;
    
    public WeatherStation() {
        observers = new java.util.ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
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
    
    // Getter methods for observers to pull state data
    public float getTemperature() {
        return temperature;
    }
    
    public float getHumidity() {
        return humidity;
    }
    
    public float getPressure() {
        return pressure;
    }
}

// ConcreteObserver 1 - holds a reference to the ConcreteSubject (WeatherStation)
// and pulls data when updated
class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private WeatherStation weatherStation;
    
    // Constructor accepts the subject and registers this observer
    public CurrentConditionsDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.registerObserver(this);
    }
    
    @Override
    public void update() {
        // Pull the latest state from the subject
        this.temperature = weatherStation.getTemperature();
        this.humidity = weatherStation.getHumidity();
        display();
    }
    
    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

// ConcreteObserver 2 - holds a reference to the ConcreteSubject (WeatherStation)
// and pulls data when updated
@SuppressWarnings("unused")
class StatisticsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private WeatherStation weatherStation;
    
    // Constructor accepts the subject and registers this observer
    public StatisticsDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.registerObserver(this);
    }

    @Override
    public void update() {
        this.temperature = weatherStation.getTemperature();
        this.humidity = weatherStation.getHumidity();
        display();
    }

    @Override
    public void display() {
        System.out.println("Statistics: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}


@SuppressWarnings("unused")
public class ObserverPattern {
    public static void main(String[] args) {
        // Create subject
        WeatherStation weatherStation = new WeatherStation();
        
        // Create and register observers
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherStation);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherStation);
        
        // Simulate weather changes
        System.out.println("Weather update 1:");
        weatherStation.setMeasurements(80, 65, 30.4f);
        
        System.out.println("\nWeather update 2:");
        weatherStation.setMeasurements(82, 70, 29.2f);
    }
}
