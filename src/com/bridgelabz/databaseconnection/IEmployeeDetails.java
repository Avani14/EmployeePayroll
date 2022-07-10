package com.bridgelabz.databaseconnection;

import com.bridgelabz.employeepayroll.Employee;
import com.bridgelabz.employeepayroll.EmployeePayDetails;
import com.bridgelabz.employeepayroll.Employee_Department_Details;

public interface IEmployeeDetails {
    void getEmployeeDetails(int id);
    void addEmployeePersonalDetails(Employee employee);
    void addEmployeePayDetails(EmployeePayDetails employeePayDetails);
    void addEmployeeDepartmentDetails(Employee_Department_Details employee_department_details);
    boolean updateBasePay();
}
