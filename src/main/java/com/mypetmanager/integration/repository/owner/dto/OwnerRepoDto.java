package com.mypetmanager.integration.repository.owner.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OwnerRepoDto {
	private Long ownerId;
	private String name;
	private String birthDate;
	private String email;
	private String phoneNumber;
	private String address;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	@Builder
	public OwnerRepoDto(Long ownerId, String name, String birthDate, String email, String phoneNumber, String address,
			Timestamp createdAt, Timestamp updatedAt) {
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


}
