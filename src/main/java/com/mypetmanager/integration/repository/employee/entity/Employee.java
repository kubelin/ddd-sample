package com.mypetmanager.integration.repository.employee.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	private String name;

	@Column(name = "employee_id", length = 20, unique = true)
	private String employeeId;

	@Column(name = "phone_number", length = 20)
	private String phoneNumber;

	@Column(name = "createdat", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "modifiedat")
	private LocalDateTime modifiedAt;

	// 기본 생성자
	public Employee() {}

	// 모든 필드를 포함한 생성자
	public Employee(String name, String employeeId, String phoneNumber) {
		this.name = name;
		this.employeeId = employeeId;
		this.phoneNumber = phoneNumber;
	}

	// Getter와 Setter 메서드
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	// createdat과 modifiedat을 위한 JPA 콜백 메서드
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		modifiedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		modifiedAt = LocalDateTime.now();
	}

	// equals, hashCode, toString 메서드
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Employee employee = (Employee)o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(employeeId, employee.employeeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, employeeId);
	}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", name='" + name + '\'' +
			", employeeId='" + employeeId + '\'' +
			", phoneNumber='" + phoneNumber + '\'' +
			", createdAt=" + createdAt +
			", modifiedAt=" + modifiedAt +
			'}';
	}
}