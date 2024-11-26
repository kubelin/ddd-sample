package com.mypetmanager.business.domain.owner.record;

import java.sql.Timestamp;
import java.util.List;

import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.pet.dto.PetDto;

public record OwnerVO2(Long ownerId, String name, String birthDate, String email, String phoneNumber, String address,
	Timestamp createdAt, Timestamp updatedAt, MembershipDto membershipDto, List<PetDto> petList) {

	@Override
	public String toString() {
		return "OwnerVO [ownerId=" + ownerId + ", name=" + name + ", birthDate=" + birthDate + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", membershipDto=" + membershipDto + "]";
	}



}
