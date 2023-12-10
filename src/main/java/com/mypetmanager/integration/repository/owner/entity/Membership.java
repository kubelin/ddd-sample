package com.mypetmanager.integration.repository.owner.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberType;
    private String memberDetail;
    private int memberPoint;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	@Override
	public String toString() {
		return "Membership [memberId=" + memberId + ", memberType=" + memberType + ", memberDetail=" + memberDetail
				+ ", memberPoint=" + memberPoint + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

    // Getters and setters
    
}
