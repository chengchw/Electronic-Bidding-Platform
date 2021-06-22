package com.chengchw.SHEA.Repository;

import org.springframework.data.repository.CrudRepository;

import com.chengchw.SHEA.Model.Guest;

import java.util.List;



public  interface GuestRepository extends CrudRepository<Guest,Long> {
	
	List<Guest> findAll(); 
	
	boolean existsByEmail(String email);
	
	Guest findByEmail(String email);
	
	Guest findGuestById(Long Id);

}
