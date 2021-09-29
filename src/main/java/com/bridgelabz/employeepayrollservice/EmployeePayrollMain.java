package com.bridgelabz.employeepayrollservice;

import java.util.ArrayList;
import java.util.Scanner;

import com.bridgelabz.employeepayrollservice.EmployeePayrollService.IOService;

public class EmployeePayrollMain {
	
	public static void main(String[] args) {

		ArrayList<Employee> employeePayrollList = new ArrayList<>();
		EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployee(consoleInputReader);
		employeePayrollService.writeEmployee(IOService.CONSOLE_IO);
	}
}
