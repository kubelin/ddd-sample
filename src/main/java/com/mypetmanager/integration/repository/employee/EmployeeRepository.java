package com.mypetmanager.integration.repository.employee;

import org.springframework.stereotype.Repository;

import com.mypetmanager.integration.repository.employee.entity.Employee;

@Repository
public interface EmployeeRepository {

	public Employee getEmployee(String employeeId) throws Exception;

}
