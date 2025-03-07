package examples;

import java.util.HashMap;
import java.util.Map;

// Define the client interface
abstract class WebServiceClient {
    abstract String get(String url);
}

// The core implementation of the web service client
class BasicWebServiceClient extends WebServiceClient {
    @Override
    public String get(String url) {
        // Simulate an HTTP GET request and return a dummy response.
        return "Response from " + url;
    }
}

// The abstract decorator that implements WebServiceClient and holds a reference to a wrapped client.
abstract class WebServiceClientDecorator extends WebServiceClient {
    protected WebServiceClient client;
    
    public WebServiceClientDecorator(WebServiceClient client) {
        this.client = client;
    }
    
    @Override
    public String get(String url) {
        return client.get(url);
    }
}

// A decorator for logging requests and responses.
class LoggingDecorator extends WebServiceClientDecorator {
    
    public LoggingDecorator(WebServiceClient client) {
        super(client);
    }
    
    @Override
    public String get(String url) {
        System.out.println("[Logging] Sending GET request to: " + url);
        String response = client.get(url);
        System.out.println("[Logging] Received response: " + response);
        return response;
    }
}

// A decorator for adding authentication headers.
class AuthenticationDecorator extends WebServiceClientDecorator {
    private String authToken;
    
    public AuthenticationDecorator(WebServiceClient client, String authToken) {
        super(client);
        this.authToken = authToken;
    }
    
    @Override
    public String get(String url) {
        // Simulate adding an authentication header.
        System.out.println("[Authentication] Adding auth token: " + authToken);
        return client.get(url);
    }
}

// A decorator for compressing requests and decompressing responses.
class CompressionDecorator extends WebServiceClientDecorator {
    
    public CompressionDecorator(WebServiceClient client) {
        super(client);
    }
    
    @Override
    public String get(String url) {
        System.out.println("[Compression] Compressing request (simulated).");
        String response = client.get(url);
        System.out.println("[Compression] Decompressing response (simulated).");
        return response;
    }
}

// A decorator for caching responses.
class CachingDecorator extends WebServiceClientDecorator {
    private Map<String, String> cache = new HashMap<>();
    
    public CachingDecorator(WebServiceClient client) {
        super(client);
    }
    
    @Override
    public String get(String url) {
        if (cache.containsKey(url)) {
            System.out.println("[Caching] Returning cached response for: " + url);
            return cache.get(url);
        } else {
            System.out.println("[Caching] No cached response for: " + url + ", making request.");
            String response = client.get(url);
            cache.put(url, response);
            return response;
        }
    }
}

// A demonstration class to show how to chain the decorators together.
public class DecoratorExample {
    public static void main(String[] args) {
        // Start with the basic client.
        WebServiceClient client = new BasicWebServiceClient();
        
        // Wrap the client with various decorators.
        client = new LoggingDecorator(client);
        client = new AuthenticationDecorator(client, "my-secret-token");
        client = new CompressionDecorator(client);
        client = new CachingDecorator(client);
        
        // First call: goes through all decorators and retrieves a fresh response.
        System.out.println("First call:");
        String response1 = client.get("http://example.com/data");
        System.out.println("Final response: " + response1);
        
        // Second call: should be served from the cache.
        System.out.println("\nSecond call:");
        String response2 = client.get("http://example.com/data");
        System.out.println("Final response: " + response2);
    }
}
