package p;

import java.util.*;
import java.io.*;

//order management system is the most important class in this project. it contains attributes to hold information about the list of orders by the customers , list of products in
// the company , nextOrderID variable , userManagement System , it has a 2d list of assigned Deliveries. it has methods to get the UserManagement System , to create the order ,
//add products , update the products , delete products , get product and order by its id , get all the orders , get all the products , get the delivery agents names , get all the
// get all the assigned deliveries.

public class OrderManagementSystem {
    private List<Order> orders = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private int nextOrderId = 1;
    private UserManagementSystem userManagementSystem = new UserManagementSystem();
    private List<List<String>> assignedDeliveries = new ArrayList<>();
    private List<Order> canceledOrders = new ArrayList<>();

    public OrderManagementSystem() {
        // Adding some products to the product list
        products.add(new Product(1, "Laptop", 1200.00));
        products.add(new Product(2, "Smartphone", 800.00));
        products.add(new Product(3, "Tablet", 400.00));

    }

    
    public UserManagementSystem getUserManagementSystem() {
        return userManagementSystem;
    }

    // Create a new order
    public Order createOrder(String username, int productId, int quantity) {
    Optional<User> userOpt = userManagementSystem.getUserByUsername(username);
    if (!userOpt.isPresent() || !(userOpt.get() instanceof RegularUser)) {
        System.out.println("----------------------------------");
        System.out.println("User not found or not a regular user");
        System.out.println("----------------------------------");
        return null;
    }

    RegularUser regularUser = (RegularUser) userOpt.get();
    Optional<Product> productOpt = getProductById(productId);
    if (productOpt.isPresent()) {
        Product product = productOpt.get(); // Retrieve the product
        // Increment the purchase count of the product
        product.setPurchaseCount(product.getPurchaseCount() + quantity);

        Order order = new Order(
            nextOrderId++, 
            regularUser.getUsername(), 
            product, 
            quantity, 
            regularUser.getCity(), 
            regularUser.getLandmark(), 
            regularUser.getHouseNumber(), 
            regularUser.getPinCode(), 
            regularUser.getPhoneNumber(),
            "...update please....",
            "0",
            "pending",
            "...update please...",
            "...update please..."
        );
        orders.add(order);
        return order;
    } else {
        System.out.println("----------------------------------");
        System.out.println("Product not found");
        System.out.println("----------------------------------");
        return null;
    }
}


    //add a new product
    public void addProduct(String name, double price) {
        int newProductId = products.size() + 1; // Generate new product ID
        Product newProduct = new Product(newProductId, name, price);
        products.add(newProduct);
    }

