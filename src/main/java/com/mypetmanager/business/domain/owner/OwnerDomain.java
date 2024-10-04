package com.mypetmanager.business.domain.owner;

import java.util.HashMap;

import org.springframework.boot.actuate.autoconfigure.tracing.TracingProperties.Baggage.Correlation;
import org.springframework.stereotype.Component;

import com.mypetmanager.application.dto.OwnerRequestVO;
import com.mypetmanager.business.domain.DomainMapper;
import com.mypetmanager.business.domain.owner.dto.MembershipDto;
import com.mypetmanager.business.domain.owner.dto.OwnerDTO;
import com.mypetmanager.business.domain.owner.dto.PaymentDto;
import com.mypetmanager.business.domain.owner.record.OwnerVO;
import com.mypetmanager.business.domain.pet.PetDomain;
import com.mypetmanager.global.annotation.domain.RootDomain;
import com.mypetmanager.global.domain.OwnerRootDomain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RootDomain("OwnerDomain")
@Slf4j
@Getter
@Component
public class OwnerDomain extends OwnerRootDomain {

	public enum OwnerStatus {
		HAS_PET, NO_PET
	}

	Correlation tt = new Correlation();

	@Enumerated(EnumType.STRING)
	private OwnerStatus status;

	private OwnerVO owerVo;
	private Long ownerId;
	private OwnerDTO ownerDto;
	private MembershipDto membershipDto;
	private PaymentDto paymentDto;
	private PetDomain pet;

	public OwnerVO findOwnerVO(Long ownerId) throws Exception {
		return super.getOwnerRepo().findOwnerVO(ownerId);
	}

	public void saveOwner(OwnerRequestVO ownerVO) throws Exception {
		// ownerVO 변환 -> DTO
		// 변수 수정이 필요할 경우
		OwnerDTO ownerDataDto = DomainMapper.INSTANCE.convertRecordToOwnerDto(ownerVO);

		HashMap<String, String> test = new HashMap<>();
		test.put("1", "2");
		test.put("2", "3");
		test.put("3", "4");

		for (String key : test.keySet()) {
			System.out.println(" resutl : " + test.get(key));
		}

		log.info("doaminMapper ownerVO => {} ", ownerVO);
		log.info("doaminMapper ownerDto => {} ", ownerDataDto);

		super.getOwnerRepo().saveOwner(ownerDataDto);
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

	public OwnerDTO getOwnerInformation() {
		OwnerDTO resultDto = DomainMapper.INSTANCE.convertToOwnerDto(this);
		return resultDto;
	}

}
