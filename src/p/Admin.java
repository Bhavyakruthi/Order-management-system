/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p;

/**
 *
 * @author bhavy
 */
import java.util.*;

// admin class is used to create an admin object. it contains methods to view customers , view delivery agents , view managers , view the feedbacks , adding the staff and removing 
// the staff.

class Admin extends User {
    private String name;
    private String workExperience;
    private String preferredWorkTimings;

    public Admin(String username, String password, String name, String workExperience, String preferredWorkTimings) {
        super(username, password);
        this.name = name;
        this.workExperience = workExperience;
        this.preferredWorkTimings = preferredWorkTimings;
    }

    @Override
    public String getRole() {
        return "Admin";
    }
    
    //method to view customers
        public void viewCustomers(UserManagementSystem userManagementSystem) {
        List<User> users = userManagementSystem.getUsers();
        System.out.println("List of Customers:");
        System.out.println("-----------------");
        for (User user : users) {
            if (user instanceof RegularUser) {
                RegularUser regularUser = (RegularUser) user;
                System.out.println("Username: " + regularUser.getUsername());
                System.out.println("Role: " + regularUser.getRole());
                System.out.println("City: " + regularUser.getCity());
                System.out.println("Landmark: " + regularUser.getLandmark());
                System.out.println("House Number: " + regularUser.getHouseNumber());
                System.out.println("Pin Code: " + regularUser.getPinCode());
                System.out.println("Phone Number: " + regularUser.getPhoneNumber());
                System.out.println("-------------------------");
            }
        }
    }

    // Method to add staff
    public void addStaff(UserManagementSystem userManagementSystem, Scanner scanner) {
        System.out.println("Add Staff:");
        System.out.print("Enter role (Manager/DeliveryAgent): ");
        String role = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter work experience: ");
        String workExperience = scanner.nextLine();
        System.out.print("Enter previous workplace: ");
        String previousWorkplace = scanner.nextLine();
        // Additional staff-specific details can be added here
        if (userManagementSystem.signUp(role, username, password, name, workExperience, previousWorkplace)) {
            System.out.println("----------------------------------");
            System.out.println("Staff added successfully!");
            System.out.println("----------------------------------");
        } else {
            System.out.println("----------------------------------");
            System.out.println("Failed to add staff. Username already exists.");
            System.out.println("----------------------------------");
        }
    }

    // Method to remove staff
    public void removeStaff(UserManagementSystem userManagementSystem, Scanner scanner) {
        System.out.println("Remove Staff:");
        System.out.println("----------");
        System.out.print("Enter username of the staff to remove: ");
        String username = scanner.nextLine();
        Optional<User> userOptional = userManagementSystem.getUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("----------------------------------");
            System.out.println("Staff removed successfully:");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());
            System.out.println("----------------------------------");
            userManagementSystem.removeUserByUsername(username);
        } else {
            System.out.println("----------------------------------");
            System.out.println("Staff with username " + username + " not found.");
            System.out.println("----------------------------------");
        }
    }

    // Method to view feedback
    public void viewFeedback(OrderManagementSystem oms) {
        System.out.println("View Feedback:");
        System.out.println("----------");
        List<Order> allOrders = oms.getAllOrders();

        for (Order order : allOrders) {
            if (order.getFeedback() != null && !order.getFeedback().isEmpty()) {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Customer: " + order.getCustomerName());
                System.out.println("Product: " + order.getProduct().getName());
                System.out.println("Feedback: " + order.getFeedback());
                System.out.println("----------------------------------");
            }
        }
    }
    
    //method to view managers
    public void viewManagers(UserManagementSystem userManagementSystem) {
        System.out.println("Managers:");
        System.out.println("---------");
        List<Manager> managers = userManagementSystem.getManagers();
        for (Manager manager : managers) {
            System.out.println("Name: " + manager.getName());
            System.out.println("Work Experience: " + manager.getWorkExperience());
            System.out.println("Preferred Work Timings: " + manager.getPreferredWorkTimings());
            System.out.println("----------------------------------");
        }
    }
    
    //method to view delivery agents 
    public void viewDeliveryAgents(UserManagementSystem userManagementSystem) {
        System.out.println("Delivery Agents:");
        System.out.println("------------");
        List<DeliveryAgent> deliveryAgents = userManagementSystem.getDeliveryAgents();
        for (DeliveryAgent agent : deliveryAgents) {
            System.out.println("Name: " + agent.getName());
            System.out.println("Work Experience: " + agent.getWorkExperience());
            System.out.println("Preferred Work Timings: " + agent.getPreferredWorkTimings());
            System.out.println("----------------------------------");
        }
    }
    
    // Increase price of the most frequently purchased product based on a particular algorithm
    public void increasePriceOfPopularProduct(OrderManagementSystem oms) {
        List<Product> products = oms.getAllProducts();
        Product mostPopularProduct = findMostPopularProduct(products);
        if (mostPopularProduct != null) {
            double incrementPercentage = getUserInputForIncrementPercentage(); // Get user input for increment percentage
            double newPrice = calculateNewPriceForPopularProduct(mostPopularProduct.getPrice(), mostPopularProduct.getPurchaseCount(), incrementPercentage);
            mostPopularProduct.setPrice(newPrice);
            System.out.println("----------------------------------");
            System.out.println("Price of the most popular product (" + mostPopularProduct.getName() + ") increased to: $" + newPrice);
            System.out.println("----------------------------------");
        } else {
            System.out.println("----------------------------------");
            System.out.println("No products found.");
            System.out.println("----------------------------------");
        }
    }

    // Find the most frequently purchased product
    private Product findMostPopularProduct(List<Product> products) {
        Product mostPopularProduct = null;
        int maxPurchaseCount = 0;
        for (Product product : products) {
            if (product.getPurchaseCount() > maxPurchaseCount) {
                mostPopularProduct = product;
                maxPurchaseCount = product.getPurchaseCount();
            }
        }
        return mostPopularProduct;
    }

    // Calculate new price for the most popular product based on a specific algorithm
    private double calculateNewPriceForPopularProduct(double currentPrice, int purchaseCount, double incrementPercentage) {
        int purchasePairs = purchaseCount / 2; // Calculate how many pairs of purchases
        return currentPrice * Math.pow(1 + incrementPercentage, purchasePairs); // Increment price for each pair
    }
    
    // Get user input for increment percentage
    private double getUserInputForIncrementPercentage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the percentage by which the price should increase for every 2 purchases: ");
        double incrementPercentage = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return incrementPercentage / 100.0; // Convert percentage to decimal
    }
    
}