🍽️ HungryMate – Online Food Ordering Web Application

HungryMate is a **full-stack Java web application** that simulates a real-world food ordering and delivery platform similar to Swiggy and Zomato.
The project is built using **Jakarta EE (Servlets & JSP)** with a strong emphasis on **core backend fundamentals**, **session management**, and **JDBC-based database interaction**.

This project showcases how enterprise-grade Java web applications are designed, developed, and managed.

---

## 📌 Features

* 👤 User Registration & Login
* 🍴 Restaurant & Menu Browsing
* 🛒 Cart Management using `HttpSession`
* 📦 Order Placement with JDBC Transactions
* 📜 Order History Tracking
* 🔁 Server-side navigation using `RequestDispatcher`
* 💳 Payment-ready architecture (COD implemented)

---

## 🧠 Core Concepts Demonstrated

* MVC Architecture
* HttpSession lifecycle & scope
* RequestDispatcher (forward vs redirect)
* JDBC CRUD operations
* Transaction management (commit & rollback)
* DAO & DAOImpl pattern
* Clean separation of concerns

---

## 🛠️ Tech Stack

### Backend

* Java (JDK 8+)
* Jakarta EE (Servlets & JSP)
* JDBC
* Apache Tomcat 9+

### Frontend

* HTML5
* CSS3
* JavaScript
* JSP Expression Language (EL)

### Database

* MySQL

### Tools

* Eclipse IDE
* Git & GitHub
* MySQL Workbench
* Postman

---

## 🧩 Application Architecture

```
Browser
  ↓
JSP (View)
  ↓
Servlets (Controller)
  ↓
DAO Layer
  ↓
JDBC
  ↓
MySQL Database
```

✔️ MVC Based
✔️ Scalable & Maintainable
✔️ Industry-aligned Design

---

## 🛒 Cart & Session Management

* Each user has an **independent cart**
* Cart data is stored in `HttpSession`
* Cart persists across multiple requests
* Prevents data collision between users

```java
HttpSession session = request.getSession();
session.setAttribute("cart", cart);
```

---

## 🔄 Role of RequestDispatcher

Used for:

* Server-side request forwarding
* Maintaining request scope
* Clean navigation between JSPs
* Avoiding unnecessary client redirects

```java
RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
rd.forward(request, response);
```

---

## 🗃️ Database Schema (Overview)

### Order Table

| Column        | Description          |
| ------------- | -------------------- |
| order_id      | Primary Key          |
| user_id       | User Reference       |
| restaurant_id | Restaurant Reference |
| total_amount  | Total Bill           |
| order_date    | Timestamp            |
| status        | Order Status         |

### Order_Items Table

| Column        | Description     |
| ------------- | --------------- |
| order_item_id | Primary Key     |
| order_id      | Order Reference |
| menu_id       | Food Item       |
| quantity      | Quantity        |
| price         | Price           |

---

## 📂 Project Structure

```
HungryMate/
├── controller/
│   ├── LoginServlet.java
│   ├── CartServlet.java
│   └── OrderServlet.java
├── dao/
│   ├── UserDAO.java
│   ├── OrderDAO.java
│   └── OrderItemDAO.java
├── daoimpl/
├── model/
├── util/
│   └── DBConnection.java
├── webapp/
│   ├── jsp/
│   ├── css/
│   └── js/
```

---

## ⚠️ Challenges Faced

* Cart data loss across requests
  ✔️ Solved using `HttpSession`

* Partial order insertion
  ✔️ Solved using JDBC transactions

* Navigation issues
  ✔️ Solved using `RequestDispatcher.forward()`

---

## 🚀 Future Enhancements

* JWT Authentication
* Admin Dashboard
* Online Payment Gateway (Razorpay)
* Order Tracking
* Ratings & Reviews
* React Frontend Integration
* Cloud Deployment

