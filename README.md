# Student-Marketplace
A role-based marketplace platform built using Spring-Boot, where students can list products for sale, administrators moderate listings, and the public can browse approved items.

This project demonstrates secure backend architecture, layered authorization, and marketplace lifecycle management.

---

## Features Implemented 

### Authentication and Authorization
- Spring Security based authentication
- Role-based access control
- Two roles:
  - ROLE_SELLER`
  - ROLE_ADMIN`
- Secure login system
- Session-based authentication

---

### Seller Capabilities 
- View new listings
- View own listings
- Edit own listings (Owner enforced at service layer)
- Delete own listings
- Listings reset to `ACTIVE` status after editing (required re-approval)

---

### Admin Capabilities
- View all listings
- Approve listings
- Block listings
- Control marketplace visibility

---

### Public Marketplace 
- No login required
- Displays only `APPROVED` listings
- Read-only view
- Secure data exposure

---

### Security Architecture 
- Owner validation
- Role-based route restrictions
- CSRF configuration for secured routes
- Protection against unauthorized listing edits
- Proper HTTP status handlings

---

### Architecture Overview
Layered Architecture:
- **Controller Layer** -> Handles HTTP requests
- **Service Layer** -> Business Logic + Authorization checks
- **Repository Layer** -> JPA Data Access

---

## Project Structure 

```
studentmarketplace/
├── admin/
│   ├── controller/
│   └── service/
│
├── auth/
│   ├── model/
│   ├── repository/
│   └── service/
│
├── common/
├── config/
├── image/
│
├── listing/
│   ├── controller/
│   ├── model/
│   ├── repository/
│   └── service/
│
├── publicview/
└── seller/
```

---

## Database
- H2 Database
- JPA / Hibernate ORM

---

## Listing Lifecycle
1. Seller creates listing -> Status = `ACTIVE`
2. Admin reviews listing
3. Admin approves listing -> Status = `APPROVED`
4. Public marketplace displays only `APPROVED`
5. If seller edits listing -> Status resets to `ACTIVE`

---

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Thymeleaf
- H2 Database
- Maven

---

## Security Design Highlights
- Authorization logic centralized in the Service layer
- Ownership validated using Seller ID comparison
- Role-based route protection
- Proper HTTP status handling for forbidden access

---

## How to Run
1. Clone this repository
```bash
git clone https://github.com/sonjyoti/student-marketplace
```
2. Run the application 
```bash
mvn spring-boot: run
```
3. Open:
```bash
http://localhost:8080
```

---

## Developed as a backend-focused marketplace system demonstrating secure layered architecture using Spring-Boot.
