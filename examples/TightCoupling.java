package examples;

public class TightCoupling {
    public static void main(String[] args) {
        // Loose coupling example
        System.out.println("Loose Coupling");
        MovieNightOption movie = new BackToTheFuture1();
        LooseCouplingExample movieToWatch = new LooseCouplingExample(movie);
        movieToWatch.movieInformation();

        System.out.println();
        
        // Tight coupling example
        System.out.println("Tight Coupling");
        BackToTheFuture1Tight backToTheFuture1 = new BackToTheFuture1Tight();
        TightCouplingExample movieToWatchTight = new TightCouplingExample(backToTheFuture1);
        movieToWatchTight.movieInformation();
    }
}

// LOOSE COUPLING EXAMPLE
interface MovieNightOption {
    public String movieName();
    public String movieLength();
    public String movieGenre();
}

class BackToTheFuture1 implements MovieNightOption {
    public String movieName() {
        return "Back to the Future";
    }
    public String movieLength() {
        return "1 hour 56 minutes";
    }

    public String movieGenre() {
        return "Family/Sci-Fi";
    }
}

class BackToTheFuture2 implements MovieNightOption {
    public String movieName() {
        return "Back to the Future Part II";
    }
    public String movieLength() {
        return "1 hour 48 minutes";
    }

    public String movieGenre() {
        return "Family/Sci-Fi";
    }
}

class BackToTheFuture3 implements MovieNightOption {
    public String movieName() {
        return "Back to the Future Part III";
    }
    public String movieLength() {
        return "1 hour 58 minutes";
    }

    public String movieGenre() {
        return "Western/Sci-Fi";
    }
}

class LooseCouplingExample {
    MovieNightOption movieToWatch;

    public LooseCouplingExample(MovieNightOption movie) {
        this.movieToWatch = movie;
    }

    public void movieInformation() {
        System.out.println("movie name: " + movieToWatch.movieName());
        System.out.println("movie length: " + movieToWatch.movieLength());
        System.out.println("movie genre: " + movieToWatch.movieGenre());
    }
}


// TIGHT COUPLING EXAMPLE

class BackToTheFuture1Tight {
    public String movieName() {
        return "Back to the Future";
    }
    public String movieLength() {
        return "1 hour 56 minutes";
    }

    public String movieGenre() {
        return "Family/Sci-Fi";
    }
}

class BackToTheFuture2Tight {
    public String movieName() {
        return "Back to the Future Part II";
    }
    public String movieLength() {
        return "1 hour 48 minutes";
    }

    public String movieGenre() {
        return "Family/Sci-Fi";
    }
}

class BackToTheFuture3Tight {
    public String movieName() {
        return "Back to the Future Part III";
    }
    public String movieLength() {
        return "1 hour 58 minutes";
    }

    public String movieGenre() {
        return "Western/Sci-Fi";
    }
}

class TightCouplingExample {
    private Object movie; // Use a general Object type to handle different movie classes

    public TightCouplingExample(BackToTheFuture1Tight movie) {
        this.movie = movie;
    }

    public TightCouplingExample(BackToTheFuture2Tight movie) {
        this.movie = movie;
    }

    public TightCouplingExample(BackToTheFuture3Tight movie) {
        this.movie = movie;
    }

    public void movieInformation() {
        if (movie instanceof BackToTheFuture1Tight) {
            BackToTheFuture1Tight m = (BackToTheFuture1Tight) movie;
            System.out.println("movie name: " + m.movieName());
            System.out.println("movie length: " + m.movieLength());
            System.out.println("movie genre: " + m.movieGenre());
        } else if (movie instanceof BackToTheFuture2Tight) {
            BackToTheFuture2Tight m = (BackToTheFuture2Tight) movie;
            System.out.println("movie name: " + m.movieName());
            System.out.println("movie length: " + m.movieLength());
            System.out.println("movie genre: " + m.movieGenre());
        } else if (movie instanceof BackToTheFuture3Tight) {
            BackToTheFuture3Tight m = (BackToTheFuture3Tight) movie;
            System.out.println("movie name: " + m.movieName());
            System.out.println("movie length: " + m.movieLength());
            System.out.println("movie genre: " + m.movieGenre());
        }
    }
}
