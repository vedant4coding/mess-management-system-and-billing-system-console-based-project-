package org.mess.service;
import org.mess.repository.RegistrationRepository;

import java.util.List;

import org.mess.model.*;
public class RegistrationService {
	RegistrationRepository regRepo = new RegistrationRepository();
	
	public boolean isAddCustomer(RegistrationModel obj) {
		return regRepo.isAddCustomer(obj);
	}
	
	public List<RegistrationModel> isCheckCustomerUsingPhoneNo(RegistrationModel obj) {
		return regRepo.isCheckCustomerUsingPhoneNo(obj);
	}
	public int fetchCidService(String phoneNo) {
		return regRepo.fetchCidRepo(phoneNo);
	}
}
