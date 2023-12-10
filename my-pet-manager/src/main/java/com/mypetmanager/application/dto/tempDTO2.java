package com.mypetmanager.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class tempDTO2 {
	private String id;	
	
	public tempDTO2(String id) {
		this.id  = id;
	}
	
	public static tempDTO2 of(String id) {
		return new tempDTO2(id);
	}
}
