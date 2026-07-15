package p;

import java.util.*;

// Manager class creates a manager object that holds information about the managers. It has get methods to access those attributes. Manager has certain specific methods like
// managing the products of the company, managing the delivery, and viewing the feedbacks.

class Manager extends User {
    private String name;
    private String workExperience;
    private String preferredWorkTimings;
    
    private List<String> assignedDeliveries = new ArrayList<>();

    public Manager(String username, String password, String name, String workExperience, String preferredWorkTimings) {
        super(username, password);
        this.name = name;
        this.workExperience = workExperience;
        this.preferredWorkTimings = preferredWorkTimings;
    }

    @Override
    public String getRole() {
        return "Manager";
    }
    
    public String getName() {
        return name;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getPreferredWorkTimings() {
        return preferredWorkTimings;
    }

    // Manager-specific methods
    public void manageProducts(OrderManagementSystem oms, Scanner scanner) {
        System.out.println("Manage Products:");
        System.out.println("-------------");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View All Products");
        System.out.println("5. View Specific Product");
        System.out.println("6. Generate Report");
        System.out.println("----------");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("----------------------------------");
        
        switch (choice) {
            case 1:
                // Add Product
                System.out.print("Enter product name: ");
                String productName = scanner.nextLine();
                System.out.print("Enter product price: ");
                double productPrice = scanner.nextDouble();
                oms.addProduct(productName, productPrice);
                System.out.println("----------------------------------");
                System.out.println("Product added successfully!");
                System.out.println("----------------------------------");
                break;
            case 2:
                // Update Product
                System.out.print("Enter product ID to update: ");
                int productId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new product name: ");
                String newProductName = scanner.nextLine();
                System.out.print("Enter new product price: ");
                double newProductPrice = scanner.nextDouble();

                if (oms.updateProduct(productId, newProductName, newProductPrice)) {
                    System.out.println("----------------------------------");
                    System.out.println("Product updated successfully!");
                    System.out.println("----------------------------------");
                } else {
                    System.out.println("----------------------------------");
                    System.out.println("Product update failed. Product may not exist.");
                    System.out.println("----------------------------------");
                }
                break;
            case 3:
                // Delete Product
                System.out.print("Enter product ID to delete: ");
                int deleteProductId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                oms.deleteProduct(deleteProductId);
                System.out.println("----------------------------------");
                System.out.println("Product deleted successfully!");
                System.out.println("----------------------------------");
                break;
            case 4:
                // View All Products
                System.out.println("All products:");
                System.out.println("-----------");
                oms.getAllProducts().forEach(System.out::println);
                System.out.println("----------------------------------");
                break;
            case 5:
                // View Specific Product
                System.out.print("Enter product ID to view: ");
                int viewProductId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                oms.getProductById(viewProductId).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Product not found.")
                );
                System.out.println("----------------------------------");
                break;
            case 6:
                // Generate Report
                generateReport(oms);
                break;
            default:
                System.out.println("----------------------------------");
                System.out.println("Invalid option. Please try again.");
                System.out.println("----------------------------------");
        }
    }

