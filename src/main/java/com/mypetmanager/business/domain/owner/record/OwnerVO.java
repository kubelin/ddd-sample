package com.mypetmanager.business.domain.owner.record;

import java.sql.Timestamp;
import java.util.List;

import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.pet.dto.PetDto;
import com.mypetmanager.global.annotation.domain.ValueObject;

import lombok.Getter;

@ValueObject
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
	
	private MembershipDto membershipDto;
	
	private List<PetDto> petList;
	
	public OwnerVO() {
	}
	
	public OwnerVO(Long ownerId, String name, String birthDate, String email, String phoneNumber, String address,
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
		this.membershipDto = membershipDto;
	}
	
	public void setMembership(MembershipDto membership) {
		this.membershipDto = membership;
	}
	
	@Override
	public String toString() {
		return "OwnerVO [ownerId=" + ownerId + ", name=" + name + ", birthDate=" + birthDate + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", membershipDto=" + membershipDto + "]";
	}
	
	
	
}
