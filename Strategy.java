// Strategy interface
abstract class PaymentStrategy {
    abstract void pay(int amount);
}

// Concrete strategy 1
@SuppressWarnings("unused")
class CreditCardPayment extends PaymentStrategy {
    private String cardNumber;
    private String name;
    private String cvv;
    private String dateOfExpiry;
    
    public CreditCardPayment(String cardNumber, String name, String cvv, String dateOfExpiry) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with credit card");
    }
}

// Concrete strategy 2
@SuppressWarnings("unused")
class PayPalPayment extends PaymentStrategy {
    private String email;
    private String password;
    
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using PayPal");
    }
}

// Concrete strategy 3
@SuppressWarnings("unused")
class BitcoinPayment extends PaymentStrategy {
    private String walletAddress;
    
    public BitcoinPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Bitcoin");
    }
}

// Context class. This is the class that will use the strategy pattern.
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

// Main class to run the example
@SuppressWarnings("all") // This will suppress all warnings for this file
public class Strategy {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        // Pay by credit card
        cart.setPaymentStrategy(new CreditCardPayment("1234 5678 9012 3456", "John Doe", "123", "12/25"));
        cart.checkout(100);
        
        // Pay by PayPal
        cart.setPaymentStrategy(new PayPalPayment("john.doe@example.com", "password"));
        cart.checkout(200);
        
        // Pay by Bitcoin
        cart.setPaymentStrategy(new BitcoinPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
        cart.checkout(300);
    }
}
