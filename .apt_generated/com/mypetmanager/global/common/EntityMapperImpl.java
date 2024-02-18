package com.mypetmanager.global.common;

import com.mypetmanager.business.domain.owner.OwnerDomain;
import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.owner.dto.MembershipDto.MembershipDtoBuilder;
import com.mypetmanager.business.domain.owner.dto.OwnerDto;
import com.mypetmanager.business.domain.owner.dto.OwnerDto.OwnerDtoBuilder;
import com.mypetmanager.business.domain.owner.record.OwnerVO;
import com.mypetmanager.integration.repository.owner.entity.Membership;
import com.mypetmanager.integration.repository.owner.entity.Owner;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T02:20:12+0900",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class EntityMapperImpl implements EntityMapper {

    @Override
    public OwnerVO convertToOwnerVO(Owner entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        OwnerVO ownerVO = new OwnerVO();

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
    public List<OwnerDomain> convertToOwnerDomainList(List<OwnerVO> ownerVo) {
        if ( ownerVo == null ) {
            return null;
        }

        List<OwnerDomain> list = new ArrayList<OwnerDomain>( ownerVo.size() );
        for ( OwnerVO ownerVO : ownerVo ) {
            list.add( ownerVOToOwnerDomain( ownerVO ) );
        }

        return list;
    }

    @Override
    public Owner convertToOwner(OwnerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Owner owner = new Owner();

        return owner;
    }

    @Override
    public OwnerDomain convertToOwnerDomain(OwnerDto dto) {
        if ( dto == null ) {
            return null;
        }

        OwnerDomain ownerDomain = new OwnerDomain();

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
    public MembershipDto convertToMembershipDto(Membership entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        MembershipDtoBuilder membershipDto = MembershipDto.builder();

        membershipDto.createdAt( entitiy.getCreatedAt() );
        membershipDto.memberDetail( entitiy.getMemberDetail() );
        membershipDto.memberId( entitiy.getMemberId() );
        if ( entitiy.getMemberPoint() != null ) {
            membershipDto.memberPoint( Integer.parseInt( entitiy.getMemberPoint() ) );
        }
        membershipDto.memberType( entitiy.getMemberType() );
        membershipDto.updatedAt( entitiy.getUpdatedAt() );

        return membershipDto.build();
    }

    @Override
    public OwnerDto convertToOwnerDto(OwnerDomain entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        OwnerDtoBuilder ownerDto = OwnerDto.builder();

        ownerDto.membershipDto( entitiy.getMembershipDto() );
        ownerDto.ownerId( entitiy.getOwnerId() );

        return ownerDto.build();
    }

    protected OwnerDomain ownerVOToOwnerDomain(OwnerVO ownerVO) {
        if ( ownerVO == null ) {
            return null;
        }

        OwnerDomain ownerDomain = new OwnerDomain();

        return ownerDomain;
    }
}
