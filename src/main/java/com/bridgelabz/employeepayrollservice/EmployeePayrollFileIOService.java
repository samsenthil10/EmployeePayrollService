package com.bridgelabz.employeepayrollservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollFileIOService {

	public static String PAYROLL_FILE_NAME = "payroll-file.txt";

	public void writeData(List<Employee> employeePayrollList) {
		StringBuffer empBuffer = new StringBuffer();
		employeePayrollList.forEach(employee -> {
			String employeeDataString = employee.toString().concat("\n");
			empBuffer.append(employeeDataString);
		});

		try {
			Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printData() {
		try {
			Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(System.out::println);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long countEntries() {
		long entries = 0;
		try {
			entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public List<Employee> readData() {

		List<Employee> employeePayrollList = new ArrayList<Employee>();
		List<String[]> employeeDataFromFile = new ArrayList<String[]>();
		try {

			Files.lines(new File("payroll-file.txt").toPath())
			.map(line->line.trim())
			.forEach(line->employeeDataFromFile.add(line.split(",")));
			for(int index=0;index<employeeDataFromFile.size();index++) {

				employeePayrollList.add(new Employee(Integer.parseInt(employeeDataFromFile.get(index)[0].split("=")[1])
						,employeeDataFromFile.get(index)[1].split("=")[1]
								,Double.parseDouble(employeeDataFromFile.get(index)[3].split("=")[1])));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}
}