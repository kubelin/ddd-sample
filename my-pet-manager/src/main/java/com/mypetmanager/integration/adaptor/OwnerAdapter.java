package com.mypetmanager.integration.adaptor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mypetmanager.business.domain.owner.vo.OwnerVO;
import com.mypetmanager.global.common.EntityMapper;
import com.mypetmanager.global.infra.GenericJpaAdapter;
import com.mypetmanager.integration.repository.owner.MembershipJpaRepository;
import com.mypetmanager.integration.repository.owner.OwnerJpaRepository;
import com.mypetmanager.integration.repository.owner.OwnerRepository;
import com.mypetmanager.integration.repository.owner.dto.MembershipDTO;
import com.mypetmanager.integration.repository.owner.dto.OwnerDto;
import com.mypetmanager.integration.repository.owner.entity.Membership;
import com.mypetmanager.integration.repository.owner.entity.Owner;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class OwnerAdapter extends GenericJpaAdapter implements OwnerRepository {
	final private OwnerJpaRepository ownerJpaRepo;
	final private MembershipJpaRepository memberJpaRepo;

	@Override
	public OwnerVO masterOwnerVO(Long ownerId) throws Exception {
		OwnerVO returnVO;
		MembershipDTO membershipDto;
		Owner owner;
		Membership memberEntitiy;

		owner = ownerJpaRepo.findById(ownerId).orElseThrow(() -> new Exception("null"));
		System.out.println(owner);
		returnVO = EntityMapper.INSTANCE.convertToOwnerVO(owner);
		
		memberEntitiy = memberJpaRepo.findById(ownerId).orElseThrow(() -> new Exception("null"));
		membershipDto = EntityMapper.INSTANCE.convertToMembershipDto(memberEntitiy);
		
		returnVO.setMembershipDto(membershipDto);

		return returnVO;
	}
	
	@Override
	public List<OwnerVO> masterOwnerVoList() throws Exception {
		List<OwnerVO> returnVO;
		MembershipDTO membershipDto;
		List<Owner> ownerList;
		Membership memberEntitiy;

		ownerList = ownerJpaRepo.findAll();
		System.out.println(ownerList);
		returnVO = EntityMapper.INSTANCE.convertToOwnerVoList(ownerList);
		
		//memberEntitiy = memberJpaRepo.findById(ownerId).orElseThrow(() -> new Exception("null"));
		//membershipDto = EntityMapper.INSTANCE.convertToMembershipDto(memberEntitiy);
		
		//returnVO.setMembershipDto(membershipDto);

		return returnVO;
	}
	

	@Override
	public OwnerDto getOwner(Long ownerId) throws Exception {
		System.out.println("getOwner");

		Owner owner = ownerJpaRepo.findById(ownerId).orElseThrow(() -> new Exception("null"));
		OwnerDto resultDto = EntityMapper.INSTANCE.convertToOwnerDto(owner);

		System.out.println(resultDto);

		return resultDto;
	}

	@Override
	public MembershipDTO getMembershipVO(Long memberId) throws Exception {
		Membership memberEntitiy = memberJpaRepo.findById(memberId).orElseThrow(() -> new Exception("null"));
		MembershipDTO resultVO = EntityMapper.INSTANCE.convertToMembershipDto(memberEntitiy);

		return resultVO;
	}

	@Override
	public void saveOwner(OwnerDto ownerDto) throws Exception {
		Owner owner = ownerJpaRepo.findById(ownerDto.getOwnerId()).orElse(null);
		if( owner == null ) {
			System.out.println( " data not exist ! " );
			ownerJpaRepo.save(owner);	
		}else {
			System.out.println( " data already exist ! " );
		}
	}

	@Override
	public void updateOwner(OwnerDto ownerDto) throws Exception {
		// Entity
		Owner owner = super.findEntity( Owner.class, ownerDto.getOwnerId() );
		if( owner != null ) {
			// set DTO's value to Entity's field
			super.setDtoToEntity(owner, ownerDto);
			ownerJpaRepo.save(owner);
		}
	}

	@Override
	public List<OwnerDto> getOwnerList() throws Exception {
		
		return null;
	}
	
}
