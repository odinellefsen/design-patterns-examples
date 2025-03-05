// Mediator interface from the class diagram
interface ChatMediator {
    void sendMessage(String message, User user);
    void sendPrivateMessage(String message, User sender, String recipient);
    void registerUser(User user);
}

// ConcreteMediator from the class diagram
class ChatRoom implements ChatMediator {
    private java.util.HashMap<String, User> users;
    
    public ChatRoom() {
        this.users = new java.util.HashMap<>();
    }
    
    @Override
    public void registerUser(User user) {
        if (!users.containsKey(user.getName())) {
            users.put(user.getName(), user);
            System.out.println(user.getName() + " joined the chat room");
        }
    }
    
    @Override
    public void sendMessage(String message, User sender) {
        // Broadcast message to all users except the sender
        for (User user : users.values()) {
            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }
    
    @Override
    public void sendPrivateMessage(String message, User sender, String recipientName) {
        User recipient = users.get(recipientName);
        if (recipient != null) {
            recipient.receive("PRIVATE: " + message, sender.getName());
            System.out.println("[" + sender.getName() + " -> " + recipientName + "] (private)");
        } else {
            sender.receive("User " + recipientName + " not found", "System");
        }
    }
    
    // Additional method for admin announcements
    public void sendAnnouncement(String message, String senderName) {
        System.out.println("\n[ANNOUNCEMENT from " + senderName + "]: " + message);
        for (User user : users.values()) {
            if (!user.getName().equals(senderName)) {
                user.receive("ANNOUNCEMENT: " + message, senderName);
            }
        }
    }
}

// Colleague abstract class from the class diagram
abstract class User {
    protected ChatMediator mediator;
    protected String name;
    
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract void send(String message);
    
    public abstract void sendPrivate(String recipient, String message);
    
    public abstract void receive(String message, String sender);
}

// ConcreteColleague1 from the class diagram
class UserImpl extends User {
    public UserImpl(ChatMediator mediator, String name) {
        super(mediator, name);
    }
    
    @Override
    public void send(String message) {
        System.out.println("\n" + name + " sends: " + message);
        mediator.sendMessage(message, this);
    }
    
    @Override
    public void sendPrivate(String recipient, String message) {
        System.out.println("\n" + name + " sends private message to " + recipient);
        mediator.sendPrivateMessage(message, this, recipient);
    }
    
    @Override
    public void receive(String message, String sender) {
        System.out.println(name + " received from " + sender + ": " + message);
    }
}

// ConcreteColleague2 from the class diagram
class AdminUser extends User {
    public AdminUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }
    
    @Override
    public void send(String message) {
        System.out.println("\n" + name + " sends: " + message);
        mediator.sendMessage(message, this);
    }
    
    @Override
    public void sendPrivate(String recipient, String message) {
        System.out.println("\n" + name + " sends private message to " + recipient);
        mediator.sendPrivateMessage(message, this, recipient);
    }
    
    @Override
    public void receive(String message, String sender) {
        System.out.println(name + " received from " + sender + ": " + message);
    }
    
    // Additional functionality specific to AdminUser
    public void sendAnnouncement(String message) {
        if (mediator instanceof ChatRoom) {
            ((ChatRoom) mediator).sendAnnouncement(message, name);
        }
    }
}

@SuppressWarnings("all")
public class Mediator {
    public static void main(String[] args) {
        // Create the mediator
        ChatRoom chatRoom = new ChatRoom();
        
        // Create colleagues
        User john = new UserImpl(chatRoom, "John");
        User alice = new UserImpl(chatRoom, "Alice");
        User bob = new UserImpl(chatRoom, "Bob");
        
        // Register colleagues with the mediator
        chatRoom.registerUser(john);
        chatRoom.registerUser(alice);
        chatRoom.registerUser(bob);
        
        // Colleagues interact through the mediator
        john.send("Hello everyone!");
        
        alice.send("Hi John, how are you?");
        
        bob.send("Hey folks, what are we discussing today?");
        
        // Direct message
        john.sendPrivate("Alice", "Can we meet later?");
        
        // Create a different type of colleague
        AdminUser admin = new AdminUser(chatRoom, "Admin");
        chatRoom.registerUser(admin);
        
        // Admin broadcasts an announcement
        admin.sendAnnouncement("The system will be down for maintenance tonight.");
    }
}