package com.mypetmanager.business.domain.owner;

import org.springframework.stereotype.Component;

import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.owner.dto.OwnerDto;
import com.mypetmanager.business.domain.owner.dto.PaymentDto;
import com.mypetmanager.business.domain.owner.record.OwnerVO;
import com.mypetmanager.global.annotation.domain.RootDomain;
import com.mypetmanager.global.common.EntityMapper;
import com.mypetmanager.global.domain.OwnerRootDomain;
import com.mypetmanager.integration.repository.pet.entity.Pet;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@RootDomain("OwnerDomain")
@Getter
@Component
public class OwnerDomain extends OwnerRootDomain {
	public enum OwnerStatus {
		HAS_PET, NO_PET
	}

	@Enumerated(EnumType.STRING)
	private OwnerStatus status;

	private OwnerVO owerVo;
	private Long ownerId;
	private OwnerDto ownerDto;
	private MembershipDto membershipDto;
	private PaymentDto paymentDto;
	private Pet pet;

	public OwnerVO getMasterOwnerVO(Long ownerId) throws Exception {
		return super.getOwnerRepo().masterOwnerVO(ownerId);
	}

	public void setRepo() {
		super.setOwnerRepo();
	}

	public void getPetList() {
		System.out.println("get pet~");
	}

	public void setMembership(Long memberId) {}

	public void setOwnerInfomation(Long ownerId) {
		try {
			super.getOwnerRepo().getOwner(ownerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OwnerDto getOwnerInformation() {
		OwnerDto resultDto = EntityMapper.INSTANCE.convertToOwnerDto(this);
		return resultDto;
	}

}
