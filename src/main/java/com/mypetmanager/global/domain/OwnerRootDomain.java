package com.mypetmanager.global.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mypetmanager.global.common.ApplicationContextProvider;
import com.mypetmanager.integration.repository.owner.OwnerRepository;

import jakarta.persistence.MappedSuperclass;

/**
 * 
 * @author kubel
 *
 */
@Component
@Scope("prototype")
@MappedSuperclass
public abstract class OwnerRootDomain {
	public static enum AggregateStatus {
		ACTIVE, ARCHIVE
	}
	
    public OwnerRootDomain() {
        setOwnerRepo();
    }
    
	private OwnerRepository anotherRepository;

	public OwnerRepository getOwnerRepo() {
		return anotherRepository;
	}
	
	protected void setOwnerRepo() {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		if( applicationContext != null ) {
			this.anotherRepository = applicationContext.getBean(OwnerRepository.class);	
		}
	}
	
}
