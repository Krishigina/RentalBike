# Rental Bike Management System Desktop app
## Overview
This project is a client-server desktop application designed to support business processes for a bicycle rental company. It allows efficient management of customer data, bicycle inventory, bookings, and rentals. The application is built using Java with JavaFX for the client-side and JDBC for database interactions.
## Features
- **User Roles:** Administrator, Manager, and Client with distinct permissions.
- **User Authentication:** Secure login and registration with password hashing using BCrypt.
- **Bicycle Management:** Manage bicycle models, availability, and rental status.
- **Booking System:** Create, view, and manage bookings and rentals.
- **Database Integration:** MySQL database for persistent data storage.
- **User Interface:** Graphical user interface using JavaFX.
## Technologies Used
- **Programming Language:** Java 
- **GUI Framework:** JavaFX 
- **Database:** JDBC, MySQL 
- **Architecture:** MVC
- **Version Control:** Git
- **Зassword hashing:** BCrypt
## Architecture
The application follows the MVC (Model-View-Controller) architectural pattern.
## Database Model
<details>
  <summary>Show an infological (conceptual) database model</summary>
  
  ![Инфологическая (концептуальная) модель БД](https://github.com/user-attachments/assets/527b740b-5228-419f-b02e-189949b08f52)
  </details>
<details>
  <summary>Show the database's datalog model</summary>

![Даталогическая модель БД](https://github.com/user-attachments/assets/0066ff91-9e7a-4b2f-8d7c-1bbea1aae559)
</details>
<details>
<summary>Show the physical database model</summary>
  
  ![Физическая модель БД](https://github.com/user-attachments/assets/c0f0f2fc-9437-40c6-a108-412d22c4e220)
</details>

## SQL-script to create database
Below is the SQL script used to create the database schema for the application.

<details>
<summary>Show SQL Script</summary>

```sql
  CREATE TABLE IF NOT EXISTS role( 
id INT PRIMARY KEY AUTO_INCREMENT, 
name VARCHAR(50) NOT NULL 
); 
  CREATE TABLE IF NOT EXISTS users( 
id INT PRIMARY KEY AUTO_INCREMENT, 
role_id INT NOT NULL, 
login VARCHAR(100) NOT NULL, 
password VARCHAR(128), 
FOREIGN KEY(role_id) REFERENCES role(id) 
); 
  CREATE TABLE IF NOT EXISTS clients ( 
id INT AUTO_INCREMENT PRIMARY KEY, 
last_name VARCHAR(100) NOT NULL, 
first_name VARCHAR(100) NOT NULL, 
second_name VARCHAR(100) NOT NULL, 
passport VARCHAR(10) NOT NULL, 
address VARCHAR(200) NOT NULL, 
user_id INTEGER NOT NULL, 
FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE 
); 
  CREATE TABLE IF NOT EXISTS bike_models( 
id INT AUTO_INCREMENT PRIMARY KEY, 
name VARCHAR(100) UNIQUE NOT NULL, 
type ENUM('городской', 'дорожный', 'горный') NOT NULL, 
gear_count INTEGER UNSIGNED NOT NULL 
); 
  CREATE TABLE IF NOT EXISTS bikes (
id INT AUTO_INCREMENT PRIMARY KEY, 
model_id INTEGER NOT NULL, 
FOREIGN KEY(model_id) REFERENCES bike_models(id) ON DELETE CASCADE 
); 
  CREATE TABLE IF NOT EXISTS stores ( 
id INT AUTO_INCREMENT PRIMARY KEY, 
name VARCHAR(100) UNIQUE NOT NULL, 
address VARCHAR(200) NOT NULL 
); 
  CREATE TABLE IF NOT EXISTS bookings( 
id INT AUTO_INCREMENT PRIMARY KEY, 
client_id INTEGER NOT NULL, 
bike_id INTEGER NOT NULL, 
store_id INTEGER NOT NULL, 
pickup_date DATE NOT NULL, 
FOREIGN KEY(client_id) REFERENCES clients(id), 
FOREIGN KEY(bike_id) REFERENCES bikes(id), 
FOREIGN KEY(store_id) REFERENCES stores(id) 
); 
  CREATE TABLE IF NOT EXISTS rentals ( 
id INT AUTO_INCREMENT PRIMARY KEY, 
booking_id INTEGER NOT NULL, 
return_date DATE NOT NULL, 
FOREIGN KEY(booking_id) REFERENCES bookings(id) 
);

```
</details>

## Getting started
1. **Clone/Download the Repository**  
```bash
   git clone https://github.com/Krishigina/RentalBike.git
```
2. **Set Up MySQL Database**
- Download and install MySQL if you haven't already.
- Create a new database and import the schema provided above 
3. **Configure Database Connection** <br />
Enter your database connection details (URL, username, and password) in the `Models/DBConnection.java` file.
4. **Build and Run the Application** <br />
Open the project in your IDE, then build and run the application.
 <br />For example, in IntelliJ IDEA, click on Run > Run 'Application'.
5. **Access the Application** <br />
Once the application is running create a new user from the registration screen, then login.

## Screenshots
![Снимок экрана 2025-01-18 232152](https://github.com/user-attachments/assets/9e3ad6ed-9a4c-47ef-8ff3-79fe03381d9f)
![Снимок экрана 2025-01-18 232116](https://github.com/user-attachments/assets/4297260d-51ab-4789-86d8-2e6cf0f2046b)
![Снимок экрана 2025-01-18 232134](https://github.com/user-attachments/assets/a6f3cd5a-4855-400d-8176-ccf67da54ca3)
![Снимок экрана 2025-01-18 232207](https://github.com/user-attachments/assets/5af109d7-af79-4bc1-80b8-f83d1e4a67a8)
![Снимок экрана 2025-01-18 232220](https://github.com/user-attachments/assets/f199d388-4a7c-4e1b-8480-e7397c5bf487)
![Снимок экрана 2025-01-18 232243](https://github.com/user-attachments/assets/e7551260-978e-4832-bb1b-a344dd32001d)


## Documentation
[Пояснительная записка.pdf](https://github.com/user-attachments/files/18465954/221-362.pdf)

