package com.mypetmanager.integration.repository.owner.entity;
import java.time.LocalDateTime;

import com.mypetmanager.global.annotation.domain.SubDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SubDomain("OwnerDomain")
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private String payType;

    private String payDetail;

    private String payState;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	@Override
	public String toString() {
		return "Payment [payId=" + payId + ", owner=" + owner + ", payType=" + payType + ", payDetail=" + payDetail
				+ ", payState=" + payState + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

    // Getters and setters
    
}