    public void manageDelivery(OrderManagementSystem oms, Scanner scanner) {
        System.out.println("Manage Delivery:");
        System.out.println("------------");
        System.out.println("1. Assign Delivery Agent");
        System.out.println("2. View Assigned Deliveries");
        System.out.println("----------");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("----------------------------------");
        
        switch (choice) {
            case 1:
                // Assign Delivery Agent
                List<Order> allOrdersCopy = oms.getAllOrdersCopy(oms.getAllOrders(), oms.getAssignedDeliveries());
                List<String> deliveryAgentsNames = oms.getAllDeliveryAgentsNames(oms.getUserManagementSystem());

                System.out.println("Available orders:");
                System.out.println("--------");
                allOrdersCopy.forEach(System.out::println);
                System.out.println("----------------------------------");

                System.out.println("Available delivery agents:");
                System.out.println("----------");
                deliveryAgentsNames.forEach(System.out::println);
                System.out.println("----------------------------------");

                System.out.print("Enter order ID: ");
                int orderId = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.print("Enter delivery agent's name: ");
                String deliveryAgentName = scanner.nextLine();

                Optional<Order> orderOptional = allOrdersCopy.stream().filter(order -> order.getId() == orderId).findFirst();
                if (orderOptional.isPresent()) {
                    Order order = orderOptional.get();
                    allOrdersCopy.remove(order);
                    oms.getOrderById(orderId).get().setAssignedAgent(deliveryAgentName);
                    deliveryAgentsNames.remove(deliveryAgentName);

                    // Add the assigned delivery to the view
                    assignedDeliveries.add("Order ID: " + order.getId() + ", Delivery Agent: " + deliveryAgentName);
                    
                    System.out.println("-------------------------");
                    
                    // Print the revised lists
                    System.out.println("Available orders after assignment:");
                    allOrdersCopy.forEach(System.out::println);
                    System.out.println("---------");
                    
                    System.out.println("Available delivery agents after assignment:");
                    deliveryAgentsNames.forEach(System.out::println);
                    System.out.println("----------------------------------");
                    
                } else {
                    System.out.println("----------------------------------");
                    System.out.println("Order not found.");
                    System.out.println("----------------------------------");
                }
                break;
            case 2:
                // View Assigned Deliveries
                System.out.println("Assigned deliveries:");
                System.out.println("--------");
                assignedDeliveries.forEach(System.out::println);
                System.out.println("----------------------------------");
                break;
            default:
                System.out.println("----------------------------------");
                System.out.println("Invalid option. Please try again.");
                System.out.println("----------------------------------");
        }
    }

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

    public void generateReport(OrderManagementSystem oms) {
    System.out.println("Generating Report:");
    System.out.println("=================");
    
    // Products Report
    System.out.println("Products Report:");
    System.out.println("==================================================");
    System.out.printf("%-10s %-25s %-10s%n", "Product ID", "Product Name", "Price");
    System.out.println("--------------------------------------------------");
    for (Product product : oms.getAllProducts()) {
        System.out.printf("%-10d %-25s %-10.2f%n", product.getId(), product.getName(), product.getPrice());
    }
    System.out.println("==================================================");

    // Orders Report
    System.out.println("Orders Report:");
    System.out.println("================================================================================================");
    System.out.printf("%-10s %-20s %-25s %-10s %-10s %-10s%n", "Order ID", "Customer", "Product", "Quantity", "Price", "Status");
    System.out.println("---------------------------------------------------------------------------------");
    for (Order order : oms.getAllOrders()) {
        System.out.printf("%-10d %-20s %-25s %-10d %-10.2f %-10s%n", 
                          order.getId(), 
                          order.getCustomerName(), 
                          order.getProduct().getName(), 
                          order.getQuantity(), 
                          order.getProduct().getPrice() * order.getQuantity(), 
                          order.getStatus());
    }
    System.out.println("================================================================================================");

    System.out.println("Report generated successfully!");
    System.out.println("----------------------------------");
}


    public void viewUserDetails(OrderManagementSystem oms, Scanner scanner) {
        System.out.println("View User Details:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        Optional<User> userOpt = oms.getUserManagementSystem().getUserByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("User Details:");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());
            if (user instanceof RegularUser) {
                RegularUser regularUser = (RegularUser) user;
                System.out.println("City: " + regularUser.getCity());
                System.out.println("Landmark: " + regularUser.getLandmark());
                System.out.println("House Number: " + regularUser.getHouseNumber());
                System.out.println("Pin Code: " + regularUser.getPinCode());
                System.out.println("Phone Number: " + regularUser.getPhoneNumber());
                System.out.println("Feedbacks:");
                oms.getAllOrders().stream().filter(order -> order.getCustomerName().equals(regularUser.getUsername())).map(Order::getFeedback).filter(feedback -> feedback != null && !feedback.isEmpty()).forEach(System.out::println);
            }
            System.out.println("----------------------------------");
        } else {
            System.out.println("User not found.");
            System.out.println("----------------------------------");
        }
    }
}
