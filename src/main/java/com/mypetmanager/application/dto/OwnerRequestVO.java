package com.mypetmanager.application.dto;

/**
 * requestDTO record
 * @author kubel
 *
 */
public record OwnerRequestVO(Long ownerId, String name, String pass, String email, String value) {
}