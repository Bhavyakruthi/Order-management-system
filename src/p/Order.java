package p;

import java.util.*;

// order class is used to create an object which holds the information about each order a customer is making. it has attributes order ID , customerName, product ordered , quantity of
// the product and the name of the delivery agent who will deliver the order to the customer. it has methods to get all the attributes. it has set methods to set customerName,product,
//quantity , assignedAgent.

class Order {
    private int id;
    private String customerName;
    private Product product;
    private int quantity;
    private String assignedAgent;
    private String city;
    private String landmark;
    private String houseNumber;
    private String pinCode;
    private String phoneNumber;
    private String location;
    private String expectedTime;
    private String status;
    private String reason;
    private String feedback;

    public Order(int id, String customerName, Product product, int quantity, 
                String city, String landmark, String houseNumber, String pinCode, 
                String phoneNumber,String location, String expectedTime, String status,String reason, String feedback) {
        this.id = id;
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.assignedAgent = null;
        this.city = city;
        this.landmark = landmark;
        this.houseNumber = houseNumber;
        this.pinCode = pinCode;
        this.phoneNumber = phoneNumber;
        this.location = "...update please....";
        this.expectedTime = "0";
        this.status = "pending";
        this.reason = "...update please...";
        this.feedback = "...update please...";
    }

    // getters and setters for the new fields
    public String getCity() {
        return city;
    }
    
    // Getter and setter for feedback
    public String getFeedback() {
        return feedback;
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
    
    public String getLocation() {
        return location;
    }


    public String getExpectedTime() {
        return expectedTime;
    }


    public String getStatus() {
        return status;
    }
    
    public String getreason(){
        return reason;
    }

    // existing getters and setters
    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public String getAssignedAgent() {
        return assignedAgent;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setAssignedAgent(String assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setreason(String reason){
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Order: {" +
                "\n -----------"+
                "\n id=" + id +
                "\n customerName='" + customerName + '\'' +
                "\n product=" + product +
                "\n quantity=" + quantity +
                "\n assignedAgent='" + assignedAgent + '\'' +
                "\n city='" + city + '\'' +
                "\n landmark='" + landmark + '\'' +
                "\n houseNumber='" + houseNumber + '\'' +
                "\n pinCode='" + pinCode + '\'' +
                "\n phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
