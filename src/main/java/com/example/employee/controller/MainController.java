package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.dto.EmployeeDto;
 
import com.example.employee.dto.AddressDto;
import com.example.employee.service.CsvServiceImpl;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class MainController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CsvServiceImpl csvServiceImpl;
	
	
	@PostMapping("add-or-update-employee")
	public ResponseEntity<EmployeeDto> addOrUpdateEmployee(@RequestBody EmployeeDto employeeDto) {
		return new ResponseEntity<EmployeeDto>(employeeService.addOrUpdateEmployee(employeeDto), HttpStatus.OK);
	}
	
	@PostMapping("save-address-for-a-employee")
	public ResponseEntity<AddressDto> saveAddresssForAEmployee(@RequestBody AddressDto address) {
		return new ResponseEntity<AddressDto>(employeeService.saveAddresssForAEmployee(address), HttpStatus.OK);
	}
	
	@PutMapping("update-address")
	public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto) {
		return new ResponseEntity<AddressDto>(employeeService.updateAddress(addressDto), HttpStatus.OK);
	}
	
	@DeleteMapping("delete-employee-details")
	public ResponseEntity<Boolean> deleteEmployeeDetails(@RequestParam("employeeId") Integer employeeId) {
		return new ResponseEntity<Boolean>(employeeService.deleteEmployeeDetails(employeeId), HttpStatus.OK);
	}
	
	@DeleteMapping("delete-address")
	public ResponseEntity<Boolean> deleteAddresss(@RequestParam Integer addressId) {
		return new ResponseEntity<Boolean>(employeeService.deleteAddress(addressId), HttpStatus.OK);
	}
	
	@GetMapping("fetch-all-address-details")
	public ResponseEntity<List<AddressDto>> fetchAllAddressDetails() {
		return new ResponseEntity<List<AddressDto>>(employeeService.fetchAllAddresss(), HttpStatus.OK);
	}

	
	@GetMapping("fetch-address-details-for-a-employee")
	public ResponseEntity<List<AddressDto>> fetchAddresssForAEmployee(@RequestParam Integer employeeId) {
		return new ResponseEntity<List<AddressDto>>(employeeService.fetchAddresssForAEmployee(employeeId), HttpStatus.OK);
	}
	@GetMapping("page-for-employee")
	public ResponseEntity<List<EmployeeDto>> getallEmp(@RequestParam int pageNo,
			@RequestParam int pageSize,@RequestParam String sortBy) {
		return new ResponseEntity<List<EmployeeDto>>(employeeService.getallEmp(pageNo,pageSize,sortBy), HttpStatus.OK);
	}
	 @GetMapping("emp-csv")
	  public ResponseEntity<Resource> getFile() {
	    String filename = "emp.csv";
	    InputStreamResource file = new InputStreamResource(csvServiceImpl.load());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/csv"))
	        .body(file);
	  }
	
 
	
}
