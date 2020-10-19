package com.aoki.socialBank.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.dto.PersonDto;
import com.aoki.socialBank.service.AccountService;
import com.aoki.socialBank.service.PersonService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	PersonService personService;

	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public ResponseEntity<AccountDto> cadastro(@PathVariable(value = "id") long id, @RequestBody AccountDto accountDto)
			throws Exception {
		PersonDto personDto = personService.findId(id);
		if (Objects.nonNull(accountDto) && personService.findId(personDto.getId()) != null) {
			accountService.register(accountDto, id);
			accountDto.setPerson(personDto);
			return new ResponseEntity<AccountDto>(accountDto, HttpStatus.CREATED);
		}
		return new ResponseEntity<AccountDto>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccountDto> findAccount(@PathVariable(value = "id") long id) throws Exception {
		if (Objects.nonNull(id)) {
			try {
				AccountDto accountDto = accountService.findAccount(id);
				
				return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AccountDto> deleteAccount(@PathVariable(value = "id") long id) {
		try {
			accountService.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
