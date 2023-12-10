package com.mypetmanager.integration.repository.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypetmanager.integration.repository.owner.entity.Membership;

public interface MembershipJpaRepository extends JpaRepository<Membership, Long>{
	
}
