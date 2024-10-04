package com.mypetmanager.integration.adaptor;

import org.springframework.stereotype.Repository;

import com.mypetmanager.global.infra.GenericJpaAdapter;
import com.mypetmanager.integration.repository.employee.EmployeeJpaRepository;
import com.mypetmanager.integration.repository.employee.EmployeeRepository;
import com.mypetmanager.integration.repository.employee.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@AllArgsConstructor
public class EmployeeAdapter extends GenericJpaAdapter implements EmployeeRepository {
	private EmployeeJpaRepository employeeJpaRepo;

	@Override
	public Employee getEmployee(String employeeId) throws Exception {
		log.info("Get employee by id: {}", employeeId);
		if (employeeId == null) {
			throw new IllegalArgumentException("Employee ID must not be null");
		}
		log.info("EmployeeRepository getEmployee called");

		Employee employee = employeeJpaRepo.findByEmployeeId(employeeId.trim());

		return employee;
	}

}

