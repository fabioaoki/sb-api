package com.aoki.socialBank.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aoki.socialBank.dto.PersonDto;
import com.aoki.socialBank.responses.Response;
import com.aoki.socialBank.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	PersonService personService;

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity<Response<PersonDto>> registerPerson(@Valid @RequestBody PersonDto personDto,
			BindingResult result) throws Exception {
		Response<PersonDto> response = new Response<PersonDto>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return new ResponseEntity<Response<PersonDto>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			personService.register(personDto);
			return new ResponseEntity<Response<PersonDto>>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Response<PersonDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<PersonDto> findPerson(@PathVariable(value = "id") long id) throws Exception {
		if (Objects.nonNull(id)) {
			try {
				PersonDto personDto = personService.findId(id);
				return new ResponseEntity<PersonDto>(personDto, HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PersonDto> alterRegister(@PathVariable(value = "id") long id, @RequestBody PersonDto dto)
			throws Exception {
		try {
			PersonDto personDto = personService.findId(id);
			dto.setId(personDto.getId());
			if (dto.getBirthDate() == null) {
				dto.setBirthDate(personDto.getBirthDate());
			}
			if (dto.getAddress() == null) {
				dto.setAddress(personDto.getAddress());
			}
			if (dto.getName() == null) {
				dto.setName(personDto.getName());
			}
			if (dto.getEmail() == null) {
				dto.setEmail(personDto.getEmail());
			}
			personService.alterRegister(dto);
			return new ResponseEntity<PersonDto>(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<PersonDto>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PersonDto> deletaPessoa(@PathVariable(value = "id") long id) {
		try {
			personService.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}