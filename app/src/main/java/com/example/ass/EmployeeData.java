package com.example.ass;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    private static List<Employee> employeeList = new ArrayList<>();

    static {
        // Updated sample data for initial testing
        employeeList.add(new Employee("E001", "Manzi Delphin", "Male", "manzi.delphin@example.com", "0780000001", "IT"));
        employeeList.add(new Employee("E002", "Eric Cyiza", "Male", "eric.cyiza@example.com", "0780000002", "Engineering"));
    }

    public static List<Employee> getEmployeeList() {
        return employeeList;
    }

    public static void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
