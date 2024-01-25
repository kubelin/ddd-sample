package com.mypetmanager.business.domain.owner;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.mypetmanager.business.domain.owner.record.OwnerVO;
import com.mypetmanager.global.annotation.domain.DomainFacotry;
import com.mypetmanager.global.annotation.domain.SubDomain;
import com.mypetmanager.integration.repository.owner.OwnerRepository;
import com.mypetmanager.integration.repository.owner.entity.Owner;
import com.mypetmanager.integration.repository.pet.PetRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainFacotry("OwnerFactory")
public class OwnerFactory {

	@Autowired
	private OwnerRepository ownerRpeo;
	@Autowired
	private PetRepository petRepo;
	@PersistenceContext
	protected EntityManager entityManager;

	private String targetRootDomain = "OwnerDomain";

	public OwnerDomain createDomain(Long ownerId) throws Exception {
		OwnerDomain ownerDomain = null;
		OwnerVO ownerVo;

		//ownerVo = ownerRpeo.masterOwnerVO(ownerId);
//		loadAnnotatedClassz(ownerDomain, ownerId);
		//Owner ow = new Owner();
		getEntities(Owner.class);
//		ownerDomain = EntityMapper.INSTANCE.convertToOwnerDomain(ownerVo);

//		return ownerDomain;
		return ownerDomain;
	}

	public List<OwnerDomain> createOwnerDomainList() throws Exception {
		List<OwnerDomain> ownerDomainList;
		List<OwnerVO> ownerVoLsit;

		ownerVoLsit = ownerRpeo.masterOwnerVoList();
		//		ownerDomainList = EntityMapper.INSTANCE.convertToOwnerDomainList(ownerVoLsit);
		//
		//		return ownerDomainList;
		return null;
	}

	public void loadAnnotatedClassz(OwnerDomain ownerDomain, Long ownerId) {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(SubDomain.class));

		Set<BeanDefinition> beanDefs = provider.findCandidateComponents("com.mypetmanager.integration.repository");

		beanDefs.forEach(beanDefinition -> {
			AnnotatedBeanDefinition annoBean = (AnnotatedBeanDefinition) beanDefinition;
			AnnotationMetadata meta = annoBean.getMetadata();

			var tempMap = meta.getAnnotationAttributes(SubDomain.class.getName());
			if (targetRootDomain.equals(tempMap.get("value"))) {
				log.info("ready to load entity");
				try {
					var tempClass = Class.forName(beanDefinition.getBeanClassName());
					entityManager.find(tempClass, ownerId);

//					ownerDomain.setOwnerDto(null);
//					ownerDomain.setPaymentDto(null);
//					ownerDomain.setMembershipDto(null);

				} catch (ClassNotFoundException e) {
					// raise Exception
					e.printStackTrace();
				}


			}
		});
	}

	public List<Object> getEntities(Object entityClass){
		System.out.println(" result = " );
//		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
//
//		for (EntityType<?> entityType : entities) {
//		    String entityName = entityType.getName();
//		    System.out.println("Entity Name: " + entityName);
//		}
		return null;
	}

}
