package com.mypetmanager.integration.repository.owner.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MembershipDTO {
    private Long memberId;
    private String memberType;
    private String memberDetail;
    private int memberPoint;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @Builder
	public MembershipDTO(Long memberId, String memberType, String memberDetail, int memberPoint,
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
