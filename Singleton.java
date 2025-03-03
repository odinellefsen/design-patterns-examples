@SuppressWarnings("all")
public class Singleton {
    public static void main(String[] args) {
        // Get the database connection instance
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.query("SELECT * FROM users");
        
        // Try to get another instance - will return the same object
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.query("SELECT * FROM products");
        
        // Verify both variables refer to the same instance
        System.out.println("Are db1 and db2 the same instance? " + (db1 == db2));
        
        // Get the configuration manager instance
        ConfigurationManager config = ConfigurationManager.getInstance();
        System.out.println("App name: " + config.getAppName());
        config.setAppName("Updated App Name");
        
        // Get the instance again and verify the state is maintained
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.println("Updated app name: " + config2.getAppName());
    }
}

// Singleton from the class diagram - Thread-safe with lazy initialization
class DatabaseConnection {
    // Static uniqueInstance as shown in the class diagram
    private static DatabaseConnection uniqueInstance;
    
    // Private constructor prevents instantiation from other classes
    private DatabaseConnection() {
        System.out.println("Creating new database connection");
    }
    
    // Static getInstance method as shown in the class diagram
    public static synchronized DatabaseConnection getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DatabaseConnection();
        }
        return uniqueInstance;
    }
    
    // Other useful Singleton data/methods as mentioned in the class diagram
    public void query(String sql) {
        System.out.println("Executing SQL: " + sql);
    }
    
    public void close() {
        System.out.println("Closing database connection");
    }
}

// Singleton from the class diagram - Using eager initialization
class ConfigurationManager {
    // Static uniqueInstance with eager initialization
    private static final ConfigurationManager uniqueInstance = new ConfigurationManager();
    
    // Configuration data
    private String appName;
    private String version;
    
    // Private constructor
    private ConfigurationManager() {
        System.out.println("Loading configuration...");
        // Simulate loading configuration from a file
        this.appName = "My Application";
        this.version = "1.0.0";
    }
    
    // Static getInstance method as shown in the class diagram
    public static ConfigurationManager getInstance() {
        return uniqueInstance;
    }
    
    // Other useful Singleton methods as mentioned in the class diagram
    public String getAppName() {
        return appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
}

// Singleton from the class diagram - Using double-checked locking
class LoggerService {
    // Static uniqueInstance as shown in the class diagram
    private static volatile LoggerService uniqueInstance;
    
    // Private constructor
    private LoggerService() {
        System.out.println("Initializing logger service");
    }
    
    // Static getInstance method with double-checked locking
    public static LoggerService getInstance() {
        if (uniqueInstance == null) {
            synchronized (LoggerService.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new LoggerService();
                }
            }
        }
        return uniqueInstance;
    }
    
    // Other useful Singleton methods as mentioned in the class diagram
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
    
    public void setLogLevel(String level) {
        System.out.println("Setting log level to: " + level);
    }
}
