package com.mypetmanager.integration.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mypetmanager.integration.repository.pet.dto.PetResponseRecord;
import com.mypetmanager.integration.repository.pet.entity.Pet;
import com.mypetmanager.integration.repository.pet.entity.QPet;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdvancedRepositoryImpl implements AdvancedRepository { // extends QuerydslRepositorySupport

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<PetResponseRecord> findPetPageBySearchParams(Long petId, String petName, Pageable pageable)
		throws Exception {
		QPet qpet = QPet.pet;

		Pet myPet = queryFactory.selectFrom(qpet).where(qpet.petId.eq(petId)).fetchFirst();
		System.out.println(myPet.getPetName());
		System.out.println(myPet.getPetName());


		/*
		  * usring QuerydslRepositorySupport
		  */
		//		 QPet qpet = new QPet("qpet"); // 여기서는 QPet의 생성자를 호출하여 인스턴스를 생성합니다.

		//		JPQLQuery<PetResponseRecord> query = from(qpet)
		//				.select(Projections.constructor(PetResponseRecord.class, qpet.petId, qpet.petName, qpet.petType));

		//		if (petName != null && !petName.isBlank()) {
		//			query.where(qpet.petName.containsIgnoreCase(petName));
		//		}

		//		List<PetResponseRecord> resultRecord = Optional.ofNullable(getQuerydsl()).orElseThrow(() -> new Exception("dd"))
		//		.applyPagination(pageable, query).fetch();

		return null; // query.fetch();
	}

}