package com.example.employee.service;

 
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.employee.domain.Employee;
import com.example.employee.domain.Address;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.AddressDto;
import com.example.employee.exception.EmployeeException;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.AddressRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private AddressRepository addressRepo;

	 

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public EmployeeDto addOrUpdateEmployee(EmployeeDto employeeDto) {
		try {
			Employee employee = new Employee();
			employee.setId(employeeDto.getId());
			employee.setEmployeeName(employeeDto.getEmployeeName());
			employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
			employee.setEmployeePhone(employeeDto.getEmployeePhone());
			employee.setEmployeeDate(employeeDto.getEmployeeDate());
			employeeRepo.save(employee);
			return employeeDto;
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public AddressDto saveAddresssForAEmployee(AddressDto address) {
		try {
			Address addressEntity = new Address();
			BeanUtils.copyProperties(address, addressEntity);
			addressEntity.setEmployee(employeeRepo.findById(address.getEmployeeId()).get());
			addressRepo.save(addressEntity);
			return address;
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public AddressDto updateAddress(AddressDto addressDto) {
		try {
			String query = "update address set city='" + addressDto.getCity() + "' where id='" + addressDto.getId()
					+ "' ";
			jdbcTemplate.update(query);
			return addressDto;
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public Boolean deleteEmployeeDetails(int employeeId) {
		try {
			employeeRepo.deleteById(employeeId);
			return true;
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public Boolean deleteAddress(int addressId) {
		try {
			String query = "delete from address where id='" + addressId + "' ";
			jdbcTemplate.update(query);
			return true;
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public List<AddressDto> fetchAllAddresss() {
		try {
			String sql = "select p.id, p.city, b.employee_name from address p inner join employee b on b.id = p.employee_id";
			List<AddressDto> addressDtoList = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<AddressDto>(AddressDto.class));
			if (addressDtoList != null && !addressDtoList.isEmpty()) {
				return addressDtoList;
			} else {
				throw new EmployeeException("Addresss not found, please add a address");
			}
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}


	@Override
	public List<AddressDto> fetchAddresssForAEmployee(int employeeId) {
		try {
			String sql = "select p.id, p.city, b.employee_name,p.employee_id from address p inner join employee b on b.id = p.employee_id where p.employee_id = "
					+ employeeId;
			List<AddressDto> addressDtoList = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<AddressDto>(AddressDto.class));
			if (addressDtoList != null && !addressDtoList.isEmpty()) {
				return addressDtoList;
			} else {
				throw new EmployeeException("Addresss not found, please add a address");
			}
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	public List<EmployeeDto> getallEmp(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Employee> pagedResult = employeeRepo.findAll(paging);

		if (pagedResult.hasContent()) {
			List<Employee> employeeList = pagedResult.getContent();
			List<EmployeeDto> finalResult = new ArrayList<>();
			employeeList.forEach(employee -> {
				EmployeeDto employeeObj = new EmployeeDto();
				employeeObj.setId(employee.getId());
				employeeObj.setEmployeeName(employee.getEmployeeName());
				employeeObj.setAddressCount(employee.getAddress().size());
				employeeObj.setEmployeeEmail(employee.getEmployeeEmail());
				employeeObj.setEmployeePhone(employee.getEmployeePhone());
				employeeObj.setEmployeeDate(employee.getEmployeeDate());

				finalResult.add(employeeObj);
			});

			return finalResult;

		} else {
			return new ArrayList<EmployeeDto>();
		}
	}

}
