package com.mypetmanager.global.infra;

import java.lang.reflect.Field;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;

/**
 * 
 * @author kubel
 *
 */
public abstract class GenericJpaAdapter{
    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericJpaAdapter() {}

    /**
     * findEntity
     * @param
     * @return
     * @author kubel
     * 2023. 12. 08.
     */
    public <T> T findEntity(Class<?> entityClassz, Object primaryKey) {
    	
    	@SuppressWarnings("unchecked")
		T returnEntity = (T) entityManager.find(entityClassz, primaryKey, LockModeType.PESSIMISTIC_WRITE);
    	// no data for update
    	// you can choose whether insert or update with null or not
    	// in this case, return null
    	if (returnEntity == null) {
    		return null;
    		//throw new RuntimeException("returnEntity " + entityClassz.getCanonicalName() + " id = " + primaryKey + " does not exist");
    	}
    	return returnEntity;
    }
    
    /**
     * isContained
     * @param
     * @return
     * @author kubel
     * 2023. 12. 10.
     */
	public boolean isContained(Object entityClassz) {
		return entityManager.contains(entityClassz);
	}
    
	/**
	 * delete
	 * @param
	 * @return
	 * @author kubel
	 * 2023. 12. 10.
	 */
    public void delete(){
		//Not implemented
		//just flag
		//entity.markAsRemoved();					
	}
    
    /**
     * setDtoToEntity
     * @param
     * @return
     * @author kubel
     * 2023. 12. 08.
     */
	 public void setDtoToEntity(Object entity, Object dto) throws IllegalAccessException {		 
	        for (Field field : dto.getClass().getDeclaredFields()) {
	            field.setAccessible(true);
	            Object value = field.get(dto);
	            if (value != null) {
	                Field entityField;
	                try {
	                    entityField = entity.getClass().getDeclaredField(field.getName());
	                    entityField.setAccessible(true);
	                    entityField.set(entity, value);
	                } catch (NoSuchFieldException e) {
	                	// 에러처리
	                    e.printStackTrace();
	                }
	            }
	        }
	 }
	 
}
