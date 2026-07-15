package p;

import java.util.*;
 
class DeliveryAgent extends User {
    private String name;
    private String workExperience;
    private String preferredWorkTimings;

    public DeliveryAgent(String username, String password, String name, String workExperience, String preferredWorkTimings) {
        super(username, password);
        this.name = name;
        this.workExperience = workExperience;
        this.preferredWorkTimings = preferredWorkTimings;
    }
    
    @Override
    public String getRole() {
        return "DeliveryAgent";
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
    //the receiveOrder function is getting all the the orders from the oms class and if the the name of the 
    //delivery agent is equal to the name present in the orders list , then we print the order.
    public void receiveOrder(OrderManagementSystem oms) {
        System.out.println("Received Orders:");
        System.out.println("------------");
        for (Order order : oms.getAllOrders()) {
            if (order.getAssignedAgent() != null && order.getAssignedAgent().equals(this.name)) {
                System.out.println(order);
                System.out.println("----------------------------------");
            }
        }
    }

    public void updateOrderStatus(OrderManagementSystem oms) {
        System.out.println("Update Order Status:");
        System.out.println("-------------------");
        for (Order order : oms.getAllOrders()) {
            if (order.getAssignedAgent() != null && order.getAssignedAgent().equals(this.name)) {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Location: " + order.getLocation());
                System.out.println("Expected Time: " + order.getExpectedTime());
                System.out.println("Status: " + order.getStatus());
                System.out.println("----------------------------------");
            }
        }
        if (!oms.getAllOrders().stream().anyMatch(order -> order.getAssignedAgent() != null && order.getAssignedAgent().equals(this.name))) {
            System.out.println("Orders are not assigned.");
            System.out.println("----------------------------------");
        }
 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter order ID to update status: ");
        System.out.println("----------------------------------");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Optional<Order> orderOptional = oms.getOrderById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            if (order.getAssignedAgent() != null && order.getAssignedAgent().equals(this.name)) {
                if (order.getStatus().equals("pending")) {
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter expected time: ");
                    String expectedTime = scanner.nextLine();
                    System.out.print("Enter status: ");
                    String status = scanner.nextLine();
                    order.setLocation(location);
                    order.setExpectedTime(expectedTime);
                    order.setStatus(status);
                    System.out.println("----------------------------------");
                    System.out.println("Order status updated.");
                    System.out.println("----------------------------------");
                } else if(order.getStatus().equals("incomplete")){
                    System.out.print("Enter status: ");
                    String reason = scanner.nextLine();
                    order.setreason(reason);
                    System.out.println("----------------------------------");
                    System.out.println("Order status updated.");
                    System.out.println("----------------------------------");
                } else {
                    System.out.println("Order status is not pending.");
                    System.out.println("----------------------------------");
                }
            } else {
                System.out.println("This order is not assigned to you.");
                System.out.println("----------------------------------");
            }
        } else {
            System.out.println("Order not found.");
            System.out.println("----------------------------------");
        }
    }
    
    public void trackOrder(OrderManagementSystem oms) {
    System.out.println("Previous Orders: ");
    System.out.println("----------------------------------");    
        for (Order order : oms.getAllOrders()) {
            if (order.getAssignedAgent() != null && order.getAssignedAgent().equals(this.name)) {
                if("delivered".equalsIgnoreCase(order.getStatus())){
                    System.out.println("Order ID: " + order.getId());
                    System.out.println("Customer Name: " + order.getCustomerName());
                    System.out.println("Product: " + order.getProduct());
                    System.out.println("Quantity: " + order.getQuantity());
                    System.out.println("City: " + order.getCity());
                    System.out.println("Landmark: " + order.getLandmark());
                    System.out.println("House Number: " + order.getHouseNumber());
                    System.out.println("Pin Code: " + order.getPinCode());
                    System.out.println("Phone Number: " + order.getPhoneNumber());
                    System.out.println("Location: " + order.getLocation());
                    System.out.println("Expected Time: " + order.getExpectedTime());
                    System.out.println("Status: " + order.getStatus());
                    System.out.println("----------------------------------");
                }
                else if("incomplete".equalsIgnoreCase(order.getStatus())){
                    System.out.println("Order ID: " + order.getId());
                    System.out.println("Customer Name: " + order.getCustomerName());
                    System.out.println("Product: " + order.getProduct());
                    System.out.println("Quantity: " + order.getQuantity());
                    System.out.println("City: " + order.getCity());
                    System.out.println("Landmark: " + order.getLandmark());
                    System.out.println("House Number: " + order.getHouseNumber());
                    System.out.println("Pin Code: " + order.getPinCode());
                    System.out.println("Phone Number: " + order.getPhoneNumber());
                    System.out.println("Location: " + order.getLocation());
                    System.out.println("Expected Time: " + order.getExpectedTime());
                    System.out.println("Status: " + order.getStatus());
                    System.out.println("Reason: " + order.getreason());
                    System.out.println("----------------------------------");
                }
            }
        }
    }
    
    public void getCancelledOrders(OrderManagementSystem oms) {
        System.out.println("Cancelled Orders:");
        System.out.println("-----------------");
        for (Order order : oms.getAllCanceledOrders()) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Product: " + order.getProduct());
            System.out.println("Quantity: " + order.getQuantity());
            System.out.println("City: " + order.getCity());
            System.out.println("Landmark: " + order.getLandmark());
            System.out.println("House Number: " + order.getHouseNumber());
            System.out.println("Pin Code: " + order.getPinCode());
            System.out.println("Phone Number: " + order.getPhoneNumber());
            System.out.println("----------------------------------");
        }
    }
    
}
        