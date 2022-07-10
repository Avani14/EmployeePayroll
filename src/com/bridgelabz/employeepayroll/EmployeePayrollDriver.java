package com.bridgelabz.employeepayroll;


import com.bridgelabz.databaseconnection.EmployeeDetailsToDatabase;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeePayrollDriver {
    static Scanner sc = new Scanner(System.in);
    static Gson gson = new Gson();
    static File file = new File("EmployeeDetails.json");
    static HashMap<Byte,Employee> employeeHashMap = new HashMap<>();
    static String str;
    static int count = 0;
    public static void writeInFile() throws IOException
    {
        str = gson.toJson(str);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(str);
        count++;
        bufferedWriter.close();
        fileWriter.close();
        System.out.println("Data inserted");
    }
    //Ability to read the file to print the payroll of employees
    public static void readFile()throws IOException{
        FileReader fileReader = new FileReader(file);
        Object obj = gson.fromJson(fileReader,Object.class);
        System.out.println(obj);
    }
    public void addEmployeeDetails()
    {
        System.out.println("Enter employee personal details");
        System.out.println("Enter employee id: ");
        byte id = sc.nextByte();
        System.out.println("Enter employee name: ");
        String name = sc.next();
        System.out.println("Enter employee gender: ");
        String gender = sc.next();
        System.out.println("Enter employee's phone number: ");
        long phone = sc.nextLong();
        System.out.println("Enter employee's address: ");
        String address = sc.next();
        Employee e = new Employee(id,name,gender,phone,address);
        EmployeeDetailsToDatabase employeeDetailsToDatabase = new EmployeeDetailsToDatabase();
        employeeDetailsToDatabase.addEmployeePersonalDetails(e);
        System.out.println("---------------");
        System.out.println("Enter employee pay details");
        System.out.println("Enter employee's Basic_Pay: ");
        int Basic_Pay = sc.nextInt();
        System.out.println("Enter employee's Deductions: ");
        int Deductions = sc.nextInt();
        System.out.println("Enter employee's Taxable_Pay: ");
        int Taxable_Pay = sc.nextInt();
        System.out.println("Enter employee's Income_Tax: ");
        int Income_Tax = sc.nextInt();
        System.out.println("Enter employee's Net_Pay: ");
        int Net_Pay = sc.nextInt();
        EmployeePayDetails employeePayDetails = new EmployeePayDetails(id, Basic_Pay, Deductions, Taxable_Pay, Income_Tax, Net_Pay);
        employeeDetailsToDatabase.addEmployeePayDetails(employeePayDetails);
        System.out.println("---------------");str = e.toString().concat("\n");
        System.out.println("Enter employee's department details");
        System.out.println("Enter employee's department");
        String emp_department = sc.next();
        System.out.println("Enter employee's salary");
        int salary = sc.nextInt();
        System.out.println("Enter employee's start date");
        String date = sc.next();
        Employee_Department_Details employee_department_details = new Employee_Department_Details(id,salary,emp_department,date);
        employeeDetailsToDatabase.addEmployeeDepartmentDetails(employee_department_details);
        employeeHashMap.put(id,e);
        try {
            writeInFile();
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
    public static void readEmployeeDetails() {
            try {
                readFile();
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
    }
    //Counting the number of entries
    public static long countEntries(){
        long count = 0;
        try {
            count = Files.lines(file.toPath()).count();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll system.");
        EmployeePayrollDriver employeePayrollDriver = new EmployeePayrollDriver();
        EmployeeDetailsToDatabase employeeDetailsToDatabase = new EmployeeDetailsToDatabase();
        byte option;
        while(true) {
            System.out.println("\n Enter 1 to add details \n Enter 2 to display details \n Enter 3 to get the number of employees \n Enter 4 to get " +
                    "employee details how have joined in particular time \n Enter 5 to exit");
            option = sc.nextByte();
            switch (option) {
                case 1: employeePayrollDriver.addEmployeeDetails();
                break;
                case 2 :
                    System.out.println("Enter the employee's id to get the details");
                    int id = sc.nextInt();
                    employeeDetailsToDatabase.getEmployeeDetails(id);
                break;
                case 3:
                    //Counting the number of entries.
                    System.out.println("Number of employees are "+EmployeePayrollDriver.countEntries());
                    break;
                case 4:
                    System.out.println("Enter start date");
                    String start_date = sc.next();
                    System.out.println("Enter end date");
                    String end_date = sc.next();
                    employeeDetailsToDatabase.getEmployeeDetailsInRange(start_date,end_date);
                    break;
                case 5 :
                    System.out.println("Thank You!");
                    return;
            }
        }
    }
}
