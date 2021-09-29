package com.bridgelabz.employeepayrollservice;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.employeepayrollservice.EmployeePayrollService.IOService;
public class EmployeePayrollServiceTest {

	@Test
	public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {

		Employee[] arrayOfEmployees = {
				new Employee(1, "Jeff Bezos", 100000.0),
				new Employee(2, "Bill Gates", 200000.0),
				new Employee(3, "Mark Zuckerberg", 300000.0)
		};
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmployees));
		employeePayrollService.writeEmployee(IOService.FILE_IO);
		employeePayrollService.printData(IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		Assert.assertEquals(3, entries);
	}
	@Test
	public void givenFileOnReadingFromFileShouldMatchEmployeeCount() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		long entries = employeePayrollService.readEmployee(EmployeePayrollService.IOService.FILE_IO).size();
		assertEquals(3, entries);
	} 

	@Test
	public void  givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {

		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<Employee> employeePayrollList = employeePayrollService.readEmployee(IOService.DB_IO);
		Assert.assertEquals(3, employeePayrollList.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB()
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		@SuppressWarnings("unused")
		List<Employee> employeePayrollList = employeePayrollService.readEmployee(IOService.DB_IO);
		employeePayrollDBService.updateEmployeeDataUsingStatement("Terisa",3000000.0);
		boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
		Assert.assertTrue(result);
	}

	@Test
	public void givenEmployeePayrollInDB_ShouldRetrieveEmployeeSalarySumWithGenderMap()
	{
		EmployeePayrollDBService employeePayrollService = new EmployeePayrollDBService();

		Map<String, Double> expectedGenderSalaryMap = new HashMap<String, Double>();
		expectedGenderSalaryMap.put("M", 2000000.0);
		expectedGenderSalaryMap.put("F", 3000000.0);
		Map<String, Double> genderSalaryMap = employeePayrollService.getDetailsBasedOnGender(1);
		Assert.assertEquals(expectedGenderSalaryMap, genderSalaryMap);
	}

	@Test
	public void givenEmployeePayrollInDB_ShouldRetrieveEmployeeSalaryAvgWithGenderMap()
	{
		EmployeePayrollDBService employeePayrollService = new EmployeePayrollDBService();

		Map<String, Double> expectedGenderSalaryMap = new HashMap<String, Double>();
		expectedGenderSalaryMap.put("M", 1000000.0);
		expectedGenderSalaryMap.put("F", 3000000.0);
		Map<String, Double> genderSalaryMap = employeePayrollService.getDetailsBasedOnGender(2);
		Assert.assertEquals(expectedGenderSalaryMap, genderSalaryMap);
	}

	@Test
	public void givenEmployeePayrollInDB_ShouldRetrieveEmployeeSalaryMinWithGenderMap()
	{
		EmployeePayrollDBService employeePayrollService = new EmployeePayrollDBService();

		Map<String, Double> expectedGenderSalaryMap = new HashMap<String, Double>();
		expectedGenderSalaryMap.put("M", 1000000.0);
		expectedGenderSalaryMap.put("F", 3000000.0);
		Map<String, Double> genderSalaryMap = employeePayrollService.getDetailsBasedOnGender(3);
		Assert.assertEquals(expectedGenderSalaryMap, genderSalaryMap);
	}

	@Test
	public void givenEmployeePayrollInDB_ShouldRetrieveEmployeeSalaryMaxWithGenderMap()
	{
		EmployeePayrollDBService employeePayrollService = new EmployeePayrollDBService();

		Map<String, Double> expectedGenderSalaryMap = new HashMap<String, Double>();
		expectedGenderSalaryMap.put("M", 1000000.0);
		expectedGenderSalaryMap.put("F", 3000000.0);
		Map<String, Double> genderSalaryMap = employeePayrollService.getDetailsBasedOnGender(4);
		Assert.assertEquals(expectedGenderSalaryMap, genderSalaryMap);
	}

	@Test
	public void givenEmployeePayrollInDB_ShouldRetrieveEmployeeSalaryCountWithGenderMap()
	{
		EmployeePayrollDBService employeePayrollService = new EmployeePayrollDBService();

		Map<String, Double> expectedGenderSalaryMap = new HashMap<String, Double>();
		expectedGenderSalaryMap.put("M", 2.0);
		expectedGenderSalaryMap.put("F", 1.0);
		Map<String, Double> genderSalaryMap = employeePayrollService.getDetailsBasedOnGender(5);
		Assert.assertEquals(expectedGenderSalaryMap, genderSalaryMap);
	}	

	@Test
	public void givenDateRangeOfJoiningDate_ShouldReturnCountOfEmployeesJoined()
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String startDate="2018-01-02";
		String endDate="2019-11-30";

		LocalDate.parse(startDate, formatter);
		LocalDate.parse(endDate, formatter);
		int count=employeePayrollService.getEmployeeJoinCount(IOService.DB_IO,startDate,endDate);
		Assert.assertEquals(2,count);
	}
	@Test
	public void givenNewEmployee_WhenAddedShouldSyncWithDB() throws SQLException, EmployeePayrollException {

		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployee(IOService.DB_IO);
		employeePayrollService.addEmployeeToPayroll("Test", "M", 10000.00, LocalDate.now(), 1);
		boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Test");
		Assert.assertTrue(result);
		EmployeePayrollDBService.getInstance().removeEmployee("Test");
	}

	@Test
	public void whenEmployeeIsRemoved_DatabaseShouldContainEmployeeDetailAsInactive() {
		
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		int count = employeePayrollService.readEmployee( IOService.DB_IO).size();
		List<Employee> employeeListFromDBEmployees=	employeePayrollService .removeEmployee(IOService.DB_IO,1);
		List<Employee> employeeListInMemory = employeePayrollService.getEmployeePayrollList();
		Assert.assertEquals(employeeListFromDBEmployees.size(), count);
		Assert.assertEquals(employeeListInMemory.size(), count-1);
	}
	
	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldUpdateInEmployeeAndPayroll()
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		@SuppressWarnings("unused")
		List<Employee> employeePayrollList = employeePayrollService.readEmployee(IOService.DB_IO);
		try {
			employeePayrollDBService.updateEmployeeToPayroll("Terisa",3000000.0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
		Assert.assertTrue(result);
	}
}