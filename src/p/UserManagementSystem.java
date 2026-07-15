package p;

import java.util.*;

//user management system contains the lists of users using the software. it contains methods to add new customer ( User) and admins/managers/delivery agent , getting userlist , 
//getting the user by username , removing user by username , getting teh list of managers and delivery agents.

class UserManagementSystem {
    private List<User> users = new ArrayList<>();
    
    // Sign up a new user
    public boolean signUpAsUser(String username, String password, String city, String landmark, String houseNumber, String pinCode, String phoneNumber) {
        if (getUserByUsername(username).isPresent()) {
            return false;
        }
        users.add(new RegularUser(username, password, city, landmark, houseNumber, pinCode, phoneNumber));
        return true;
    }
    
    // Sign up a new admin/ manager/ delivery agent
    public boolean signUp(String role, String username,String password,  String name, String workExperience, String preferredWorkTimings) {
        if (getUserByUsername(username).isPresent()) {
            return false;
        }
        switch (role.toLowerCase()) {
            case "admin":
                users.add(new Admin(username, password, name, workExperience, preferredWorkTimings ));
                break;
            case "deliveryagent":
                users.add(new DeliveryAgent(username, password, name, workExperience, preferredWorkTimings));
                break;
            case "manager":
                users.add(new Manager(username, password, name, workExperience, preferredWorkTimings ));
                break;
            default:
                return false;
        }
        return true;
    }

    // Log in a user
    public Optional<User> login(String username, String password) {
        Optional<User> user = getUserByUsername(username);
        return user.filter(value -> value.getPassword().equals(password));
    }

    public List<User> getUsers() {
        return new ArrayList<>(users); // Return a copy of the users list
    }

    // Get user by username
    public Optional<User> getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
    
    // remove user by username
    public void removeUserByUsername(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }
    
    //list of managers
    public List<Manager> getManagers() {
        List<Manager> managers = new ArrayList<>();
        for (User user : users) {
        if (user instanceof Manager) {
            managers.add((Manager) user);
        }
    }
    return managers;
    }
    
    //list of delivery agents
    public List<DeliveryAgent> getDeliveryAgents() {
        List<DeliveryAgent> deliveryAgents = new ArrayList<>();
        for (User user : users) {
        if (user instanceof DeliveryAgent) {
            deliveryAgents.add((DeliveryAgent) user);
        }
    }
        return deliveryAgents;
    }
}

