package com.mypetmanager.global.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionCheckInterceptor implements HandlerInterceptor {

	private final static String TEST_ID = "A001";
	private final static String EMPLOYEE_ID_ATTR = "employeeId";

	/**
	 * 임시 테스트를 위한 session check interceptor
	 * if session is expired, redirect to login page
	 * if session is not exist, redirect to login page
	 * @author kubel
	 * 2024. 10. 3.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// GET 메소드는 세션 체크를 하지 않음
		if ("GET".equalsIgnoreCase(request.getMethod())) {
			log.info("Session ID : {}", request.getSession().getId());
			return true;
		}
		// 로그인 권한 체크
		HttpSession session = request.getSession(false);
		// 로그인 권한 없을 경우 false
		if (!isValidSession(session)) {
			log.info("Session is not exist or invalid. Redirect to login page.");
			response.sendRedirect("/employee/search");
			return false;
		}

		// 비정상 접근 체크 ( test id로만 로그인 가능 )
		String sessionEmployeeId = (String)session.getAttribute(EMPLOYEE_ID_ATTR);
		String paramEmployeeId = request.getParameter(EMPLOYEE_ID_ATTR);

		// 로그인 ID외의 다른 ID 조회시, 에러
		if (!isValidAttempt(sessionEmployeeId, paramEmployeeId)) {
			log.error("Potential admin impersonation attempt detected.");
			log.error(sessionEmployeeId + " <> " + paramEmployeeId);

			response.sendError(HttpStatus.FORBIDDEN.value(), "Access Denied");
			return false;
		}

		log.info("Session ID : {}", session.getId());
		log.info("Login Name : {}", session.getAttribute("name"));
		log.info("Login EmployeeId : {}", session.getAttribute("employeeId"));
		return true; // 정상 접근

	}

	private boolean isValidSession(HttpSession session) {
		return session != null && session.getAttribute(EMPLOYEE_ID_ATTR) != null;

	}

	private boolean isValidAttempt(String sessionEmployeeId, String paramEmployeeId) {
		return TEST_ID.equals(sessionEmployeeId) && TEST_ID.equals(paramEmployeeId);
	}
}
