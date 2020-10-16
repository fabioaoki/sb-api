package com.aoki.socialBank.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/socialBank", method = RequestMethod.POST)
	public ResponseEntity<AccountDto> cadastro(@RequestBody AccountDto accountDto) throws Exception {
		if(Objects.nonNull(accountDto)) {
			accountService.register(accountDto);
			return new ResponseEntity<AccountDto>(HttpStatus.CREATED);
		}
			return new ResponseEntity<AccountDto>(HttpStatus.BAD_REQUEST);
		}
}
