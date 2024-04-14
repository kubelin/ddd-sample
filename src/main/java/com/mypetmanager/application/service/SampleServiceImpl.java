package com.mypetmanager.application.service;

import org.springframework.stereotype.Service;

import com.mypetmanager.business.domain.owner.OwnerFactory;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SampleServiceImpl implements SampleService {
	// factory
	final OwnerFactory ownerFactory;

	@Override
	public void saveOwner() throws Exception {
		ownerFactory.createDomain(1L);
	}

}
