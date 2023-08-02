package com.example.employee.dto;

import java.util.Date;
import java.util.List;

public class EmployeeDto {

	private int id;
	private String employeeName;
	private String employeeEmail;
	private String employeePhone;
	private Date employeeDate;
	
	private List<AddressDto> addresss;
	private long addressCount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
 
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeePhone() {
		return employeePhone;
	}
	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}
	public Date getEmployeeDate() {
		return employeeDate;
	}
	public void setEmployeeDate(Date employeeDate) {
		this.employeeDate = employeeDate;
	}
	public List<AddressDto> getAddresss() {
		return addresss;
	}
	public void setAddresss(List<AddressDto> addresss) {
		this.addresss = addresss;
	}
	
	public long getAddressCount() {
		return addressCount;
	}
	public void setAddressCount(long addressCount) {
		this.addressCount = addressCount;
	}
}
