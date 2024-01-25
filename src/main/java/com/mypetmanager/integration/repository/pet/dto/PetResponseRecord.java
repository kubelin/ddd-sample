package com.mypetmanager.integration.repository.pet.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.mypetmanager.integration.repository.owner.entity.Owner;

public record PetResponseRecord(Long petId, Owner owner, String petName, Date petBirthDate, String petType,
		String breed, Timestamp createdAt, Timestamp updatedAt) {

	public PetResponseRecord of(Long petId, Owner owner, String petName) {
		return new PetResponseRecord(petId, owner, petName, petBirthDate, petName, petName, createdAt, updatedAt);
	}
}
