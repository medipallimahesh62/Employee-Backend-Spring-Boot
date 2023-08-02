package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByIdIn(List<Integer> addressIds);
	
	List<Address> findByEmployeeId(int employeeId);
	
	List<Address> findByCity(String city);
}
