// Model - Represents the data and business logic
class StudentModel {
    private String rollNo;
    private String name;
    private String grade;
    
    public String getRollNo() {
        return rollNo;
    }
    
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
}

// View - Represents the visualization of the data
class StudentView {
    public void printStudentDetails(String studentName, String rollNo, String grade) {
        System.out.println("Student Details:");
        System.out.println("Name: " + studentName);
        System.out.println("Roll Number: " + rollNo);
        System.out.println("Grade: " + grade);
    }
    
    // Additional view methods for different UI components
    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }
    
    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }
}

// Controller - Acts as an interface between Model and View
class StudentController {
    private StudentModel model;
    private StudentView view;
    
    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    
    // Getter and setter methods to update the model
    public String getStudentName() {
        return model.getName();
    }
    
    public void setStudentName(String name) {
        model.setName(name);
    }
    
    public String getStudentRollNo() {
        return model.getRollNo();
    }
    
    public void setStudentRollNo(String rollNo) {
        model.setRollNo(rollNo);
    }
    
    public String getStudentGrade() {
        return model.getGrade();
    }
    
    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }
    
    // Method to update the view
    public void updateView() {
        view.printStudentDetails(model.getName(), model.getRollNo(), model.getGrade());
    }
    
    // Process user input (simulating controller handling UI events)
    public void processUserInput(String value, String field) {
        try {
            switch (field) {
                case "name":
                    setStudentName(value);
                    view.showSuccessMessage("Name updated successfully");
                    break;
                case "roll":
                    setStudentRollNo(value);
                    view.showSuccessMessage("Roll number updated successfully");
                    break;
                case "grade":
                    // Add validation logic
                    if (isValidGrade(value)) {
                        setStudentGrade(value);
                        view.showSuccessMessage("Grade updated successfully");
                    } else {
                        view.showErrorMessage("Invalid grade. Must be A, B, C, D, or F");
                    }
                    break;
                default:
                    view.showErrorMessage("Unknown field: " + field);
            }
        } catch (Exception e) {
            view.showErrorMessage("Error processing input: " + e.getMessage());
        }
    }
    
    // Business logic in the controller
    private boolean isValidGrade(String grade) {
        return grade.matches("[A-F][+\\-]?");
    }
}

public class ModelViewController {
    public static void main(String[] args) {
        // Create the model
        StudentModel model = new StudentModel();
        model.setName("John Doe");
        model.setRollNo("CS21001");
        model.setGrade("A");
        
        // Create the view
        StudentView view = new StudentView();
        
        // Create the controller
        StudentController controller = new StudentController(model, view);
        
        // Display the initial student details
        System.out.println("Initial Student Details:");
        controller.updateView();
        
        // Update the model data through the controller
        System.out.println("\nAfter updating student details:");
        controller.setStudentName("Jane Smith");
        controller.setStudentGrade("A+");
        
        // Display the updated student details
        controller.updateView();
        
        // Simulate user input changing the roll number
        System.out.println("\nAfter user input to change roll number:");
        controller.processUserInput("CS21002", "roll");
        controller.updateView();
    }
}