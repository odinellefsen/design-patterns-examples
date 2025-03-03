@SuppressWarnings("all")
public class Adapter {
    public static void main(String[] args) {
        // Create a media player that works with our application
        MediaPlayer player = new AudioPlayer();
        
        // Play an MP3 file (supported natively)
        playMedia(player, "song.mp3");
        
        // Now we want to play formats not supported by our MediaPlayer
        
        // Use an adapter to play MP4
        MediaPlayer mp4Adapter = new AdvancedMediaAdapter(new Mp4Player());
        playMedia(mp4Adapter, "video.mp4");
        
        // Use an adapter to play MKV
        MediaPlayer mkvAdapter = new AdvancedMediaAdapter(new MkvPlayer());
        playMedia(mkvAdapter, "movie.mkv");
    }
    
    // Client code that works with MediaPlayer
    public static void playMedia(MediaPlayer player, String filename) {
        System.out.println("Client: Playing " + filename);
        player.play(filename);
        System.out.println();
    }
}

// Target interface from the class diagram
interface MediaPlayer {
    void play(String filename);
}

// A concrete implementation of the Target interface
class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp3")) {
            System.out.println("Playing MP3 file: " + filename);
        } else {
            System.out.println("AudioPlayer only supports MP3 format.");
        }
    }
}

// Adaptee from the class diagram
interface AdvancedMediaPlayer {
    // Different interface from what the client expects
    void playFile(String filename);
}

// Concrete Adaptee implementation
class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playFile(String filename) {
        System.out.println("Playing MP4 file with advanced features: " + filename);
    }
}

// Another Concrete Adaptee implementation
class MkvPlayer implements AdvancedMediaPlayer {
    @Override
    public void playFile(String filename) {
        System.out.println("Playing MKV file with high definition: " + filename);
    }
}

// Adapter from the class diagram
class AdvancedMediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public AdvancedMediaAdapter(AdvancedMediaPlayer advancedPlayer) {
        this.advancedPlayer = advancedPlayer;
    }
    
    @Override
    public void play(String filename) {
        // Adapt the call to the different interface
        advancedPlayer.playFile(filename);
    }
}
