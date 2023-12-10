package com.mypetmanager.integration.repository.owner;

import java.util.List;

import com.mypetmanager.business.domain.owner.vo.OwnerVO;
import com.mypetmanager.integration.repository.owner.dto.MembershipDTO;
import com.mypetmanager.integration.repository.owner.dto.OwnerDto;

public interface OwnerRepository {
	
	public OwnerDto getOwner(Long ownerId) throws Exception;
	
	public List<OwnerDto> getOwnerList() throws Exception;
	
	public MembershipDTO getMembershipVO(Long memberId) throws Exception;
	
	public void saveOwner(OwnerDto ownerDto) throws Exception;
	
	public OwnerVO masterOwnerVO(Long ownerId) throws Exception;
	
	public List<OwnerVO> masterOwnerVoList() throws Exception;

	public void updateOwner(OwnerDto ownerDto) throws Exception;
	
}
