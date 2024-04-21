package com.mypetmanager.application.service;

import com.mypetmanager.application.dto.OwnerRequestVO;
import com.mypetmanager.application.dto.OwnerResponseVO;

public interface PetShopService {

	public void saveOwner(OwnerRequestVO ownerId) throws Exception;

	public OwnerResponseVO findOwner(Long ownerId) throws Exception;

}
