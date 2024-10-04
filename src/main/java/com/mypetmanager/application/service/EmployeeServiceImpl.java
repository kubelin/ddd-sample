package com.mypetmanager.application.service;

import org.springframework.stereotype.Service;

import com.mypetmanager.integration.adaptor.EmployeeAdapter;
import com.mypetmanager.integration.repository.employee.entity.Employee;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeAdapter employeeAdapter;

	@Override
	public Employee getEmployee(String employeeId) throws Exception {

		// find employee
		Employee employee = employeeAdapter.getEmployee(employeeId);

		if (employee == null) {
			throw new Exception("Employee not found");
		}

		return employee;
	}

}
