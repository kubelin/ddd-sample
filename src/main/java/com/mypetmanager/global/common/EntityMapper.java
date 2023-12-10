package com.mypetmanager.global.common;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.mypetmanager.business.domain.owner.OwnerDomain;
import com.mypetmanager.business.domain.owner.vo.OwnerVO;
import com.mypetmanager.integration.repository.owner.dto.MembershipDTO;
import com.mypetmanager.integration.repository.owner.dto.OwnerDto;
import com.mypetmanager.integration.repository.owner.entity.Membership;
import com.mypetmanager.integration.repository.owner.entity.Owner;

//componentModel="spring"
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, 
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EntityMapper {
	
	EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);
	
	
	// ENTITY to VO
	OwnerVO convertToOwnerVO(Owner entitiy);
	List<OwnerVO> convertToOwnerVoList(List<Owner> entitiy);
	// VO to Domain
	OwnerDomain convertToOwnerDomain(OwnerVO ownerVo);
	List<OwnerDomain> convertToOwnerDomainList(List<OwnerVO> ownerVo);
	
	
	// DTO to ENTITY
	Owner convertToOwner(OwnerDto dto);
	// DTO to domain
	OwnerDomain convertToOwnerDomain(OwnerDto dto);
	
	// ENTITY to DTO
	OwnerDto convertToOwnerDto(Owner entitiy);
	MembershipDTO convertToMembershipDto(Membership entitiy);
	
	// DOMAIN to DTO
	OwnerDto convertToOwnerDto(OwnerDomain entitiy);
	
	
}
