package com.amanuel.license.services;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.amanuel.license.models.License;
import com.amanuel.license.models.Person;
import com.amanuel.license.repositories.LicenseRepository;
import com.amanuel.license.repositories.PersonRepository;

@Service
public class LicenseService {
	private final PersonRepository personRepository;
	private final LicenseRepository licenseRepository;
	
//	public LicenseService() {
//		
//	}
	public LicenseService(LicenseRepository licenseRepository, PersonRepository personRepository) {
		this.personRepository = personRepository;
		this.licenseRepository = licenseRepository;
	}

	public ArrayList<Person> getAllPerson(){
		return personRepository.findAll();
	}
	public Person getPerson(Long id) {
		return personRepository.findOne(id);
	}
	public Person addNewPerson(Person person) {
		
		return personRepository.save(person);
	}
	
	//License methods:
	public License addNewLicense(License license) {
//		Long personId = license.getPerson().getId();
		// Pad with 0 with a width of 6
//		String licNumber = String.format("%06d", personId);
//
//		license.setNumber(licNumber);
		License lic = licenseRepository.save(license);
		
		return lic;
//		licenseRepository.save(license);
	}
//	public License getLicense(Long id) {
//		return licenseRepository.findOne(id);
//	}
}
