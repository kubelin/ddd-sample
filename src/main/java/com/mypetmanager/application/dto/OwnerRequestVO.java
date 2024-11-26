package com.mypetmanager.application.dto;

/**
 * description: This class represents the owner request data transfer object (DTO) for owner records.
 * requestDTO record
 * @author kubel
 *
 */
public record OwnerRequestVO(Long ownerId, String name, String pass, String email, String value) {

}
