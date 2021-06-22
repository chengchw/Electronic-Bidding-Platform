package com.chengchw.SHEA.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


import com.chengchw.SHEA.Model.Provider;
import com.chengchw.SHEA.Repository.ProviderRepository;

@Component
public class ProviderValidator {
	
	private final ProviderRepository proRepo;
	
	public ProviderValidator(ProviderRepository proRepo) {
		
		this.proRepo = proRepo;
	}
	
	public boolean supports(Class<?> clazz) {
        return Provider.class.equals(clazz);
    }
	
	public void validate(Object target, Errors errors) {
		
		Provider user = (Provider)target;
		
		
		if(!user.getPassword().equals(user.getPasswordConfirmation())) {
			errors.rejectValue("password", "Match", "Password not match");
		}
		
		else if(this.proRepo.existsByEmail(user.getEmail())) {
			
			errors.rejectValue("email", "Match", "Email Already Exist");
		}
	}
}
