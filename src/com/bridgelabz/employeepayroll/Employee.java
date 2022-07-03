package com.bridgelabz.employeepayroll;

public class Employee {
    private String name;
    private byte id;
    private long salary;

    public Employee(String name, byte id, long salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                '}';
    }
}
