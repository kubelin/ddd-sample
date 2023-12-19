package com.mypetmanager.business.domain.owner;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.mypetmanager.business.domain.owner.vo.OwnerVO;
import com.mypetmanager.global.annotation.domain.RootDomain;
import com.mypetmanager.global.common.EntityMapper;
import com.mypetmanager.global.domain.OwnerRootDomain;
import com.mypetmanager.integration.repository.owner.dto.MembershipDTO;
import com.mypetmanager.integration.repository.owner.dto.OwnerDto;
import com.mypetmanager.integration.repository.pet.entity.Pet;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@RootDomain
@Setter
@Getter
@Component
public class OwnerDomain extends OwnerRootDomain{
	//private OwnerRepository ownerRpeo;
	
	public enum OwnerStatus{
		HAS_PET, NO_PET
	}
	@Enumerated(EnumType.STRING)
	private OwnerStatus status;
	private OwnerVO owerVo;
	
	private Long ownerId;
	private String name;
	private String birthDate;
	private String email;
	private String phoneNumber;
	private String address;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	private MembershipDTO membershipDto;
	
	
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
	
	public void setMembership(Long memberId) {
	}
	
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
	
	@Override
	public String toString() {
		return "OwnerDomain [ownerRpeo=" + super.getOwnerRepo() + ", status=" + status + ", ownerId=" + ownerId + ", name=" + name
				+ ", birthDate=" + birthDate + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", membershipVO=" + membershipDto
				+ ", petLits=" + pet + "]";
	}
	
	
}
