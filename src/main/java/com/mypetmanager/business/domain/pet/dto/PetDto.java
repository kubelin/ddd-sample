package com.mypetmanager.business.domain.pet.dto;

import java.sql.Date;
import java.sql.Timestamp;

import com.mypetmanager.integration.repository.owner.entity.Owner;

import lombok.Data;

@Data
public class PetDto {
	private Long petId;
	private Owner owner;
	private String petName;
	private Date petBirthDate;
	private String petType;
	private String breed;
	private Timestamp createdAt;
	private Timestamp updatedAt;

}
