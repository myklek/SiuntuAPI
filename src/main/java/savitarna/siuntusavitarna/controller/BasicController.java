package savitarna.siuntusavitarna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import savitarna.siuntusavitarna.model.Persons;
import savitarna.siuntusavitarna.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BasicController {

	@Autowired
	private PersonService personService;


	@RequestMapping("/all")
	public List<Persons> getAll() {

		System.out.println("getAll");
		System.out.println(personService.findAll());
		return personService.findAll();
	}
	@GetMapping("/test")
	public String getTest() {

		System.out.println("Test After Cahnging Code");
		return "Test V4";
	}
}