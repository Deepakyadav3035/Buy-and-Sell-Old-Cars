# 🚗 Buy and Sell Old Cars

A full-stack Java Spring Boot project for buying and selling used cars. This system enables users to list, search, and book cars, while agents evaluate vehicles and admins manage listings and operations. Secure access is implemented via JWT and role-based authorization.

---

## 🔧 Tech Stack

- **Backend**: Java 8, Spring Boot, Spring MVC, Spring Data JPA, Hibernate
- **Security**: Spring Security, JWT, Role-Based Access Control
- **Database**: MySQL
- **Tools**: Maven, Postman, Lombok
- **Cloud/Deployment**: AWS EC2, S3 (in progress)

---

## ✨ Features

### 👤 User Module
- Register/Login (Customer/Guest)
- Guest Login with OTP and temporary role (`ROLE_GUEST`)
- Search cars by model, brand, location, etc.
- Book car visit or schedule test drive

### 🚘 Car Module
- Add new car listings (Admin/Agent)
- Upload car photos (AWS S3)
- View car details and availability
- Car status management (Available, Booked, Sold)

### 🧑‍💼 Agent Module
- Agent allocation for car inspection
- Agent verification and evaluation reports
- Admin assigns agents dynamically

### 🔐 Security
- JWT-based authentication
- Role-based access control (`ROLE_USER`, `ROLE_ADMIN`, `ROLE_GUEST`)
- Protected endpoints for booking, listing, and admin operations

### 💳 Payment Module *(in progress)*
- Razorpay integration for booking fee
- Auto seat/cancel flow post-payment

---

## 🗂️ Project Structure
com.example.buycars
├── controller
│ ├── UserController.java
│ ├── CarController.java
│ └── AuthController.java
├── entity
│ ├── Car.java
│ ├── User.java
│ └── Agent.java
├── repository
├── service
│ ├── CarService.java
│ ├── JWTService.java
│ └── OTPService.java
├── security
│ ├── JWTFilter.java
│ └── SecurityConfig.java
└── exception

## 🔐 JWT Security

- JWT is generated after login and sent in `Authorization` header.
- All requests pass through `JWTFilter`.
