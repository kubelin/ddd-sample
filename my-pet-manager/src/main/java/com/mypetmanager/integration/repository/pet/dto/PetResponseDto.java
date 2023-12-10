package com.mypetmanager.integration.repository.pet.dto;

import java.sql.Date;
import java.sql.Timestamp;

import com.mypetmanager.integration.repository.owner.entity.Owner;

import lombok.Builder;
import lombok.Getter;


@Getter
public class PetResponseDto {
	private Long petId;
    private Owner owner;
    private String petName;
    private java.sql.Date petBirthDate;
    private String petType;
    private String breed;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    
    @Builder
	public PetResponseDto(Long petId, Owner owner, String petName, Date petBirthDate, String petType, String breed,
			Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.petId = petId;
		this.owner = owner;
		this.petName = petName;
		this.petBirthDate = petBirthDate;
		this.petType = petType;
		this.breed = breed;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    
    
}
