package com.bridgelabz.employeepayrollservice;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Employee {
	
	private int employeeId;
	private String employeeName;
	private double salary;
	private String gender;
	private LocalDate startDate;
	private Company company;
	private Payroll payroll;
	private List<Department> departments;

	public Employee(int employeeId, String employeeName,double salary) {

		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.salary = salary;
	}


	public Employee(int employeeId, String employeeName, double salary,LocalDate startDate) {
		this(employeeId, employeeName, salary);
		this.startDate = startDate;
	}


	public Employee(int employeeId, String employeeName, String gender, double salary, LocalDate startDate, Company company, Payroll payroll, List<Department> departments) {

		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.gender = gender;
		this.salary = salary;
		this.startDate = startDate;
		this.company = company;
		this.payroll = payroll;
		this.departments = departments;
	}

	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Payroll getPayroll() {
		return payroll;
	}


	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}


	public List<Department> getDepartments() {
		return departments;
	}


	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(company, other.company) && Objects.equals(departments, other.departments)
				&& employeeId == other.employeeId && Objects.equals(employeeName, other.employeeName)
				&& Objects.equals(gender, other.gender) && Objects.equals(payroll, other.payroll)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
				&& Objects.equals(startDate, other.startDate);
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", gender=" + gender
				+ ", salary=" + salary + ", startDate=" + startDate + ", company=" + company + ", payroll=" + payroll
				+ ", departments=" + departments + "]";
	}
}
