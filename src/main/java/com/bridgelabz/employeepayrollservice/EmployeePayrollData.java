package com.bridgelabz.employeepayrollservice;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeePayrollData {

	public int id;
	public String name;
	public double salary;
	public LocalDate startDate; 

	public EmployeePayrollData(int id, String name, double salary, LocalDate startDate) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.startDate = startDate; 
	}
	
	public EmployeePayrollData(int id,String name,double salary) 
	{
		this.id=id;
		this.name=name;
		this.salary=salary;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePayrollData other = (EmployeePayrollData) obj;
		return Objects.equals(name, other.name) && id == other.id
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
				&& Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", salary=" + salary;
	}
}
