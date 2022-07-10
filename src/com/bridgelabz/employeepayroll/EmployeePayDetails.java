package com.bridgelabz.employeepayroll;

public class EmployeePayDetails {
    private int emp_id;
    private int Basic_Pay;
    private int Deductions;
    private int Taxable_Pay;
    private int Income_Tax;
    private int Net_Pay;

    public EmployeePayDetails(int emp_id, int basic_Pay, int deductions, int taxable_Pay, int income_Tax, int net_Pay) {
        this.emp_id = emp_id;
        Basic_Pay = basic_Pay;
        Deductions = deductions;
        Taxable_Pay = taxable_Pay;
        Income_Tax = income_Tax;
        Net_Pay = net_Pay;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public int getBasic_Pay() {
        return Basic_Pay;
    }

    public int getDeductions() {
        return Deductions;
    }

    public int getTaxable_Pay() {
        return Taxable_Pay;
    }

    public int getIncome_Tax() {
        return Income_Tax;
    }

    public int getNet_Pay() {
        return Net_Pay;
    }

    @Override
    public String toString() {
        return "EmployeePayDetails{" +
                "emp_id=" + emp_id +
                ", Basic_Pay=" + Basic_Pay +
                ", Deductions=" + Deductions +
                ", Taxable_Pay=" + Taxable_Pay +
                ", Income_Tax=" + Income_Tax +
                ", Net_Pay=" + Net_Pay +
                '}';
    }
}
