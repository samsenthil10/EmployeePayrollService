package com.bridgelabz.employeepayrollservice;

import java.util.List;
import java.util.Scanner;
public class EmployeePayrollService {

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	private List<EmployeePayrollData> employeePayrollList;

	EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();

	public EmployeePayrollService() { 
		
		employeePayrollDBService=EmployeePayrollDBService.getInstance();

	}

	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	public void readEmployeePayrollData(Scanner consoleInputReader) {
		System.out.println("Enter Employee ID: ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter Employee Name: ");
		String name = consoleInputReader.next();
		System.out.println("Enter Employee Salary: ");
		double salary = consoleInputReader.nextDouble();
		employeePayrollList.add(new EmployeePayrollData(id, name, salary));
	}

	public void writeEmployeePayrollData(IOService ioservice) {
		if (ioservice.equals(IOService.CONSOLE_IO))
			System.out.println("\nWriting Employee Payroll to Console\n" + employeePayrollList);
		else if (ioservice.equals(IOService.FILE_IO)) {
			new EmployeePayrollFileIOService().writeData(employeePayrollList);
		}
	}

	public void printData(IOService ioservice) {
		if (ioservice.equals(IOService.FILE_IO)) {
			new EmployeePayrollFileIOService().printData();
		}
	}

	public long countEntries(IOService ioservice) {
		if (ioservice.equals(IOService.FILE_IO)) {
			return new EmployeePayrollFileIOService().countEntries();
		}
		return 0;
	}


	public  List<EmployeePayrollData> readEmployeePayrollDataFromDB(IOService ioService)
	{
		if(ioService.equals(IOService.DB_IO))
		{
			this.employeePayrollList=new EmployeePayrollDBService().readData();
		}
		return employeePayrollList;
	}
	
	public  List<EmployeePayrollData> readEmployeePayrollDataFromOldDB(IOService ioService)
	{
		if(ioService.equals(IOService.DB_IO))
		{
			this.employeePayrollList=new EmployeePayrollDBService().readDataOld();
		}
		return employeePayrollList;
	}

	public long readEmployeePayrollData(IOService ioservice) {

		if (ioservice.equals(IOService.FILE_IO)) {
			this.employeePayrollList = new EmployeePayrollFileIOService().readData();
			System.out.println("PARSED DATA FROM FILE: ");
			this.employeePayrollList.forEach(System.out::println);
		}
		return this.employeePayrollList.size();
	}	

	public void updateEmployeeSalary(String name, double salary) 
	{
		int result =employeePayrollDBService.updateEmployeeData(name, salary);
		if(result==0) return;
		EmployeePayrollData employeePayrollData=this.getEmployeePayrollData(name);
		if(employeePayrollData!=null) employeePayrollData.salary=salary;

	}

	private EmployeePayrollData getEmployeePayrollData(String name) 
	{
		EmployeePayrollData employeePayrollData;
		employeePayrollData=this.employeePayrollList.stream()
				.filter(employeeDataItem->employeeDataItem.name.equals(name))
				.findFirst()
				.orElse(null);

		return employeePayrollData;

	}

	public boolean checkEmployeePayrollInSyncWithDB(String name)
	{
		List<EmployeePayrollData> employeePayrollDataList=employeePayrollDBService.getEmployeePayrollData(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}
	
	public boolean checkEmployeePayrollInSyncWithDBUsingStatement(String name)
	{
		List<EmployeePayrollData> employeePayrollDataList=employeePayrollDBService.getEmployeePayrollDataFromDBUsingStatement(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}
	
	public int getEmployeeJoinCount(IOService ioService, String startDate, String endDate)
	{
		if(ioService.equals(IOService.DB_IO))
		{
			return employeePayrollDBService.getEmployeeJoinCount( startDate,  endDate);
		}

		return 0;
	}

	public void deleteEmployeeData(IOService ioService,int id) 
	{
		if(ioService.equals(IOService.DB_IO))
		{
			 employeePayrollDBService.deleteEmployeeData(id);
		}
		
	}
	
}
