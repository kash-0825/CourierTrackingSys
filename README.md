# Courier and Parcel Tracking System

A console-based Courier and Parcel Tracking System developed in Java for Team N.

## Features

- **Parcel Booking:** Book parcels with sender details, receiver details, and parcel weight.
- **Agent Registration:** Register delivery agents with unique IDs and contact information.
- **Agent Assignment:** Assign available delivery agents to parcels using a FIFO queue.
- **Status Tracking:** Update parcel status through a predefined delivery lifecycle.
- **Tracking History:** View parcel status updates along with their timestamps.
- **Parcel Cancellation:** Cancel parcels that are still in the Booked state.
- **Parcel and Agent Listing:** View all registered parcels and delivery agents.
- **Parcel Search:** Search parcels by sender or receiver name.
- **Input Validation:** Handle invalid inputs, duplicate assignments, unavailable agents, and invalid status transitions.

## Technologies and Concepts Used

- Java (JDK 25)
- Object-Oriented Programming
- Inheritance and Abstraction
- HashMap
- ArrayList
- Custom Linked List-based Queue
- Exception Handling
- LocalDateTime

## Project Structure

```text
CourierTrackingSys/
│
├── src/
│   └── main/
│       └── java/
│           ├── app/
│           │   └── Main.java
│           ├── datastructures/
│           │   └── AgentQueue.java
│           ├── exceptions/
│           ├── model/
│           ├── service/
│           └── util/
│
├── out/                 # Compiled class files
└── README.md
```

## Prerequisites

- Java Development Kit (JDK) 25
- Windows PowerShell or a compatible terminal

Verify your Java installation:

```powershell
java -version
javac -version
```

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/kash-0825/CourierTrackingSys.git
```

### 2. Navigate to the project directory

```powershell
cd CourierTrackingSys
```

### 3. Compile the source files

Run the following command in PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object FullName)
```

### 4. Run the application

```powershell
java -cp out app.Main
```

## Application Menu

Once the application starts, the following menu is displayed:

```text
===== COURIER AND PARCEL TRACKING SYSTEM =====
1. Book a parcel
2. Register a delivery agent
3. Assign an agent to a parcel
4. Update parcel status
5. Track a parcel
6. View all parcels
7. Cancel a parcel
8. View all agents
9. Search parcels by sender or receiver
0. Exit
```

Enter the corresponding menu number to perform an operation.

## Parcel Status Lifecycle

Parcels follow this predefined status progression:

```text
Booked
   |
   v
Picked Up
   |
   v
In Transit
   |
   v
Out for Delivery
   |
   v
Delivered
```

Invalid status transitions are rejected. An agent must be assigned before a parcel can be picked up.

## Data Storage

The application currently uses in-memory data structures for storing parcels, agents, and tracking history.

**Note:** All application data is temporary and is reset when the application exits. Data persistence across application restarts is not currently implemented.

## Team

Team N