package com.mypetmanager.application.service;

import org.springframework.stereotype.Service;

import com.mypetmanager.application.dto.OwnerRequestVO;
import com.mypetmanager.application.dto.OwnerResponseVO;
import com.mypetmanager.business.domain.owner.OwnerDomain;
import com.mypetmanager.business.domain.owner.OwnerFactory;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements PetShopService {
	// factory
	final OwnerFactory ownerFactory;

	@Override
	@Transactional
	public void saveOwner(OwnerRequestVO ownerRequestVO) throws Exception {

		// doamin 생성
		OwnerDomain ownerDomain = ownerFactory.createDomain(ownerRequestVO.ownerId());
		// save owner
		ownerDomain.saveOwner(ownerRequestVO);

	}

	@Override
	public OwnerResponseVO findOwner(Long ownerId) throws Exception {

		// doamin 생성
		OwnerDomain ownerDomain = ownerFactory.createDomain(ownerId);

		return null;
	}

}
