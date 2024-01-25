package com.mypetmanager.business.domain.owner.dto;

import java.time.LocalDateTime;

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

}