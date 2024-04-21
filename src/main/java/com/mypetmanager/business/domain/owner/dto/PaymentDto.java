package com.mypetmanager.business.domain.owner.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDto {
	private Long payId;
	private String payType;
	private String payDetail;
	private String payState;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@Builder
	public PaymentDto(Long payId, String payType, String payDetail, String payState, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		super();
		this.payId = payId;
		this.payType = payType;
		this.payDetail = payDetail;
		this.payState = payState;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "PaymentDto [payId=" + payId + ", payType=" + payType + ", payDetail=" + payDetail + ", payState="
			+ payState + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}