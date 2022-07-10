package com.bridgelabz.employeepayrolltest;

import com.bridgelabz.databaseconnection.EmployeeDetailsToDatabase;
import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class EmployeePayrollTest {
    @Test
    public void checkFileExists()
    {
        File file = new File("EmployeeDetails.json");
        Assertions.assertTrue(file.exists());
    }
    @Test
    public void deleteThenCheckIfExists(){
        File file =  new File("TestFile.txt");
        file.delete();
        Assertions.assertTrue(!file.exists());
    }
    @Test
    public void createADirectory() {
        File file =  new File("TestFolder");
        file.mkdir();
        Assertions.assertTrue(file.exists());
    }
    @Test
    public void listAllFiles() {
        String dir = "F:\\Study\\BridgeLabz Java\\EmployeePayroll";
        List<Path> pathList = new ArrayList<>();
        try {
            pathList = Files.list(Paths.get(dir))
                    .collect(Collectors.toList());
            System.out.println(pathList);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void watchService() {
        String dir = "F:\\Study\\BridgeLabz Java\\EmployeePayroll";
        try {
             Files.list(Paths.get(dir))
                    .filter(Files::isRegularFile)
                     .forEach(n-> System.out.println(n));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void checkUpdateOfSalary(){
        EmployeeDetailsToDatabase employeeDetailsToDatabase = new EmployeeDetailsToDatabase();
        boolean expected = employeeDetailsToDatabase.updateBasePay();
        Assertions.assertEquals(expected,true);
    }
    @Test
    public void checkSumOfSalary(){
        EmployeeDetailsToDatabase employeeDetailsToDatabase = new EmployeeDetailsToDatabase();
        boolean expected = employeeDetailsToDatabase.getMinMaxSumAvg("F");
        Assertions.assertEquals(expected,true);
    }

}
