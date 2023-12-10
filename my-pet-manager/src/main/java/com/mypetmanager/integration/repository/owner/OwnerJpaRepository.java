package com.mypetmanager.integration.repository.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypetmanager.integration.repository.owner.entity.Owner;
//extends HibernateRepository<Owner>, JpaRepository<Owner, Long>{
public interface OwnerJpaRepository extends JpaRepository<Owner, Long>{
	
}
