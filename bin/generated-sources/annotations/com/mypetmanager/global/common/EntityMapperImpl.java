package com.mypetmanager.global.common;

import com.mypetmanager.business.domain.owner.OwnerDomain;
import com.mypetmanager.business.domain.owner.vo.OwnerVO;
import com.mypetmanager.integration.repository.owner.dto.MembershipDTO;
import com.mypetmanager.integration.repository.owner.dto.MembershipDTO.MembershipDTOBuilder;
import com.mypetmanager.integration.repository.owner.dto.OwnerDto;
import com.mypetmanager.integration.repository.owner.dto.OwnerDto.OwnerDtoBuilder;
import com.mypetmanager.integration.repository.owner.entity.Membership;
import com.mypetmanager.integration.repository.owner.entity.Owner;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-10T23:40:43+0900",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
public class EntityMapperImpl implements EntityMapper {

    @Override
    public OwnerVO convertToOwnerVO(Owner entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        OwnerVO ownerVO = new OwnerVO();

        ownerVO.setAddress( entitiy.getAddress() );
        ownerVO.setBirthDate( entitiy.getBirthDate() );
        ownerVO.setCreatedAt( entitiy.getCreatedAt() );
        ownerVO.setEmail( entitiy.getEmail() );
        ownerVO.setName( entitiy.getName() );
        ownerVO.setOwnerId( entitiy.getOwnerId() );
        ownerVO.setPhoneNumber( entitiy.getPhoneNumber() );
        ownerVO.setUpdatedAt( entitiy.getUpdatedAt() );

        return ownerVO;
    }

    @Override
    public List<OwnerVO> convertToOwnerVoList(List<Owner> entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        List<OwnerVO> list = new ArrayList<OwnerVO>( entitiy.size() );
        for ( Owner owner : entitiy ) {
            list.add( convertToOwnerVO( owner ) );
        }

        return list;
    }

    @Override
    public OwnerDomain convertToOwnerDomain(OwnerVO ownerVo) {
        if ( ownerVo == null ) {
            return null;
        }

        OwnerDomain ownerDomain = new OwnerDomain();

        ownerDomain.setAddress( ownerVo.getAddress() );
        ownerDomain.setBirthDate( ownerVo.getBirthDate() );
        ownerDomain.setCreatedAt( ownerVo.getCreatedAt() );
        ownerDomain.setEmail( ownerVo.getEmail() );
        ownerDomain.setMembershipDto( ownerVo.getMembershipDto() );
        ownerDomain.setName( ownerVo.getName() );
        ownerDomain.setOwnerId( ownerVo.getOwnerId() );
        ownerDomain.setPhoneNumber( ownerVo.getPhoneNumber() );
        ownerDomain.setUpdatedAt( ownerVo.getUpdatedAt() );

        return ownerDomain;
    }

    @Override
    public List<OwnerDomain> convertToOwnerDomainList(List<OwnerVO> ownerVo) {
        if ( ownerVo == null ) {
            return null;
        }

        List<OwnerDomain> list = new ArrayList<OwnerDomain>( ownerVo.size() );
        for ( OwnerVO ownerVO : ownerVo ) {
            list.add( convertToOwnerDomain( ownerVO ) );
        }

        return list;
    }

    @Override
    public Owner convertToOwner(OwnerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Owner owner = new Owner();

        owner.setAddress( dto.getAddress() );
        owner.setBirthDate( dto.getBirthDate() );
        owner.setCreatedAt( dto.getCreatedAt() );
        owner.setEmail( dto.getEmail() );
        owner.setName( dto.getName() );
        owner.setOwnerId( dto.getOwnerId() );
        owner.setPhoneNumber( dto.getPhoneNumber() );
        owner.setUpdatedAt( dto.getUpdatedAt() );

        return owner;
    }

    @Override
    public OwnerDomain convertToOwnerDomain(OwnerDto dto) {
        if ( dto == null ) {
            return null;
        }

        OwnerDomain ownerDomain = new OwnerDomain();

        ownerDomain.setAddress( dto.getAddress() );
        ownerDomain.setBirthDate( dto.getBirthDate() );
        ownerDomain.setCreatedAt( dto.getCreatedAt() );
        ownerDomain.setEmail( dto.getEmail() );
        ownerDomain.setName( dto.getName() );
        ownerDomain.setOwnerId( dto.getOwnerId() );
        ownerDomain.setPhoneNumber( dto.getPhoneNumber() );
        ownerDomain.setUpdatedAt( dto.getUpdatedAt() );

        return ownerDomain;
    }

    @Override
    public OwnerDto convertToOwnerDto(Owner entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        OwnerDtoBuilder ownerDto = OwnerDto.builder();

        ownerDto.address( entitiy.getAddress() );
        ownerDto.birthDate( entitiy.getBirthDate() );
        ownerDto.createdAt( entitiy.getCreatedAt() );
        ownerDto.email( entitiy.getEmail() );
        ownerDto.name( entitiy.getName() );
        ownerDto.ownerId( entitiy.getOwnerId() );
        ownerDto.phoneNumber( entitiy.getPhoneNumber() );
        ownerDto.updatedAt( entitiy.getUpdatedAt() );

        return ownerDto.build();
    }

    @Override
    public MembershipDTO convertToMembershipDto(Membership entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        MembershipDTOBuilder membershipDTO = MembershipDTO.builder();

        membershipDTO.createdAt( entitiy.getCreatedAt() );
        membershipDTO.memberDetail( entitiy.getMemberDetail() );
        membershipDTO.memberId( entitiy.getMemberId() );
        membershipDTO.memberPoint( entitiy.getMemberPoint() );
        membershipDTO.memberType( entitiy.getMemberType() );
        membershipDTO.updatedAt( entitiy.getUpdatedAt() );

        return membershipDTO.build();
    }

    @Override
    public OwnerDto convertToOwnerDto(OwnerDomain entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        OwnerDtoBuilder ownerDto = OwnerDto.builder();

        ownerDto.address( entitiy.getAddress() );
        ownerDto.birthDate( entitiy.getBirthDate() );
        ownerDto.createdAt( entitiy.getCreatedAt() );
        ownerDto.email( entitiy.getEmail() );
        ownerDto.name( entitiy.getName() );
        ownerDto.ownerId( entitiy.getOwnerId() );
        ownerDto.phoneNumber( entitiy.getPhoneNumber() );
        ownerDto.updatedAt( entitiy.getUpdatedAt() );

        return ownerDto.build();
    }
}
