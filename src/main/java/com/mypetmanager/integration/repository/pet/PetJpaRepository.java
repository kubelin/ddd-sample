package com.mypetmanager.integration.repository.pet;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypetmanager.integration.repository.pet.entity.Pet;

public interface PetJpaRepository extends JpaRepository<Pet, Long>{

}
