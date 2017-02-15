package com.pylonmusic.playground.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pylonmusic.playground.domain.Person;
import com.pylonmusic.playground.service.PersonService;

@Controller
public class IndexController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/persons/all")
	@Transactional(readOnly=true)
	public @ResponseBody List<Person> getPersistedPeople() {
		return personService.findAll();
	}
	
	@PostMapping("persons/drop")
	@Transactional
	@ResponseBody
	public void deleteAll() {
		personService.deleteAll();
	}
	
}
