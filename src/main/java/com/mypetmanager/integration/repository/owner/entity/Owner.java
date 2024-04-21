package com.mypetmanager.integration.repository.owner.entity;
import java.sql.Timestamp;

import com.mypetmanager.global.annotation.domain.SubDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@SubDomain("OwnerDomain")
@Entity
@Getter
@Setter
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public void setOnlyName() {

    }

	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", name=" + name + ", birthDate=" + birthDate + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}



}
