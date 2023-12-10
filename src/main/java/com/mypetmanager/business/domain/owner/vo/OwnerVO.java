package com.mypetmanager.business.domain.owner.vo;

import java.sql.Timestamp;

import com.mypetmanager.global.annotation.domain.ValueObject;
import com.mypetmanager.integration.repository.owner.dto.MembershipDTO;

import lombok.Getter;
import lombok.Setter;

@ValueObject
@Setter
@Getter
public class OwnerVO {
	private Long ownerId;
	private String name;
	private String birthDate;
	private String email;
	private String phoneNumber;
	private String address;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	private MembershipDTO membershipDto;
	
	public OwnerVO() {
	}
	
	public OwnerVO(Long ownerId, String name, String birthDate, String email, String phoneNumber, String address,
			Timestamp createdAt, Timestamp updatedAt, MembershipDTO membershipDto) {
		super();
		this.ownerId = ownerId;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.membershipDto = membershipDto;
	}
	
	
//	
//	public void setMembershipDto(MembershipDTO memberDto) {
//		this.membershipDto = memberDto;
//		this.membershipDto.setMemberDetail(memberDto.getMemberDetail());
//	}

	@Override
	public String toString() {
		return "OwnerVO [ownerId=" + ownerId + ", name=" + name + ", birthDate=" + birthDate + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", membershipDto=" + membershipDto + "]";
	}
	
	
	
}
