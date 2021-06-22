package com.chengchw.SHEA.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.chengchw.SHEA.Model.Product;


public interface ProductRepository extends CrudRepository<Product,Long> {
	
	List<Product> findAll();
	
	List<Product> findByCategory(String category);
	
	List<Product> findByState(String category);
	
	Optional<Product> findById(Long Id);
	
	List<Product> findByCurrentBidId(Long Id);
	
	List<Product> findByCurrentBidIdNot(Long Id);
	

}
