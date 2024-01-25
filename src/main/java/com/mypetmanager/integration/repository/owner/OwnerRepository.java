package com.mypetmanager.integration.repository.owner;

import java.util.List;

import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.owner.dto.OwnerDto;
import com.mypetmanager.business.domain.owner.record.OwnerVO;

public interface OwnerRepository {
	
	public OwnerDto getOwner(Long ownerId) throws Exception;
	
	public List<OwnerDto> getOwnerList() throws Exception;
	
	public MembershipDto getMembershipVO(Long memberId) throws Exception;
	
	public void saveOwner(OwnerDto ownerDto) throws Exception;
	
public OwnerVO masterOwnerVO(Long ownerId) throws Exception;
	
	public List<OwnerVO> masterOwnerVoList() throws Exception;

	public void updateOwner(OwnerDto ownerDto) throws Exception;
	
}
