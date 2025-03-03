@SuppressWarnings("all")
public class Builder {
    public static void main(String[] args) {
        // Create a meal builder
        MealDirector director = new MealDirector();
        
        // Build a vegetarian meal
        MealBuilder vegetarianBuilder = new VegetarianMealBuilder();
        director.constructMeal(vegetarianBuilder);
        Meal vegetarianMeal = vegetarianBuilder.getResult();
        System.out.println("Vegetarian Meal:");
        System.out.println(vegetarianMeal);
        
        // Build a non-vegetarian meal
        MealBuilder nonVegBuilder = new NonVegetarianMealBuilder();
        director.constructMeal(nonVegBuilder);
        Meal nonVegMeal = nonVegBuilder.getResult();
        System.out.println("\nNon-Vegetarian Meal:");
        System.out.println(nonVegMeal);
        
        // Build a kids meal
        MealBuilder kidsBuilder = new KidsMealBuilder();
        director.constructMeal(kidsBuilder);
        Meal kidsMeal = kidsBuilder.getResult();
        System.out.println("\nKids Meal:");
        System.out.println(kidsMeal);
        
        // Using the builder directly without a director
        System.out.println("\nCustom Meal (without director):");
        Meal customMeal = new VegetarianMealBuilder()
            .buildMainCourse()
            .buildDessert()
            .getResult();
        System.out.println(customMeal);
    }
}

// Product from the class diagram
class Meal {
    private String mainCourse;
    private String side;
    private String drink;
    private String dessert;
    private String toy; // For kids meal
    
    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }
    
    public void setSide(String side) {
        this.side = side;
    }
    
    public void setDrink(String drink) {
        this.drink = drink;
    }
    
    public void setDessert(String dessert) {
        this.dessert = dessert;
    }
    
    public void setToy(String toy) {
        this.toy = toy;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (mainCourse != null) sb.append("Main course: ").append(mainCourse).append("\n");
        if (side != null) sb.append("Side: ").append(side).append("\n");
        if (drink != null) sb.append("Drink: ").append(drink).append("\n");
        if (dessert != null) sb.append("Dessert: ").append(dessert).append("\n");
        if (toy != null) sb.append("Toy: ").append(toy).append("\n");
        return sb.toString();
    }
}

// Builder interface from the class diagram
interface MealBuilder {
    // buildPart() methods from the class diagram
    MealBuilder buildMainCourse();
    MealBuilder buildSide();
    MealBuilder buildDrink();
    MealBuilder buildDessert();
    
    // getResult() method from the class diagram
    Meal getResult();
}

// ConcreteBuilder from the class diagram
class VegetarianMealBuilder implements MealBuilder {
    private Meal meal;
    
    public VegetarianMealBuilder() {
        this.meal = new Meal();
    }
    
    @Override
    public MealBuilder buildMainCourse() {
        meal.setMainCourse("Vegetable Lasagna");
        return this;
    }
    
    @Override
    public MealBuilder buildSide() {
        meal.setSide("Garden Salad");
        return this;
    }
    
    @Override
    public MealBuilder buildDrink() {
        meal.setDrink("Fruit Juice");
        return this;
    }
    
    @Override
    public MealBuilder buildDessert() {
        meal.setDessert("Fruit Salad");
        return this;
    }
    
    @Override
    public Meal getResult() {
        return meal;
    }
}

// Another ConcreteBuilder
class NonVegetarianMealBuilder implements MealBuilder {
    private Meal meal;
    
    public NonVegetarianMealBuilder() {
        this.meal = new Meal();
    }
    
    @Override
    public MealBuilder buildMainCourse() {
        meal.setMainCourse("Grilled Chicken");
        return this;
    }
    
    @Override
    public MealBuilder buildSide() {
        meal.setSide("Mashed Potatoes");
        return this;
    }
    
    @Override
    public MealBuilder buildDrink() {
        meal.setDrink("Soda");
        return this;
    }
    
    @Override
    public MealBuilder buildDessert() {
        meal.setDessert("Chocolate Cake");
        return this;
    }
    
    @Override
    public Meal getResult() {
        return meal;
    }
}

// Another ConcreteBuilder
class KidsMealBuilder implements MealBuilder {
    private Meal meal;
    
    public KidsMealBuilder() {
        this.meal = new Meal();
    }
    
    @Override
    public MealBuilder buildMainCourse() {
        meal.setMainCourse("Chicken Nuggets");
        return this;
    }
    
    @Override
    public MealBuilder buildSide() {
        meal.setSide("French Fries");
        return this;
    }
    
    @Override
    public MealBuilder buildDrink() {
        meal.setDrink("Apple Juice");
        return this;
    }
    
    @Override
    public MealBuilder buildDessert() {
        meal.setDessert("Ice Cream");
        meal.setToy("Action Figure"); // Kids meal includes a toy
        return this;
    }
    
    @Override
    public Meal getResult() {
        return meal;
    }
}

// Director from the class diagram
class MealDirector {
    // construct() method from the class diagram
    public void constructMeal(MealBuilder builder) {
        builder.buildMainCourse()
               .buildSide()
               .buildDrink()
               .buildDessert();
    }
}
