package com.bridgelabz.employeepayrollservice;


@SuppressWarnings("serial")
public class EmployeePayrollException extends RuntimeException
{
	enum ExceptionType 
	{
		ENTERED_NULL, ENTERED_EMPTY
	}

	ExceptionType type;

	public EmployeePayrollException(ExceptionType type, String message) 
	{
		super(message);
		this.type = type;

	}
}
