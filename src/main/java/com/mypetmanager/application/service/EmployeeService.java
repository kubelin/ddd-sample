package com.mypetmanager.application.service;

import com.mypetmanager.integration.repository.employee.entity.Employee;

public interface EmployeeService {

	public Employee getEmployee(String employeeId) throws Exception;

}
