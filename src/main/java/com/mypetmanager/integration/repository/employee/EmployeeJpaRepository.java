package com.mypetmanager.integration.repository.employee;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mypetmanager.integration.repository.employee.entity.Employee;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

	// search by employee_id
	Employee findByEmployeeId(String employeeId);

	@Query(value = "SELECT * FROM employees WHERE employee_id = :empId", nativeQuery = true)
	Employee findByEmployeeIdNative(@Param("empId") String employeeId);

}
