package com.bridgelabz.employeepayroll;

public class Employee {
    private String name;
    private int id;
    private String emp_gender;
    private long emp_phone;
    private String emp_address;

    public Employee(int id,String name, String emp_gender, long emp_phone, String emp_address) {
        this.name = name;
        this.id = id;
        this.emp_gender = emp_gender;
        this.emp_phone = emp_phone;
        this.emp_address = emp_address;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public long getEmp_phone() {
        return emp_phone;
    }

    public String getEmp_address() {
        return emp_address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", emp_gender='" + emp_gender + '\'' +
                ", emp_phone=" + emp_phone +
                ", emp_address='" + emp_address + '\'' +
                '}';
    }
}
