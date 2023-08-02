package com.example.employee.service;

 
import java.util.List;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.AddressDto;

public interface EmployeeService {

	public EmployeeDto addOrUpdateEmployee(EmployeeDto employeeDto);
	
	public AddressDto saveAddresssForAEmployee(AddressDto address);
	
	public AddressDto updateAddress(AddressDto addressDto);
	
	public Boolean deleteEmployeeDetails(int employeeId);
	
	public Boolean deleteAddress(int addressId);
	
	public List<AddressDto> fetchAllAddresss();
	
	 
	
	public List<AddressDto> fetchAddresssForAEmployee(int employeeId);
	public List<EmployeeDto> getallEmp(Integer pageNo, Integer pageSize, String sortBy);
 
}
