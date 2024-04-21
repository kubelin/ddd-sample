package com.mypetmanager.business.domain.owner;

import java.util.List;

import com.mypetmanager.business.domain.DomainMapper;
import com.mypetmanager.business.domain.owner.record.OwnerVO;
import com.mypetmanager.global.annotation.domain.DomainFacotry;
import com.mypetmanager.integration.repository.owner.OwnerRepository;
import com.mypetmanager.integration.repository.pet.PetRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@DomainFacotry("OwnerFactory")
public class OwnerFactory {

	private OwnerRepository ownerRpeo;
	private PetRepository petRepo;
	//	protected EntityManager entityManager;
	//public String targetRootDomain = "OwnerDomain";

	/**
	 * 단건의 오너도메인 생성
	 *
	 * @param
	 * @return
	 * @author kubel
	 * 2024. 4. 20.
	 */
	public OwnerDomain createDomain(Long ownerId) throws Exception {
		OwnerDomain ownerDomain;
		OwnerVO ownerVo;

		ownerVo = ownerRpeo.findOwnerVO(ownerId);
		//		loadAnnotatedClassz(ownerDomain, ownerId);
		//		Owner owner = new Owner();
		//		getEntities(Owner.class);
		if (ownerVo != null) {
			ownerDomain = DomainMapper.INSTANCE.convertToOwnerDomain(ownerVo);
		}else {
			ownerDomain = new OwnerDomain();
		}

		return ownerDomain;
	}

	/**
	 * 다건의 오너도메인 생성
	 *
	 * @param
	 * @return
	 * @author kubel
	 * 2024. 4. 20.
	 */
	public List<OwnerDomain> createOwnerDomainList() throws Exception {
		List<OwnerDomain> ownerDomainList;
		List<OwnerVO> ownerVoLsit;

		ownerVoLsit = ownerRpeo.masterOwnerVoList();
		ownerDomainList = DomainMapper.INSTANCE.convertToOwnerDomainList(ownerVoLsit);

		return ownerDomainList;
	}

	/*
		public void loadAnnotatedClassz(OwnerDomain ownerDomain, Long ownerId) {
			ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
			provider.addIncludeFilter(new AnnotationTypeFilter(SubDomain.class));

			Set<BeanDefinition> beanDefs = provider.findCandidateComponents("com.mypetmanager.integration.repository");

			beanDefs.forEach(beanDefinition -> {
				AnnotatedBeanDefinition annoBean = (AnnotatedBeanDefinition)beanDefinition;
				AnnotationMetadata meta = annoBean.getMetadata();

				var tempMap = meta.getAnnotationAttributes(SubDomain.class.getName());
				if (targetRootDomain.equals(tempMap.get("value"))) {
					log.info("ready to load entity");
					try {
						var tempClass = Class.forName(beanDefinition.getBeanClassName());
						entityManager.find(tempClass, ownerId);

					} catch (ClassNotFoundException e) {
						// raise Exception
						e.printStackTrace();
					}

				}
			});
		}

		public List<Object> getEntities(Object entityClass) {
			Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

			for (EntityType<?> entityType : entities) {
				String entityName = entityType.getName();
				System.out.println("Entity Name: " + entityName);
			}
			return null;
		}
	*/
}
