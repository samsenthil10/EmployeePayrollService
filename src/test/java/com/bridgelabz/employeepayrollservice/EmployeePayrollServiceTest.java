package com.bridgelabz.employeepayrollservice;

import static org.junit.Assert.assertEquals;

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
        EmployeePayrollData[] arrayOfEmps = {
                new EmployeePayrollData(1, "Jeff Bezos", 100000.0),
                new EmployeePayrollData(2, "Bill Gates", 200000.0),
                new EmployeePayrollData(3, "Mark Zuckerberg", 300000.0)
        };
        EmployeePayrollService employeePayrollService;
        employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
        employeePayrollService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);
		Assert.assertEquals( 3, entries);
    }
    @Test
    public void givenFileOnReadingFromFileShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        assertEquals(3, entries);
    } 
    
    @Test
	public void  givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount()
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollList = employeePayrollService.readEmployeePayrollDataFromDB( IOService.DB_IO);
		Assert.assertEquals(3, employeePayrollList.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB()
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		@SuppressWarnings("unused")
		List<EmployeePayrollData> employeePayrollList = employeePayrollService.readEmployeePayrollDataFromDB(IOService.DB_IO);
		employeePayrollService.updateEmployeeSalary("Terisa",3000000.00);
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
}
