package com.mypetmanager.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mypetmanager.application.service.EmployeeService;
import com.mypetmanager.integration.repository.employee.entity.Employee;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class SampleJspController01 {

	private EmployeeService employeeService;

	@GetMapping("/employee/login")
	public ModelAndView loginEmployee(HttpSession session) {

		// 임시로 controller에서 session attribute 초기화
		session.setAttribute("employeeId", "A001");

		return new ModelAndView("EmployeeSearch");
	}

	@GetMapping("/employee/search")
	public ModelAndView showSearchForm() {

		return new ModelAndView("EmployeeSearch");
	}

	@GetMapping("/employee/result")
	public ModelAndView showSearchResult() {

		return new ModelAndView("EmployeeResult");
	}

	@PostMapping("/employee/search")
	public ModelAndView searchEmployee(
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String employeeId,
		@RequestParam(required = false) String phoneNumber) {


		ModelAndView mav = new ModelAndView("EmployeeResult");

		// search from database
		Employee employee;
		try {
			log.info("empyloeeid param {}", employeeId);
			employee = employeeService.getEmployee(employeeId);


			mav.addObject("name", employee.getName());
			mav.addObject("employeeId", employee.getEmployeeId());
			mav.addObject("phoneNumber", employee.getPhoneNumber());
			mav.addObject("searchPerformed", true);
		} catch (Exception e) {
			log.error("Error occurred while searching employee: {}", e.getMessage());

			mav.addObject("errorMessage", "Employee not found.");
		}


		return mav;
	}
}
