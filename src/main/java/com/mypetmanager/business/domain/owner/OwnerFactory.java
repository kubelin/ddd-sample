package com.mypetmanager.business.domain.owner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mypetmanager.business.domain.owner.vo.OwnerVO;
import com.mypetmanager.global.annotation.domain.DomainFacotry;
import com.mypetmanager.global.common.EntityMapper;
import com.mypetmanager.integration.repository.owner.OwnerRepository;
import com.mypetmanager.integration.repository.pet.PetRepository;

@DomainFacotry
public class OwnerFactory {
	
	@Autowired
	private OwnerRepository ownerRpeo;
	@Autowired
	private PetRepository petRepo;
	
	public OwnerDomain createDomain(Long ownerId) throws Exception {
		OwnerDomain ownerDomain ;
		OwnerVO ownerVo;
		
		ownerVo = ownerRpeo.masterOwnerVO(ownerId);
		ownerDomain = EntityMapper.INSTANCE.convertToOwnerDomain(ownerVo);		
		
		return ownerDomain;
	}
	
	public List<OwnerDomain> createOwnerDomainList() throws Exception {
		List<OwnerDomain> ownerDomainList ;
		List<OwnerVO> ownerVoLsit;
		
		ownerVoLsit = ownerRpeo.masterOwnerVoList();
		ownerDomainList = EntityMapper.INSTANCE.convertToOwnerDomainList(ownerVoLsit);
				
		return ownerDomainList;
	}

}
