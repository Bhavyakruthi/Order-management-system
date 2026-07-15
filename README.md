# 📦 Order Management System

### A Role-Based Java Console Application for End-to-End Order, Product & Delivery Management

[![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![OOP](https://img.shields.io/badge/Paradigm-Object--Oriented-blue)](#architecture)
[![NetBeans](https://img.shields.io/badge/IDE-NetBeans-1B6AC6?logo=apache-netbeans-ide&logoColor=white)](https://netbeans.apache.org/)
[![Ant](https://img.shields.io/badge/Build-Apache%20Ant-A81C7D?logo=apacheant&logoColor=white)](https://ant.apache.org/)
[![Status](https://img.shields.io/badge/Status-Completed-brightgreen)](#)

---

## 📋 Table of Contents

1. [Overview](#-overview)
2. [Key Highlights](#-key-highlights)
3. [Architecture](#-architecture)
4. [Role-Based Feature Breakdown](#-role-based-feature-breakdown)
5. [Tech Stack](#-tech-stack)
6. [Getting Started](#-getting-started)
7. [Usage Guide](#-usage-guide)
8. [Project Structure](#-project-structure)
9. [Design Highlights](#-design-highlights)
10. [Troubleshooting](#-troubleshooting)
11. [Future Enhancements](#-future-enhancements)
12. [Team](#-team)

---

## 🎯 Overview

**Order Management System (OMS)** is a standalone, in-memory Java console application built to model a real-world retail order pipeline — from product discovery to order placement, delivery tracking, and post-purchase feedback.

Rather than a single-user CRUD demo, the system is built around **four distinct user roles**, each with its own permissions and workflows, mirroring how an actual e-commerce backend separates customer, staff, and management concerns. It was developed as a semester project for **22AIE111 – Object Oriented Programming in Java** at Amrita School of Artificial Intelligence, and demonstrates applied OOP principles — abstraction, inheritance, and encapsulation — over a from-scratch design rather than a framework-generated one.

---

## ✨ Key Highlights

- 🔐 **Four-role access control** — Regular User, Admin, Manager, and Delivery Agent, each with a dedicated menu and permission boundary
- 🛒 **Full order lifecycle** — browse → place order → track → cancel → feedback
- 🚚 **Delivery coordination** — managers assign delivery agents; agents update live status and location
- 💰 **Dynamic pricing engine** — admins can adjust product prices using a purchase-count-driven algorithm, rather than static pricing
- 🗣️ **Ownership-verified feedback system** — feedback can only be submitted by the verified owner of an order
- 🧱 **Clean OOP architecture** — an abstract `User` base class with role-specific subclasses, keeping shared logic centralized and role logic isolated
- ⚙️ **Zero external dependencies** — no database, no framework; pure Java SE, ideal for demonstrating core language and design fundamentals

---

## 🏗 Architecture

The system follows a **role-based object-oriented design**, all under a single package `p`:

```text
                     ┌────────────────────────────┐
                     │   OrderManagementSystem     │  ← Main entry point & business logic hub
                     └──────────────┬───────────────┘
                                    │
                     ┌──────────────┴───────────────┐
                     │    UserManagementSystem       │  ← Registration, login, role dispatch
                     └──────────────┬───────────────┘
                                    │
        ┌───────────────┬──────────┼──────────┬───────────────┐
        ▼               ▼          ▼          ▼               ▼
┌───────────────┐ ┌───────────┐ ┌────────┐ ┌────────────────┐
│  RegularUser   │ │   Admin    │ │Manager │ │ DeliveryAgent   │
└───────┬───────┘ └─────┬─────┘ └───┬────┘ └────────┬────────┘
        │                │            │              │
        └────────────────┴─────┬──────┴──────────────┘
                                ▼
                        ┌───────────────┐
                        │   User (abstract) │
                        └───────────────┘

                Domain objects: Order · Product
```

All four role classes extend the abstract `User` class, inheriting shared identity/session logic while implementing their own role-specific operations — a textbook example of polymorphism driving a permissions model instead of if/else role checks.

---

## 🔑 Role-Based Feature Breakdown

### 👤 Regular User (Customer)
| Feature | Description |
|---|---|
| View Products | Browse the full catalog before ordering |
| Place Order | Order by product ID + quantity, with validation |
| View All Orders | Full order history at a glance |
| Track Order Status | Live status + current location + ETA, scoped to the requesting user |
| Cancel Order | Ownership-verified cancellation with confirmation |
| Update Account Info | Edit address, contact, and personal details |
| Submit Feedback | Feedback tied to a verified order + username match |

### 🛡️ Admin
| Feature | Description |
|---|---|
| View Customers | Full customer profiles, order history, contact info |
| View / Add / Remove Staff | Manage Managers and Delivery Agents |
| View Feedback | Review all customer feedback for quality insights |
| Adjust Product Pricing | Dynamic price adjustment driven by product purchase-count analytics |

### 📊 Manager
| Feature | Description |
|---|---|
| Manage Products | Add, update, delete, and view catalog items |
| Assign Deliveries | Match orders to available delivery agents |
| View Assigned Deliveries | Track fulfillment progress |
| View Feedback | Monitor customer satisfaction trends |

### 🚚 Delivery Agent
| Feature | Description |
|---|---|
| Receive Orders | View all orders assigned for delivery |
| Track Orders | Review status/history of active and past deliveries |
| Update Order Status | Update live location, ETA, and delivery state |
| View Cancelled Orders | Stay informed on cancellations affecting their queue |

---

## 🧰 Tech Stack

| Layer | Technology | Purpose |
|---|---|---|
| Language | Java SE 21 | Core application logic |
| Build (Option 1) | Apache Ant | Command-line build & run |
| Build (Option 2) | `javac`/`java` CLI | Manual compilation |
| IDE | NetBeans (Ant-based project) | Full project support out of the box |
| Data Layer | In-memory Java objects | No external DB — state lives for the runtime of the session |

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 21 (or compatible) installed and on your `PATH`
- *(Optional)* NetBeans IDE for one-click project support
- *(Optional)* Apache Ant if building via command line

### Option 1 — NetBeans
1. Open NetBeans → **File → Open Project**
2. Select the extracted `p` folder
3. Build and run directly from the IDE

### Option 2 — Command Line
```bash
# From inside the extracted "p" folder

# 1. Create build directory
mkdir build\classes          # Windows PowerShell

# 2. Compile
javac --release 21 -d build\classes src\p\*.java

# 3. Run
java -cp build\classes p.OrderManagementSystem
```

### Option 3 — Apache Ant
```bash
ant clean compile
ant run
```

---

## 📖 Usage Guide

On launch, the application presents:

```
1. Sign Up
2. Log In
3. View Products
4. Exit
```

After logging in, the menu adapts automatically based on the account's role — Regular User, Admin, Manager, or Delivery Agent — surfacing only the actions that role is permitted to perform.

**Typical flow:**
```
Sign Up → Log In → Role-specific dashboard
                        ├─ User: browse → order → track → cancel/feedback
                        ├─ Manager: manage catalog → assign deliveries
                        ├─ Delivery Agent: receive → update status
                        └─ Admin: manage staff → review feedback → adjust pricing
```

---

## 📁 Project Structure

```
p/
├── OrderManagementSystem.java   # Main class & core business logic
├── UserManagementSystem.java    # Registration, login, role routing
├── User.java                    # Abstract base class
├── RegularUser.java             # Customer role
├── Admin.java                   # Administrative operations
├── Manager.java                 # Product & delivery management
├── DeliveryAgent.java           # Delivery tracking & updates
├── Order.java                   # Order domain object
└── Product.java                 # Product catalog item
```

---

## 💡 Design Highlights

- **Abstraction** — `User` defines the shared contract every role must fulfill, without dictating implementation
- **Inheritance** — `RegularUser`, `Admin`, `Manager`, and `DeliveryAgent` all extend `User`, reusing session/identity logic instead of duplicating it
- **Encapsulation** — `Order` and `Product` expose controlled access to their internal state via getters/setters, keeping business rules centralized
- **Validation-first operations** — logins, order lookups, cancellations, and feedback all verify ownership/credentials before acting, rather than trusting input blindly

---

## 🛠 Troubleshooting

| Issue | Fix |
|---|---|
| `javac` not recognized | Ensure JDK is installed and `JAVA_HOME` is set correctly |
| `java.lang.NoClassDefFoundError` | Confirm classpath includes `build\classes` and the package path is correct |
| Project won't open in NetBeans | Open the `p` directory directly and confirm the `nbproject` folder is present |
| Menu input skipped/misbehaving | Enter valid numeric choices and press Enter after each input |

---

## 🚧 Future Enhancements

- 📧 Email notifications for order and delivery updates
- 🔄 Real-time updates via WebSockets
- ✅ Automated test coverage (unit + integration)
- 👤 Dedicated job seeker-style profile/settings page for users
- 💳 Payment gateway integration
- 📊 Advanced analytics and automated inventory management

---

## 📩 Contact

For questions, suggestions, or collaboration, feel free to open an issue on this repository.
