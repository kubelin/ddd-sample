package com.mypetmanager.integration.repository.owner;

import java.util.List;

import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.owner.dto.OwnerDTO;
import com.mypetmanager.business.domain.owner.record.OwnerVO;

public interface OwnerRepository {

	public OwnerDTO getOwner(Long ownerId) throws Exception;

	public List<OwnerDTO> getOwnerList() throws Exception;

	public MembershipDto getMembershipVO(Long memberId) throws Exception;

	public void saveOwner(OwnerDTO ownerDto) throws Exception;

	public OwnerVO findOwnerVO(Long ownerId) throws Exception;

	public List<OwnerVO> masterOwnerVoList() throws Exception;

	public void updateOwner(OwnerDTO ownerDto) throws Exception;

}
