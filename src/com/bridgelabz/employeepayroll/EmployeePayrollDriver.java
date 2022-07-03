package com.bridgelabz.employeepayroll;


import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeePayrollDriver {
    static Scanner sc = new Scanner(System.in);
    static Gson gson = new Gson();
    static File file = new File("EmployeeDetails.json");
    static HashMap<Byte,Employee> employeeHashMap = new HashMap<>();
    static int count = 0;
    public static void writeInFile() throws IOException
    {
        String str = gson.toJson(employeeHashMap);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(str);
        count++;
        bufferedWriter.close();
        fileWriter.close();
        System.out.println("Data inserted");
    }
    public static void readFile()throws IOException{
        FileReader fileReader = new FileReader(file);
        Object obj = gson.fromJson(fileReader,Object.class);
        System.out.println(obj);
    }
    public static void addEmployeeDetails()
    {
        System.out.println("Enter employee id: ");
        byte id = sc.nextByte();
        System.out.println("Enter employee name: ");
        String name = sc.next();
        System.out.println("Enter employee's salary: ");
        long salary = sc.nextLong();
        Employee e = new Employee(name,id,salary);
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
        byte option;
        while(true) {
            System.out.println("\n Enter 1 to add details \n Enter 2 to display details \n Enter 3 to get the number of employees \n Enter 4 to EXIT");
            option = sc.nextByte();
            switch (option) {
                case 1: EmployeePayrollDriver.addEmployeeDetails();
                break;
                case 2 : EmployeePayrollDriver.readEmployeeDetails();
                break;
                case 3:
                    System.out.println("Number of employees are "+count);
                    break;
                case 4 :
                    System.out.println("Thank You!");
                    return;
            }
        }
    }
}
