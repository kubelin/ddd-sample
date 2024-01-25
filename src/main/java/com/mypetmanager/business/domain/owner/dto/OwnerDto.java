package com.mypetmanager.business.domain.owner.dto;

import java.sql.Timestamp;

import com.mypetmanager.global.annotation.domain.ValueObject;

import lombok.Builder;
import lombok.Getter;

@ValueObject
@Getter
public class OwnerDto {
	private Long ownerId;
	private String name;
	private String birthDate;
	private String email;
	private String phoneNumber;
	private String address;
	private Timestamp createdAt;
	private Timestamp updatedAt;


	public OwnerDto() {
	}

	@Builder
	public OwnerDto(Long ownerId, String name, String birthDate, String email, String phoneNumber, String address,
			Timestamp createdAt, Timestamp updatedAt, MembershipDto membershipDto) {
		super();
		this.ownerId = ownerId;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	@Override
	public String toString() {
		return "OwnerVO [ownerId=" + ownerId + ", name=" + name + ", birthDate=" + birthDate + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", membershipDto=" + "]";
	}



}
