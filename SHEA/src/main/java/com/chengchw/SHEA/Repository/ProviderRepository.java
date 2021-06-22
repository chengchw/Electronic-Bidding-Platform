package com.chengchw.SHEA.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chengchw.SHEA.Model.Provider;


@Repository
public interface ProviderRepository extends CrudRepository<Provider,Long> {

	List<Provider> findAll(); 
	
	boolean existsByEmail(String email);
	
	Provider findByEmail(String email);
	
	Provider findProviderById(Long Id);
	
	
}
