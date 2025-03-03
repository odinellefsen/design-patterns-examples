@SuppressWarnings("all")
public class Facade {
    public static void main(String[] args) {
        // Client code using the facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
            new Amplifier(), 
            new DvdPlayer(),
            new Projector(),
            new TheaterLights(),
            new Screen(),
            new PopcornMaker()
        );
        
        System.out.println("--- Movie Night Starting ---");
        homeTheater.watchMovie("The Matrix");
        
        System.out.println("\n--- Movie Night Ending ---");
        homeTheater.endMovie();
    }
}

// Facade from the class diagram
class HomeTheaterFacade {
    private Amplifier amplifier;
    private DvdPlayer dvdPlayer;
    private Projector projector;
    private TheaterLights lights;
    private Screen screen;
    private PopcornMaker popcornMaker;
    
    public HomeTheaterFacade(Amplifier amplifier, DvdPlayer dvdPlayer, 
                            Projector projector, TheaterLights lights,
                            Screen screen, PopcornMaker popcornMaker) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.lights = lights;
        this.screen = screen;
        this.popcornMaker = popcornMaker;
    }
    
    // Simplified interface for watching a movie
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        popcornMaker.on();
        popcornMaker.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setDvd(dvdPlayer);
        amplifier.setSurroundSound();
        amplifier.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }
    
    // Simplified interface for ending a movie
    public void endMovie() {
        System.out.println("Shutting down the home theater...");
        popcornMaker.off();
        lights.on();
        screen.up();
        projector.off();
        amplifier.off();
        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
    }
}

// Subsystem class from the class diagram
class Amplifier {
    public void on() {
        System.out.println("Amplifier is ON");
    }
    
    public void off() {
        System.out.println("Amplifier is OFF");
    }
    
    public void setDvd(DvdPlayer dvd) {
        System.out.println("Amplifier set to DVD player");
    }
    
    public void setSurroundSound() {
        System.out.println("Amplifier surround sound enabled (5 speakers, 1 subwoofer)");
    }
    
    public void setVolume(int level) {
        System.out.println("Amplifier volume set to " + level);
    }
}

// Subsystem class from the class diagram
class DvdPlayer {
    public void on() {
        System.out.println("DVD Player is ON");
    }
    
    public void off() {
        System.out.println("DVD Player is OFF");
    }
    
    public void play(String movie) {
        System.out.println("DVD Player playing: " + movie);
    }
    
    public void stop() {
        System.out.println("DVD Player stopped");
    }
    
    public void eject() {
        System.out.println("DVD Player ejected");
    }
}

// Subsystem class from the class diagram
class Projector {
    public void on() {
        System.out.println("Projector is ON");
    }
    
    public void off() {
        System.out.println("Projector is OFF");
    }
    
    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode (16:9 aspect ratio)");
    }
}

// Subsystem class from the class diagram
class TheaterLights {
    public void on() {
        System.out.println("Theater Lights are ON");
    }
    
    public void off() {
        System.out.println("Theater Lights are OFF");
    }
    
    public void dim(int level) {
        System.out.println("Theater Lights dimming to " + level + "%");
    }
}

// Subsystem class from the class diagram
class Screen {
    public void up() {
        System.out.println("Theater Screen going UP");
    }
    
    public void down() {
        System.out.println("Theater Screen going DOWN");
    }
}

// Subsystem class from the class diagram
class PopcornMaker {
    public void on() {
        System.out.println("Popcorn Maker is ON");
    }
    
    public void off() {
        System.out.println("Popcorn Maker is OFF");
    }
    
    public void pop() {
        System.out.println("Popcorn Maker is popping popcorn!");
    }
}
