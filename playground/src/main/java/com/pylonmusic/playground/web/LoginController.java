package com.pylonmusic.playground.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pylonmusic.playground.domain.Person;
import com.pylonmusic.playground.service.PersonService;

@Controller
@RequestMapping("/index")
public class LoginController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public String index(Person person) {
		return "index";
	}
	
	@PostMapping
	public String handleSubmit(@Valid Person person, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "index";
		}
		
		try {
			personService.save(person);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Person added=" + person.toString());
		return "redirect:/persons/all";
	}
	
}
