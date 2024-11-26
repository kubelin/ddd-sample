package com.mypetmanager.business.domain.owner.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This class represents a Membership data transfer object (DTO). It contains information about a pet owner's membership.
 *
 * @author YourName
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public class MembershipDto {
	private Long memberId;
	private String memberType;
	private String memberDetail;
	private int memberPoint;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@Builder
	public MembershipDto(Long memberId, String memberType, String memberDetail, int memberPoint,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.memberId = memberId;
		this.memberType = memberType;
		this.memberDetail = memberDetail;
		this.memberPoint = memberPoint;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "MembershipDTO [memberId=" + memberId + ", memberType=" + memberType + ", memberDetail=" + memberDetail
				+ ", memberPoint=" + memberPoint + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
