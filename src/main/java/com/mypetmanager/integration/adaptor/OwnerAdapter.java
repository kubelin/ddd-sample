package com.mypetmanager.integration.adaptor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mypetmanager.business.domain.DomainMapper;
import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.owner.dto.OwnerDTO;
import com.mypetmanager.business.domain.owner.record.OwnerVO;
import com.mypetmanager.global.infra.GenericJpaAdapter;
import com.mypetmanager.integration.repository.owner.MembershipJpaRepository;
import com.mypetmanager.integration.repository.owner.OwnerJpaRepository;
import com.mypetmanager.integration.repository.owner.OwnerRepository;
import com.mypetmanager.integration.repository.owner.entity.Membership;
import com.mypetmanager.integration.repository.owner.entity.Owner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@AllArgsConstructor
public class OwnerAdapter extends GenericJpaAdapter implements OwnerRepository {
	final private OwnerJpaRepository ownerJpaRepo;
	final private MembershipJpaRepository memberJpaRepo;

	@Override
	public OwnerVO findOwnerVO(Long ownerId) throws Exception {
		OwnerVO returnVO;
		MembershipDto membershipDto;
		Owner owner;
		Membership memberEntitiy;

		owner = ownerJpaRepo.findById(ownerId).orElse(null);
		returnVO = DomainMapper.INSTANCE.convertToOwnerVO(owner);

		if (owner != null) {
			memberEntitiy = memberJpaRepo.findById(ownerId).orElse(null);
			membershipDto = DomainMapper.INSTANCE.convertToMembershipDto(memberEntitiy);

			returnVO.setMembership(membershipDto);
		}

		return returnVO;
	}

	@Override
	public List<OwnerVO> masterOwnerVoList() throws Exception {
		List<OwnerVO> returnVO;
		MembershipDto membershipDto;
		List<Owner> ownerList;
		Membership memberEntitiy;

		ownerList = ownerJpaRepo.findAll();
		System.out.println(ownerList);
		returnVO = DomainMapper.INSTANCE.convertToOwnerVoList(ownerList);

		//		memberEntitiy = memberJpaRepo.findById(ownerId).orElseThrow(() -> new Exception("null"));
		//membershipDto = EntityMapper.INSTANCE.convertToMembershipDto(memberEntitiy);

		//returnVO.setMembershipDto(membershipDto);

		return returnVO;
	}


	@Override
	public OwnerDTO getOwner(Long ownerId) throws Exception {
		System.out.println("getOwner");

		Owner owner = ownerJpaRepo.findById(ownerId).orElseThrow(() -> new Exception("null"));
		OwnerDTO resultDto = DomainMapper.INSTANCE.convertToOwnerDto(owner);

		System.out.println(resultDto);

		return resultDto;
	}

	@Override
	public MembershipDto getMembershipVO(Long memberId) throws Exception {
		Membership memberEntitiy = memberJpaRepo.findById(memberId).orElseThrow(() -> new Exception("null"));
		MembershipDto resultVO = DomainMapper.INSTANCE.convertToMembershipDto(memberEntitiy);

		return resultVO;
	}

	@Override
	public void saveOwner(OwnerDTO ownerDto) throws Exception {
		Owner owner = ownerJpaRepo.findById(ownerDto.getOwnerId()).orElse(null);
		if( owner == null ) {
			// 변환 후 저장
			owner = DomainMapper.INSTANCE.convertToOwner(ownerDto);
			log.info(" ownerDto data save {}", ownerDto);
			log.info(" owner data save {}", owner);
			ownerJpaRepo.save(owner);

		}else {
			// 업데이트
			log.info(" data already exist ! ");
		}
	}

	@Override
	public void updateOwner(OwnerDTO ownerDto) throws Exception {
		// Entity
		Owner owner = findEntity( Owner.class, ownerDto.getOwnerId() );
		if( owner != null ) {
			// set DTO's value to Entity's field
			super.setDtoToEntity(owner, ownerDto);
			ownerJpaRepo.save(owner);
		}
	}

	@Override
	public List<OwnerDTO> getOwnerList() throws Exception {

		return null;
	}

}
