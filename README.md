# ✈️ Flight Tracker CLI

This is the command-line interface (CLI) for the **Flight Tracker** sprint project. It is written in Java and connects to a REST API backend built with Spring Boot. The CLI allows users to query aviation-related data such as airports, aircraft, and passengers.

---

## 📌 Features

The CLI connects to a hosted backend and allows users to perform the following actions:

1. **View airports in a city**
2. **View aircraft flown by a passenger**
3. **View airports used by an aircraft** 
4. **View airports used by a passenger** 

All data is retrieved via HTTP `GET` requests.

---

## 🧪 Requirements

- Java 17 or newer (Java 23 used in this project)
- Maven 3.6+

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/FlightTrackerCLI.git
cd FlightTrackerCLI
```
### 2. Build the project
   
```bash
mvn compile
```

### 3. Run the CLI
```bash
mvn exec:java -Dexec.mainClass="com.keyin.Main"
```

---

## 🔗 Backend API
This CLI communicates with the hosted backend API at:

```arduino
https://sdat-s4-sprint-backend.onrender.com/
```
Ensure that the backend is online before running the CLI. API endpoints used by this project include:

GET /cities/{id}/airports

GET /passengers/{id}/aircraft

GET /aircraft/{id}/airports 

GET /passengers/{id}/used-airports 

---

## ✅ Testing
Unit tests are written using JUnit 5

Mockito is used for mocking HTTP behavior

GitHub Actions is set up to run tests automatically on pull requests

---

## ⚠️ Known Issues
SLF4J binding warning (StaticLoggerBinder) appears in the console; this is harmless.

---

## 👥 Team Contributions

| Name        | Role                     |
|-------------|--------------------------|
| Cody Collins       | CLI application developer |
| Colin Yetman | Backend developer         |
| Jaowad Hossain | Testing and CI integration |
