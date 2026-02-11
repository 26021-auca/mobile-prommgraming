# Employee Management App - Assignment 3

## Overview
This Android application allows for managing employee registrations, viewing a list of registered employees, and seeing detailed information for each employee. It demonstrates the use of Multiple Activities, Explicit and Implicit Intents, ListViews, and Background Services.

## Activities
1. **MainActivity**: The entry point of the app. It provides buttons to navigate to the registration screen and controls for the background service.
2. **EmployeeRegistrationActivity**: Allows users to input employee details (ID, Name, Gender, Email, Phone, Department) and register them.
3. **EmployeeListActivity**: Displays a `ListView` of all registered employees showing their ID and Name.
4. **EmployeeDetailActivity**: Displays full details of a selected employee. It features clickable Email and Phone fields that trigger implicit intents.

## Intents Used
- **Explicit Intents**: Used to navigate between `MainActivity`, `EmployeeRegistrationActivity`, `EmployeeListActivity`, and `EmployeeDetailActivity`. It also passes the `Employee` object (Serializable) to the detail screen.
- **Implicit Intents**:
    - `ACTION_SENDTO`: Used to open the email app with the employee's email address.
    - `ACTION_DIAL`: Used to open the phone dialer with the employee's phone number.

## Data Flow
- Employee data is encapsulated in the `Employee` class.
- A central `EmployeeData` class maintains a static list of `Employee` objects to simulate a data store.
- When an employee is registered in `EmployeeRegistrationActivity`, they are added to the list in `EmployeeData`.
- `EmployeeListActivity` retrieves this list to populate its `ListView`.
- Upon clicking an item in the list, the specific `Employee` object is passed to `EmployeeDetailActivity` via `intent.putExtra()`.

## Background Service
- `MyBackgroundService` is a simple service that plays the system's default ringtone in a loop when started. It continues to run even when navigating between different screens of the application.
