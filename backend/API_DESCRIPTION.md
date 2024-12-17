# API Documentation
## Table of Contents
- [Authentication Endpoints](#authentication-endpoints)
    - [POST /auth/login](#post-authlogin)
    - [POST /auth/register](#post-authregister)
- [Admin Endpoints](#admin-endpoints)
  - [Address Endpoints](#address-endpoints)
    - [POST /admin/create-address](#post-admincreate-address)
  - [University Endpoints](#university-endpoints)
    - [POST /admin/create-university](#post-admincreate-university)
- [Student Endpoints](#student-endpoints)
  - [User Endpoints](#user-endpoints)
    
## Authentication Endpoints

#### `POST /auth/login`
- **Author**: Jossin Antony
- **Description**:This endpoint authenticates users based on their credentials. 
- Upon successful authentication, a JSON Web Token (JWT) is issued, along with timestamps for token creation and expiration.
- **Request Body**:
    - `LoginRequestTO`: The authentication request
- **Throws**:
    - Exception if provided credentials does not match a user in database
- **Response**: Authentication response as `LoginResponseTO`
- **To Do**:
---

#### `POST /auth/register`
- **Author**: Jossin Antony
- **Description**:This endpoint registers users based on their credentials.
- Upon successful authentication, a JSON Web Token (JWT) is issued, along with timestamps for token creation and expiration.
- **Request Body**:
    - `LoginRegisterTO`: The authentication request
- **Throws**:
    - Exception if a user with the same email and role already exists.
    - Same credentials but with another role is allowed.
- **Response**: Authentication response as `LoginResponseTO`
- **To Do**:
---

## Admin Endpoints
### Address Endpoints

#### `POST /admin/create-address`
- **Author**: Jossin Antony
- **Description**:This endpoint creates an address from the `AddressTO` parameter.
- Upon successful operation, a new `AddressTO` is created in the database.
- **Request Body**:
  - `AddressTO`: The address transfer object.
- **Throws**:
  - Exception if provided address already exist in database
- **Response**: Creation response as `AddressTO`
- **To Do**:
---
- **To Do**:

### University Endpoints
#### `POST /admin/create-university`
- **Author**: Jossin Antony
- **Description**:This endpoint creates a university from the `UniversityTO` parameter.
- Upon successful operation, a new `UniversityTO` is created in the database.
- **Request Body**:
  - `UniversityTO`: The university transfer object.
- **Throws**:
  - Exception if provided university already exist in database
- **Response**: Creation response as `UniversityTO`
- **To Do**: Check the one-to-one relationship between university and address; might not be the most ideal one!
---

## Student Endpoints
### User Endpoints
#### 



