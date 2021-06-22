package com.chengchw.SHEA.Validator;

import org.springframework.stereotype.Component;
//import org.springframework.validation.Validator
import org.springframework.validation.Errors;

 
import com.chengchw.SHEA.Model.Guest;
import com.chengchw.SHEA.Repository.GuestRepository;

@Component
public class GuestValidator{
	
	private final GuestRepository guestRepo;
	
	public  GuestValidator(GuestRepository guestRepo) {
		
		this.guestRepo = guestRepo;
		
	}
	
	public boolean supports(Class<?> clazz) {
        return Guest.class.equals(clazz);
    }
	
	public void validate(Object target, Errors errors) {
		
		Guest user = (Guest)target;
		
		
		if(!user.getPassword().equals(user.getPasswordConfirmation())) {
			errors.rejectValue("password", "Match", "Password or Account not valid");
		}
		
		else if(this.guestRepo.existsByEmail(user.getEmail())) {
			
			errors.rejectValue("email", "Match", "Password or Account not valid");
		}
	}
}
