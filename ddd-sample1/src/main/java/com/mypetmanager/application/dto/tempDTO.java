package com.mypetmanager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class tempDTO {
	private String name;
	private String pass;
	private final tempDTO2 tempDto2;
	
}
