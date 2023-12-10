package com.mypetmanager.integration.repository.pet.entity;

import com.mypetmanager.integration.repository.owner.entity.Owner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;


@Entity
@Getter
@Table(name = "pet")
public class Pet {
	
	@Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
	private Long petId;
	
//    @Column(name = "owner_id")
//    private Long ownerId;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
	
    @Column(name = "pet_name")
    private String petName;

    @Column(name = "pet_birth_date")
    private java.sql.Date petBirthDate;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "breed")
    private String breed;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

}
