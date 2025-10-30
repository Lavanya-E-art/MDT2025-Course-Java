# 🧠💻 **Self-Assessment Task 3 — Inheritance**

## 🏭✨ **Project Title: Smart Industrial Process Management System**

## 📘 **Overview**

This project represents a **modular**, **object-oriented system** for managing and simulating **industrial** and **management processes**.  
It models relationships between **Processes**, **Resources**, and **Operations**, providing a structured way to simulate how various resource types — 🧑‍🏭 *Human*, 🧰 *Hardware*, 💾 *Software*, and 🧱 *Material* — interact in both management and industrial contexts.  

It demonstrates key **Object-Oriented Programming (OOP)** concepts such as  
🔹 **Inheritance** · 🔹 **Abstraction** · 🔹 **Encapsulation** · 🔹 **Polymorphism**  
through the design of a **Warehouse Simulation System**.

---

## 🎯 **Objectives**

1. **Read** the recommended literature on inheritance and class hierarchies.  
2. **Modify** the classes from Task 2 based on the provided UML diagrams.  
3. **Develop** a program that can simulate the work of warehouses.  
4. **Calculate** the total amount and cost of various **resources**, **operations**, and **processes**.  
5. **Upload** the final implementation and UML class diagram to GitHub as part of the submission.

---

## 🧩 **System Description**

The Warehouse Simulation System represents a digital model of how real-world warehouses operate.  
It consists of multiple **processes**, each requiring different **operations** and **resources**.

### Key Features:
- **Inheritance structure** connects abstract classes and concrete subclasses.  
- **Processes** can be management-related or industrial.  
- **Resources** include both human and non-human types.  
- **Operations** vary between transport and human-based actions.  
- **Cost and time calculations** are performed for each operation and process.  

---

## 🏗️ **Class Hierarchy (UML Overview)**

The system is built around three main hierarchies:

### 🧱 **1. Process Hierarchy**
- `Process` *(abstract)*  
  - `ManagementProcess`  
  - `IndustrialProcess`  
    - Includes methods like `ProcessDuration()` and `ProcessResources()`  

### ⚙️ **2. Resource Hierarchy**
- `Resources` *(abstract)*  
  - `HumanResources`  
  - `NonHumanResources`  
    - `HardwareResources`  
    - `MaterialResources`  
    - `SoftwareResources`  
    - `AGV`  

### 🔄 **3. Operation Hierarchy**
- `IOperation` *(interface / abstract)*  
  - `TransportOperation`  
  - `HumanOperation`

## 🧮 **Simulation Workflow**

1. **Define resources** — e.g., workers, AGVs, materials, hardware, software.  
2. **Assign operations** to each process type (management or industrial).  
3. **Simulate processes** — each process consumes resources and executes operations.  
4. **Calculate:**
   - Total number of resources used  
   - Cost per operation and per process  
   - Total resource and operation costs  
