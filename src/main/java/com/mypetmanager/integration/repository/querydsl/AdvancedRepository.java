package com.mypetmanager.integration.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mypetmanager.integration.repository.pet.dto.PetResponseRecord;

public interface AdvancedRepository {
	Page<PetResponseRecord> findPetPageBySearchParams(Long petId, String petName, Pageable pageable) throws Exception;
}
