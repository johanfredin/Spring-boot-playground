package com.pylonmusic.playground.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.pylonmusic.playground.domain.AbstractEntity;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

	int DEFAULT_MAX_RESULTS = 10;
	
	@Query("select count (#{#entityName}) > 0 from #{#entityName} e where :pName = :pVal")
	boolean isPropertyUnique(@Param("pName") String pName, @Param("pVal") Object pVal);
	
	@Query("select count (#{#entityName}) > 0 from #{#entityName} e where :pName = :pVal and e.id != :id")
	boolean isNoOtherEntityWithProperty(@Param("id") long id, @Param("pName") String pName, @Param("pVal") Object pVal);
	
	/**
	 * The "First" keyword in method name is a hint to Spring to return only one result
	 * @param pName
	 * @param pVal
	 * @return
	 */
	@Query("select id from #{#entityName} e where :pName = :pVal")
	T getFirstEntityMatchingProperty(@Param("pName") String pName, @Param("pVal") Object pVal);
	
	@Query("select id from #{#entityName} e where :pName = :pVal")
	List<T> getEntitiesMatchingProperty(@Param("pName") String pName, @Param("pVal") Object pVal);
	
}
