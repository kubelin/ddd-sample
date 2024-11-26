package com.mypetmanager.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * requestDTO record
 * @author kubel
 *
 */
@Getter
@Setter
@ToString
public class OwnerResponseVO {
	private String name;
	private String pass;
	private String value;

	// 기본 생성자
	public OwnerResponseVO() {}

	// 모든 필드를 포함하는 생성자
	public OwnerResponseVO(String name, String pass, String value) {
       this.name = name;
       this.pass = pass;
       this.value = value;
   }
}