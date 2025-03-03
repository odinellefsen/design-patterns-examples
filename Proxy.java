// Subject interface from the class diagram
interface ImageViewer {
    // request() method from the class diagram
    void displayImage(String filename);
    
    // Additional method for the protection proxy example
    default void setCredentials(String username, String password) {
        // Default empty implementation
    }
}

// RealSubject from the class diagram
class RealImageViewer implements ImageViewer {
    private String filename;
    
    public RealImageViewer(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }
    
    private void loadImageFromDisk() {
        System.out.println("Loading image: " + filename);
        // Simulate heavy image loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // request() implementation
    @Override
    public void displayImage(String filename) {
        if (!this.filename.equals(filename)) {
            this.filename = filename;
            loadImageFromDisk();
        }
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy from the class diagram - Protection Proxy example
class ImageViewerProxy implements ImageViewer {
    private RealImageViewer realImageViewer;
    private String authorizedUser;
    private String authorizedPassword;
    private String currentUser;
    private String currentPassword;
    
    public ImageViewerProxy(String authorizedUser, String authorizedPassword) {
        this.authorizedUser = authorizedUser;
        this.authorizedPassword = authorizedPassword;
    }
    
    @Override
    public void setCredentials(String username, String password) {
        this.currentUser = username;
        this.currentPassword = password;
    }
    
    private boolean isAuthorized() {
        return currentUser != null && 
               currentPassword != null && 
               currentUser.equals(authorizedUser) && 
               currentPassword.equals(authorizedPassword);
    }
    
    // request() implementation
    @Override
    public void displayImage(String filename) {
        if (!isAuthorized()) {
            System.out.println("Access denied: Not authorized to view images");
            return;
        }
        
        // Create the real subject only when needed
        if (realImageViewer == null) {
            realImageViewer = new RealImageViewer(filename);
        }
        
        // Delegate to the real subject
        realImageViewer.displayImage(filename);
    }
}

// Another Proxy example - Virtual Proxy (lazy loading)
@SuppressWarnings("all")
class LazyImageLoaderProxy implements ImageViewer {
    private RealImageViewer realImageViewer;
    private String filename;
    
    public LazyImageLoaderProxy(String filename) {
        this.filename = filename;
    }
    
    // request() implementation
    @Override
    public void displayImage(String filename) {
        // Create the real subject only when needed
        if (realImageViewer == null) {
            System.out.println("Creating real image viewer on first use");
            realImageViewer = new RealImageViewer(filename);
        }
        
        // Delegate to the real subject
        realImageViewer.displayImage(filename);
    }
}

// Another Proxy example - Remote Proxy
@SuppressWarnings("all")
class RemoteImageProxy implements ImageViewer {
    // request() implementation
    @Override
    public void displayImage(String filename) {
        System.out.println("RemoteProxy: Connecting to remote server...");
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("RemoteProxy: Fetching image " + filename + " from remote server");
        // In a real implementation, this would connect to a remote service
        
        // Create a local representation of the remote object
        RealImageViewer localRepresentation = new RealImageViewer(filename);
        
        // Use the local representation
        System.out.println("RemoteProxy: Displaying fetched image locally");
    }
}

@SuppressWarnings("all")
public class Proxy {
    public static void main(String[] args) {
        // Using a proxy to control access to a real image
        System.out.println("=== Protection Proxy Example ===");
        ImageViewer viewer = new ImageViewerProxy("admin", "admin123");
        
        // Try with incorrect credentials
        viewer.setCredentials("user", "wrongpassword");
        viewer.displayImage("vacation.jpg");
        
        // Try with correct credentials
        viewer.setCredentials("admin", "admin123");
        viewer.displayImage("vacation.jpg");
        
        System.out.println("\n=== Virtual Proxy Example ===");
        // Using a proxy to load a heavy resource only when needed
        ImageViewer lazyLoader = new LazyImageLoaderProxy("sunset.jpg");
        
        System.out.println("Image object created, but not loaded yet");
        
        // Image will be loaded only when displayed
        System.out.println("Displaying image for the first time:");
        lazyLoader.displayImage("sunset.jpg");
        
        System.out.println("\nDisplaying image again (already loaded):");
        lazyLoader.displayImage("sunset.jpg");
        
        System.out.println("\n=== Remote Proxy Example ===");
        // Using a proxy to access a remote service
        ImageViewer remoteViewer = new RemoteImageProxy();
        remoteViewer.displayImage("remote-server/profile.jpg");
    }
}