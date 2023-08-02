package com.example.employee.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
 
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.domain.Employee;
 
import com.example.employee.repository.EmployeeRepository;
@Service
public class CsvServiceImpl {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	public static ByteArrayInputStream employeesToCSV(List<Employee> employees) {
		final String[] HEADERS = { "Id", "Full name", "Email address", "Phone number", "Date of birth" };
		final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), FORMAT);) {
			for (Employee employee : employees) {
				List<String> data = Arrays.asList(String.valueOf(employee.getId()), employee.getEmployeeName(),
						employee.getEmployeeEmail(), employee.getEmployeePhone(),
						String.valueOf(employee.getEmployeeDate()));

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}
	 public ByteArrayInputStream load() {
		    List<Employee> employees = employeeRepo.findAll();

		    ByteArrayInputStream in = employeesToCSV(employees);
		    return in;
		  }

}
