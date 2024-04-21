package com.mypetmanager.business.domain;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.mypetmanager.application.dto.OwnerRequestVO;
import com.mypetmanager.business.domain.owner.OwnerDomain;
import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.owner.dto.OwnerDTO;
import com.mypetmanager.business.domain.owner.record.OwnerVO;
import com.mypetmanager.integration.repository.owner.entity.Membership;
import com.mypetmanager.integration.repository.owner.entity.Owner;

//componentModel="spring"
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface DomainMapper {

	DomainMapper INSTANCE = Mappers.getMapper(DomainMapper.class);

	// ENTITY to VO
	OwnerVO convertToOwnerVO(Owner entitiy);

	List<OwnerVO> convertToOwnerVoList(List<Owner> entitiy);

	// VO to Domain
	//	@Mapping(target = "pet", ignore = true)
	//	OwnerDomain convertToOwnerDomain(OwnerVO ownerVo);
	List<OwnerDomain> convertToOwnerDomainList(List<OwnerVO> ownerVo);

	// DTO to ENTITY
	Owner convertToOwner(OwnerDTO dto);

	// DTO to domain
	OwnerDomain convertToOwnerDomain(OwnerVO dto);

	// RequstRecrodVO to DTO
	@Mapping(source = "ownerId", target = "ownerId")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "email", target = "email")
	OwnerDTO convertRecordToOwnerDto(OwnerRequestVO vo);

	// ENTITY to DTO
	OwnerDTO convertToOwnerDto(Owner entitiy);

	MembershipDto convertToMembershipDto(Membership entitiy);

	// DOMAIN to DTO
	OwnerDTO convertToOwnerDto(OwnerDomain entitiy);

}