    //to update a product 
    public boolean updateProduct(int id, String newName, double newPrice) {
        Optional<Product> productOpt = getProductById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(newName); // Use the setName method to update the name
            product.setPrice(newPrice); // Use the setPrice method to update the price
            System.out.println("----------------------------------");
            System.out.println("Product updated successfully!");
            System.out.println("----------------------------------");
            return true;
        } else {
            System.out.println("----------------------------------");
            System.out.println("Product not found");
            System.out.println("----------------------------------");
            return false;
        }
    }

    // Delete a product
    public void deleteProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }
    
    public void addOrder(Order order) {
        orders.add(order);
    }
    
    // Delete a order
    public void deleteOrder(int id) {
        Optional<Order> orderOpt = getOrderById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            orders.remove(order);
            canceledOrders.add(order); // Add to the canceled orders list
        }
    }

    public List<Order> getAllCanceledOrders() {
        return canceledOrders;
    }

    // Retrieve an order by ID
    public Optional<Order> getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst();
    }

    // Retrieve all orders
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    // Retrieve all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    // Get product by ID
    Optional<Product> getProductById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }
    
    // Create a copy of all orders of all users
    public List<Order> getAllOrdersCopy(List<Order> allOrders, List<String> assignedDeliveries) {
    List<Order> allOrdersCopy = new ArrayList<>(allOrders);

    // Exclude orders that have already been assigned
    allOrdersCopy.removeIf(order -> assignedDeliveries.stream().anyMatch(assignedDelivery -> assignedDelivery.contains(String.valueOf(order.getId()))));

    return allOrdersCopy;
}

    // Get a list of all delivery agents' names
    public List<String> getAllDeliveryAgentsNames(UserManagementSystem userManagementSystem) {
        List<String> deliveryAgentsNames = new ArrayList<>();
        for (User user : userManagementSystem.getUsers()) {
            if (user instanceof DeliveryAgent) {
                DeliveryAgent deliveryAgent = (DeliveryAgent) user;
                deliveryAgentsNames.add(deliveryAgent.getName());
            }
        }
        return deliveryAgentsNames;
    }
    
    //to display the assigned deliveries 
    public List<String> getAssignedDeliveries() {
        List<String> assignedOrderIds = new ArrayList<>();
        for (List<String> assignedDelivery : assignedDeliveries) {
            assignedOrderIds.addAll(assignedDelivery);
    }
    return assignedOrderIds;
    }

    // MAIN function
    public static void main(String[] args) {
//        String csvFilePath = "C:\\Users\\bhavy\\OneDrive\\Desktop\\java_project_oms_1\\java_project_oms\\src\\order_management_system_1\\Product.csv";
        OrderManagementSystem oms = new OrderManagementSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------");
        System.out.println("Welcome to the Order Management System!");
        System.out.println("----------------------------------");
        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. View Products");
            System.out.println("4. Exit");
            System.out.println("-----------");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                // Sign Up
                String username;
                String password;
                System.out.print("Enter role: \n Admin \n DeliveryAgent \n Manager \n User \n ENTER : ");
                String role = scanner.nextLine();
                System.out.println("----------");
                if ("user".equalsIgnoreCase(role)) {
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    System.out.print("Enter city: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter landmark: ");
                    String landmark = scanner.nextLine();
                    System.out.print("Enter house number: ");
                    String houseNumber = scanner.nextLine();
                    System.out.print("Enter pin code: ");
                    String pinCode = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                        if (oms.userManagementSystem.signUpAsUser( username, password, city, landmark, houseNumber, pinCode, phoneNumber)) {
                            System.out.println("----------------------------------");
                            System.out.println("Sign up successful!");
                            System.out.println("----------------------------------");
                        } 
                        else {
                            System.out.println("----------------------------------");
                            System.out.println("Username already exists. Please try again.");
                            System.out.println("----------------------------------");
                        }
                }
                else if ("admin".equalsIgnoreCase(role) || "manager".equalsIgnoreCase(role) || "deliveryagent".equalsIgnoreCase(role)) {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter work experience: ");
                    String workExperience = scanner.nextLine();
                    System.out.print("Enter preferred work timings: ");
                    String preferredWorkTimings = scanner.nextLine();
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                        if (oms.userManagementSystem.signUp(role, username, password, name, workExperience, preferredWorkTimings)) {
                            System.out.println("----------------------------------");
                            System.out.println("Sign up successful!");
                            System.out.println("----------------------------------");
                        } 
                        else {
                            System.out.println("----------------------------------");
                            System.out.println("Username already exists or invalid role. Please try again.");
                            System.out.println("----------------------------------");
                        }
                } 
                else {
                    System.out.println("----------------------------------");
                    System.out.println("Invalid role. Please try again.");
                    System.out.println("----------------------------------");
                }
            } 
            else if (choice == 2) {
                // Log In
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                Optional<User> userOptional = oms.userManagementSystem.login(username, password);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    System.out.println("----------------------------------");
                    System.out.println("Login successful! Role: " + user.getRole());
                    System.out.println("----------------------------------");
                    boolean loggedIn = true;
                    while (loggedIn) {
                        switch (user.getRole().toLowerCase()) {
                            case "admin":
                                System.out.println("Admin Menu");
                                System.out.println("-----------");
                                System.out.println("1. View Customers");
                                System.out.println("2. View Staff");
                                System.out.println("3. Add Staff");
                                System.out.println("4. Remove Staff");
                                System.out.println("5. View Feedback");
                                System.out.println("6. Adjust Price of Product");
                                System.out.println("7. Log Out");
                                System.out.println("-------");
                                System.out.print("Choose an option: ");
                                int adminChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.println("--------");
                                Admin admin = (Admin) user;
                                switch (adminChoice) {
                                    case 1:
                                        admin.viewCustomers(oms.userManagementSystem);
                                    break;
                                    case 2:
                                        System.out.println("View Staff:");
                                        System.out.println("-----------");
                                        System.out.println("1. View Managers");
                                        System.out.println("2. View Delivery Agents");
                                        System.out.println("------------");
                                        System.out.print("Choose an option: ");
                                        int staffChoice = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                                        System.out.println("---------");
                                        
                                      switch (staffChoice) {
                                            case 1:
                                             // View Managers
                                                admin.viewManagers(oms.userManagementSystem);
                                            break;
                                            case 2:
                                             // View Delivery Agents
                                                admin.viewDeliveryAgents(oms.userManagementSystem);
                                            break;
                                            default:
                                                System.out.println("----------------------------------");
                                                System.out.println("Invalid choice. Please try again.");
                                                System.out.println("----------------------------------");
                                            }
                                            break;
                                            case 3:
                                                admin.addStaff(oms.userManagementSystem, scanner);
                                            break;
                                            case 4:
                                                admin.removeStaff(oms.userManagementSystem, scanner);
                                            break;
                                            case 5:
                                                admin.viewFeedback(oms);
                                            break;
                                            case 6:
                                                admin.increasePriceOfPopularProduct(oms);
                                            break;
                                            case 7:
                                                loggedIn = false;
                                                System.out.println("----------------------------------");
                                                System.out.println("Logged out successfully!");
                                                System.out.println("----------------------------------");
                                            break;
                                            default:
                                                System.out.println("----------------------------------");
                                                System.out.println("Invalid option. Please try again.");
                                                System.out.println("----------------------------------");
                                }
                                break;
                            case "deliveryagent":
                                System.out.println("Delivery Agent Menu");
                                System.out.println("--------");
                                DeliveryAgent deliveryAgent = (DeliveryAgent) user;
                                System.out.println("1. Receive Order");
                                System.out.println("2. Track Order");
                                System.out.println("3. Update Order Status");
                                System.out.println("4. View Cncelled Orders");
                                System.out.println("5. Log Out");
                                System.out.println("---------");
                                System.out.print("Choose an option: ");
                                int deliveryAgentChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.println("---------");
    
                                switch (deliveryAgentChoice) {
                                    case 1:
                                        // Receive Order
                                        deliveryAgent.receiveOrder(oms);
                                    break;
                                    case 2:
                                        // Track Order
                                        deliveryAgent.trackOrder(oms);
                                    break;
                                    case 3:
                                        // Update Order Status
                                        deliveryAgent.updateOrderStatus(oms);
                                    break;
                                    case 4:
                                        // view cancelled orders
                                        deliveryAgent.getCancelledOrders(oms);
                                    break;
                                    case 5:
                                        loggedIn = false;
                                        System.out.println("----------------------------------");
                                        System.out.println("Logged out successfully!");
                                        System.out.println("----------------------------------");
                                    break;
                                    default:
                                        System.out.println("----------------------------------");
                                         System.out.println("Invalid choice. Please try again.");
                                         System.out.println("----------------------------------");
                                }
                                break;
                            case "manager":
                                System.out.println("Manager Menu");
                                System.out.println("------------");
                                Manager manager = (Manager) user;
                                System.out.println("1. Manage Products");
                                System.out.println("2. Manage Delivery");
                                System.out.println("3. View Feedback");
                                System.out.println("4. Log Out");
                                System.out.println("----------------");
                                System.out.print("Choose an option: ");
                                int managerChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.println("--------------");

                                switch (managerChoice) {
                                    case 1:
                                        manager.manageProducts(oms, scanner);
                                        break;
                                    case 2:
                                        manager.manageDelivery(oms, scanner);
                                        break;
                                    case 3:
                                        // View Feedback
                                        manager.viewFeedback(oms);
                                        break;
                                    case 4:
                                        loggedIn = false;
                                        System.out.println("----------------------------------");
                                        System.out.println("Logged out successfully!");
                                        System.out.println("----------------------------------");
                                        break;
                                    default:
                                        System.out.println("----------------------------------");
                                        System.out.println("Invalid choice. Please try again.");
                                        System.out.println("----------------------------------");
                                }
                                break;
                            case "user":
                                RegularUser regularUser = (RegularUser) user;
                                System.out.println("User Menu");
                                System.out.println("-------------");
                                System.out.println("1. View Products");
                                System.out.println("2. Make Order");
                                System.out.println("3. View All Orders");
                                System.out.println("4. View Order Status");
                                System.out.println("5. Cancel order");
                                System.out.println("6. Update Account Info");
                                System.out.println("7. Feedback");
                                System.out.println("8. Log Out");
                                System.out.println("-----------");
                                System.out.print("Choose an option: ");
                                int loggedInChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.println("----------------------------------");

                                switch (loggedInChoice) {
                                    case 1:
                                        // View Products
                                        System.out.println("Available Products:");
                                        System.out.println("----------");
                                        oms.getAllProducts().forEach(System.out::println);
                                        System.out.println("----------------------------------");
                                        break;
                                    case 2:
                                        // Make Order
                                        System.out.print("Enter product ID: ");
                                        int productId = scanner.nextInt();
                                        System.out.print("Enter quantity: ");
                                        int quantity = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                                        Order newOrder = oms.createOrder(username, productId, quantity);
                                        if (newOrder != null) {
                                            System.out.println("----------------------------------");
                                            System.out.println("Order created successfully!");
                                            System.out.println("----------------------------------");
                                            System.out.println(newOrder);
                                        } else {
                                            System.out.println("----------------------------------");
                                            System.out.println("Order creation failed.");
                                            System.out.println("----------------------------------");
                                        }
                                        break;
                                    case 3:
                                        // View All Orders
                                        System.out.println("All orders:");
                                        System.out.println("------------");
                                        oms.getAllOrders().forEach(System.out::println);
                                        System.out.println("----------------------------------");
                                        break;
                                    case 4:
                                        System.out.print("Enter order ID: ");
                                        int orderId = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                                        regularUser.track_Order(oms, orderId);
                                        break;
                                    case 5:
                                        regularUser.cancelOrder(oms, scanner);
                                        break;
                                    case 6:
                                        regularUser.updateAccountInfo(oms, scanner);
                                        break;
                                    case 7:
                                        // Provide Feedback
                                        System.out.println("Provide Feedback:");
                                        System.out.println("-----------");
                                        regularUser.provideFeedback(oms, scanner);
                                        break;
                                    case 8:
                                        // Log Out
                                        loggedIn = false;
                                        System.out.println("----------------------------------");
                                        System.out.println("Logged out successfully!");
                                        System.out.println("----------------------------------");
                                        break;
                                    default:
                                        System.out.println("----------------------------------");
                                        System.out.println("Invalid choice. Please try again.");
                                        System.out.println("----------------------------------");
                                }
                                break;
                            default:
                                System.out.println("----------------------------------");
                                System.out.println("Invalid role. Please try again.");
                                System.out.println("----------------------------------");
                                loggedIn = false;
                        }
                    }
                } 
                else {
                    System.out.println("----------------------------------");
                    System.out.println("Invalid username or password. Please try again.");
                    System.out.println("----------------------------------");
                }
            } 
            else if (choice == 3) {
                // View Products without logging in
                System.out.println("Available Products:");
                System.out.println("--------------");
                oms.getAllProducts().forEach(System.out::println);
                System.out.println("----------------------------------");
            } 
            else if (choice == 4) {
                // Exit
                System.out.println("----------------------------------");
                System.out.println("Goodbye!Thank you for shopping with us 😊!!");
                System.out.println("----------------------------------");
                break;
            } else {
                System.out.println("----------------------------------");
                System.out.println("Invalid choice. Please try again.");
                System.out.println("----------------------------------");
            }
        }

        scanner.close();
    }
}
