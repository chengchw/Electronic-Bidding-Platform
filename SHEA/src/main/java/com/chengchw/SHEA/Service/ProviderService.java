package com.chengchw.SHEA.Service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


import com.chengchw.SHEA.Model.Provider;
import com.chengchw.SHEA.Repository.ProviderRepository;

@Service
public class ProviderService {

	private final ProviderRepository proRepo;
	
	public ProviderService(ProviderRepository proRepo) {
		
		this.proRepo = proRepo;	
	}
	
	public Provider registerProvider(Provider inputProvider) {
		
		String hashed = BCrypt.hashpw(inputProvider.getPassword(), BCrypt.gensalt());
		inputProvider.setPassword(hashed);
		return this.proRepo.save(inputProvider);
		
	}
	
	public Provider findProvByEmail(String email) {
		
		return this.proRepo.findByEmail(email);
	}
	
	public Provider findProvById(Long Id) {
		
		return this.proRepo.findProviderById(Id);
	}
	
	public boolean authenticateUser(String email, String password) {
		
		Provider theprovider = this.proRepo.findByEmail(email);
		
		if (theprovider == null) {
			return false;
		}
		
		else {
			
			if(BCrypt.checkpw(password, theprovider.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
}
