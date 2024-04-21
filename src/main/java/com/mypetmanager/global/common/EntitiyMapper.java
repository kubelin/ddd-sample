package com.mypetmanager.global.common;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


//componentModel="spring"
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface EntitiyMapper {

	//	EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);
	//
	//	// ENTITY to VO
	//	OwnerVO convertToOwnerVO(Owner entitiy);
	//
	//	List<OwnerVO> convertToOwnerVoList(List<Owner> entitiy);
	//
	//	// VO to Domain
	//	//	@Mapping(target = "pet", ignore = true)
	//	//	OwnerDomain convertToOwnerDomain(OwnerVO ownerVo);
	//	List<OwnerDomain> convertToOwnerDomainList(List<OwnerVO> ownerVo);
	//
	//	// DTO to ENTITY
	//	Owner convertToOwner(OwnerDTO dto);
	//
	//	// DTO to domain
	//	OwnerDomain convertToOwnerDomain(OwnerVO dto);
	//
	//	// ENTITY to DTO
	//	OwnerDTO convertToOwnerDto(Owner entitiy);
	//
	//	MembershipDto convertToMembershipDto(Membership entitiy);
	//
	//	// DOMAIN to DTO
	//	OwnerDTO convertToOwnerDto(OwnerDomain entitiy);

}
