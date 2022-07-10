package com.bridgelabz.databaseconnection;

import com.bridgelabz.employeepayroll.Employee;
import com.bridgelabz.employeepayroll.EmployeePayDetails;
import com.bridgelabz.employeepayroll.Employee_Department_Details;

import java.sql.*;

public class EmployeeDetailsToDatabase implements IEmployeeDetails{
    Statement statement;
    DBConection dbConection = new DBConection();
    Connection connection = dbConection.getConnection();
    ResultSet resultSet;
    //Ability to retrieve data
    @Override
    public void getEmployeeDetails(int id) {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String retrieveEmpQuery = "select * from employee_details where emp_id = "+id+";";
        String retrievePayQuery = "select * from employee_pay where emp_id = "+id+";";
        String retrieveDeptQuery = "select * from employee_department_details where emp_id = "+id+";";
        try {
            resultSet = statement.executeQuery(retrieveEmpQuery);
            while(resultSet.next()){
                System.out.println("----------------------");
                System.out.println("Employee ID: "+resultSet.getInt("emp_id"));
                System.out.println("Employee Name: "+resultSet.getString("name"));
                System.out.println("Employee Gender: "+resultSet.getString("emp_gender"));
                System.out.println("Employee Phone: "+resultSet.getLong("emp_phone"));
                System.out.println("Employee Address: "+resultSet.getString("emp_address"));
            }
            resultSet = statement.executeQuery(retrievePayQuery);
            while(resultSet.next()){
                System.out.println("----------------------");
                System.out.println("Employee Basic_Pay: "+resultSet.getInt("Basic_Pay"));
                System.out.println("Employee Deductions: "+resultSet.getInt("Deductions"));
                System.out.println("Employee Taxable_Pay: "+resultSet.getInt("Taxable_Pay"));
                System.out.println("Employee Income_Tax: "+resultSet.getInt("Income_Tax"));
                System.out.println("Employee Net_Pay: "+resultSet.getInt("Net_Pay"));
            }
            resultSet = statement.executeQuery(retrieveDeptQuery);
            while(resultSet.next()){
                System.out.println("----------------------");
                System.out.println("Employee Salary: "+resultSet.getInt("emp_salar"));
                System.out.println("Employee Department: "+resultSet.getString("emp_department"));
                System.out.println("Employee Start date: "+resultSet.getDate("emp_start_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    //Added the employee personal details.
    @Override
    public void addEmployeePersonalDetails(Employee employee) {
        String addQuery = "insert into employee_details(name,emp_gender,emp_phone,emp_address) values(?,?,?,?);";
        try {
         PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
         preparedStatement.setString(1, employee.getName());
         preparedStatement.setString(2,employee.getEmp_gender());
         preparedStatement.setLong(3,employee.getEmp_phone());
         preparedStatement.setString(4,employee.getEmp_address());
         preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Employee details added Successfully!");
    }
    //Added the employee pay details.
    @Override
    public void addEmployeePayDetails(EmployeePayDetails employeePayDetails) {
        String addQuery = "insert into employee_pay(Basic_Pay,Deductions,Taxable_Pay,Income_Tax,Net_Pay) values(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            preparedStatement.setInt(1,employeePayDetails.getBasic_Pay());
            preparedStatement.setInt(2,employeePayDetails.getDeductions());
            preparedStatement.setInt(3,employeePayDetails.getTaxable_Pay());
            preparedStatement.setInt(4,employeePayDetails.getIncome_Tax());
            preparedStatement.setInt(5,employeePayDetails.getNet_Pay());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Employee's pay details added Successfully!");
    }
    //Added the employee department details.
    @Override
    public void addEmployeeDepartmentDetails(Employee_Department_Details employee_department_details) {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String addQuery = "insert into employee_department_details(emp_salar,emp_department,emp_start_date) values(?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            preparedStatement.setInt(1,employee_department_details.getEmp_salary());
            preparedStatement.setString(2,employee_department_details.getEmp_department());
            preparedStatement.setString(3,employee_department_details.getStart_date());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Employee details added Successfully!");
    }

    @Override
    public boolean updateBasePay() {
        String updateQuery = "update employee_pay set Basic_Pay = 3000000 where emp_id in (Select emp_id from employee_details where name ='Terisa');";
        try {
            statement = connection.createStatement();
            statement.execute(updateQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void getEmployeeDetailsInRange(String start_date, String end_date) {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String retrieveEmpQuery = "Select * from employee_details emp_details inner join employee_department_details edd where emp_details.emp_id = edd.emp_id and edd.emp_start_date between cast('"+start_date+"' as date) and cast('"+end_date+"' as date);";
      try {
            resultSet = statement.executeQuery(retrieveEmpQuery);
            System.out.println("Size of resultSet is : " + resultSet.getFetchSize());
            while (resultSet.next()) {
                System.out.println("----------------------");
                System.out.println("Employee ID: " + resultSet.getInt("emp_id"));
                System.out.println("Employee Name: " + resultSet.getString("name"));
                System.out.println("Employee Gender: " + resultSet.getString("emp_gender"));
                System.out.println("Employee Phone: " + resultSet.getLong("emp_phone"));
                System.out.println("Employee Address: " + resultSet.getString("emp_address"));
                System.out.println("Employee ID: " + resultSet.getInt("emp_id"));
                System.out.println("Employee Salary: "+resultSet.getInt("emp_salar"));
                System.out.println("Employee Department: "+resultSet.getString("emp_department"));
                System.out.println("Employee Start date: "+resultSet.getDate("emp_start_date"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean getMinMaxSumAvg(String gender) {
        String sumQueryFemale = "SELECT SUM(emp_salar) from employee_department_details where emp_id in(Select emp_id from employee_details where  emp_gender = '"+gender+"' group by emp_gender);";
        String avgQueryFemale = "SELECT AVG(emp_salar) from employee_department_details where emp_id in(Select emp_id from employee_details where  emp_gender = '"+gender+"' group by emp_gender);";
        String minQueryFemale = "SELECT MIN(emp_salar) from employee_department_details where emp_id in(Select emp_id from employee_details where  emp_gender = '"+gender+"' group by emp_gender);";
        String maxQueryFemale = "SELECT MAX(emp_salar) from employee_department_details where emp_id in(Select emp_id from employee_details where  emp_gender = '"+gender+"' group by emp_gender);";
        String countQueryFemale = "SELECT COUNT(emp_salar) from employee_department_details where emp_id in(Select emp_id from employee_details where  emp_gender = '"+gender+"' group by emp_gender);";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sumQueryFemale);
            while(resultSet.next()) {
                System.out.println("Sum is: " + resultSet.getInt("SUM(emp_salar)"));
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
