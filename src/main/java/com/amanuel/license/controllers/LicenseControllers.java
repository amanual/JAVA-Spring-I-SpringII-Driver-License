package com.amanuel.license.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amanuel.license.models.License;
import com.amanuel.license.models.Person;
import com.amanuel.license.services.LicenseService;


@Controller
public class LicenseControllers {
	private final LicenseService licenseService;
	public LicenseControllers(LicenseService licenseService) {
		this.licenseService = licenseService;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping("/")
	public String index(@ModelAttribute("person") Person person) {
		System.out.println("helooo");
		return "indexPage";
	}
	@PostMapping("/add")
	public String addPerson(@Valid @ModelAttribute("person") Person person,@ModelAttribute("license") License license, BindingResult result,Model model) {
		System.out.println("newHelo");
		if(result.hasErrors()) {
			System.out.println("newHelo: 1");
			return "redirect:/";
		}
		else {
			
		licenseService.addNewPerson(person);
		model.addAttribute("persons", licenseService.getAllPerson());
		return "newPage";
		}
	}
	@PostMapping("/license/{id}")
	public String createLicense(@Valid @ModelAttribute("license") License license,@PathVariable("id") Long id, BindingResult result,Model model) {
		Person personInfo = licenseService.getPerson(id);
		model.addAttribute("personInfo", personInfo);
//		model.addAttribute("personInfo2", licenseService.getLicense(id));
		System.out.println("personInfo: " + personInfo);
		if(result.hasErrors()) {
			System.out.println("from license error");
			model.addAttribute("persons", licenseService.getAllPerson());
			return "redirect:/license{id}";
		}
		else {
			System.out.println("newHeloLicense: 2");
			model.addAttribute("persons", licenseService.getAllPerson());
//			Person userPerson = licenseService.getPerson(id);
			String licNumber = String.format("%06d", id);
			System.out.println("newHeloLicense:" + licNumber);
			license.setNumber(licNumber);
			licenseService.addNewLicense(license);
//			licenseService.addNewLicense(license);
//			License lic = licenseService.addNewLicense(license);
//			String personId = Long.toString(lic.getPerson().getId());
//			.concat(personId)
			return "showPage";
		}
	}
}
