package p;

import java.util.*;

class RegularUser extends User {
    private List<String> feedbacks;
    private String city;
    private String landmark;
    private String houseNumber;
    private String pinCode;
    private String phoneNumber;
    
    public RegularUser(String username, String password, String city, String landmark, String houseNumber, String pinCode, String phoneNumber) {
        super(username, password);
        this.feedbacks = new ArrayList<>();
        this.city = city;
        this.landmark = landmark;
        this.houseNumber = houseNumber;
        this.pinCode = pinCode;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getRole() {
        return "User";
    }

    public List<String> getFeedbacks() {
        return feedbacks;
    }
    
    public String getCity() {
        return city;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void provideFeedback(OrderManagementSystem oms, Scanner scanner) {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter feedback: ");
        String userFeedback = scanner.nextLine();
        System.out.print("Enter your username: ");
        String enteredUsername = scanner.nextLine();

        Optional<Order> orderOpt = oms.getOrderById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            if (order.getCustomerName().equals(enteredUsername)) {
                order.setFeedback(userFeedback);
                System.out.println("----------------------------------");
                System.out.println("Feedback added successfully!");
                System.out.println("----------------------------------");
            } else {
                System.out.println("----------------------------------");
                System.out.println("You can only provide feedback for your own orders.");
                System.out.println("----------------------------------");
            }
        } else {
            System.out.println("----------------------------------");
            System.out.println("Order not found.");
            System.out.println("----------------------------------");
        }
    }

    void cancelOrder(OrderManagementSystem oms, Scanner scanner) {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your username: ");
        String enteredUsername = scanner.nextLine();

        Optional<Order> orderOpt = oms.getOrderById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            if (order.getCustomerName().equals(enteredUsername)) {
                
                oms.deleteOrder(orderId);
                System.out.println("----------------------------------");
                System.out.println("Order canceled successfully!");
                System.out.println("----------------------------------");
            } else {
                System.out.println("----------------------------------");
                System.out.println("Username does not match the order's customer name.");
                System.out.println("----------------------------------");
            }
        } else {
            System.out.println("----------------------------------");
            System.out.println("Order not found.");
            System.out.println("----------------------------------");
        }
    }

    void updateAccountInfo(OrderManagementSystem oms, Scanner scanner) {
        System.out.print("Enter new city: ");
        this.city = scanner.nextLine();
        System.out.print("Enter new landmark: ");
        this.landmark = scanner.nextLine();
        System.out.print("Enter new house number: ");
        this.houseNumber = scanner.nextLine();
        System.out.print("Enter new pin code: ");
        this.pinCode = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        this.phoneNumber = scanner.nextLine();
        System.out.println("----------------------------------");
        System.out.println("Account information updated successfully!");
        System.out.println("----------------------------------"); 
    }

    void track_Order(OrderManagementSystem oms, int orderId) {
        Optional<Order> orderOpt = oms.getOrderById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            if (order.getCustomerName().equals(this.getUsername())) {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Status: " + order.getStatus());
            } else {
                System.out.println("----------------------------------");
                System.out.println("Order not found or you do not have permission to view this order.");
                System.out.println("----------------------------------");
            }
        } else {
            System.out.println("----------------------------------");
            System.out.println("Order not found.");
            System.out.println("----------------------------------");
        }
    }
}
