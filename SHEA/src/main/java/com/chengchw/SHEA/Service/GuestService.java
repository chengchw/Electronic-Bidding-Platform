package com.chengchw.SHEA.Service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


import com.chengchw.SHEA.Model.Guest;
import com.chengchw.SHEA.Model.Provider;
import com.chengchw.SHEA.Repository.GuestRepository;

@Service
public class GuestService {
	
	private final GuestRepository guestRepo;
	
	public GuestService(GuestRepository guestRepo) {
		
		this.guestRepo = guestRepo;
	}
	
	public Guest registerGuest(Guest inputGuest) {
		
		String hashed = BCrypt.hashpw(inputGuest.getPassword(), BCrypt.gensalt());
		inputGuest.setPassword(hashed);
		return this.guestRepo.save(inputGuest);
	}
	
	public Guest findGuestByEmail(String email) {
		
		return this.guestRepo.findByEmail(email);
	}
	
	public Guest findGuestById(Long Id) {
		
		return this.guestRepo.findGuestById(Id);
	}
	
	public boolean authenticateUser(String email, String password) {
		
		Guest theguest = this.guestRepo.findByEmail(email);
		
		if (theguest == null) {
			return false;
		}
		
		else {
			
			if(BCrypt.checkpw(password, theguest.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	
}
