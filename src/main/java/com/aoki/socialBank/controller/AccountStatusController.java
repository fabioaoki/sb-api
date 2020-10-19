package com.aoki.socialBank.controller;

import java.util.List;
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
import com.aoki.socialBank.dto.AccountStatusDto;
import com.aoki.socialBank.service.AccountService;
import com.aoki.socialBank.service.AccountStatusService;
import com.aoki.socialBank.service.PersonService;

@RestController
public class AccountStatusController {

	@Autowired
	AccountService accountService;

	@Autowired
	AccountStatusService accountStatusService;

	@Autowired
	PersonService personService;

	@RequestMapping(value = "/account/unblock/{id}", method = RequestMethod.POST)
	public ResponseEntity<AccountStatusDto> unblock(@PathVariable(value = "id") long id) throws Exception {
		AccountDto accountDto = accountService.findAccount(id);
		if (Objects.nonNull(accountDto)) {
			AccountStatusDto accountStatusDto = new AccountStatusDto();
			accountStatusDto.prePersist(accountStatusDto);
			accountStatusService.unblock(accountDto.getId(), accountStatusDto);
			return new ResponseEntity<AccountStatusDto>(HttpStatus.CREATED);
		}
		return new ResponseEntity<AccountStatusDto>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/account/block/{id}", method = RequestMethod.POST)
	public ResponseEntity<AccountStatusDto> block(@PathVariable(value = "id") long id,
			@RequestBody AccountDto accountDto) throws Exception {
		AccountDto dto = accountService.findAccount(id);
		if (Objects.nonNull(dto)) {
			AccountStatusDto accountStatusDto = new AccountStatusDto();
			accountStatusDto.prePersist(accountStatusDto);
			accountStatusService.block(dto.getId(), accountStatusDto);
			return new ResponseEntity<AccountStatusDto>(HttpStatus.CREATED);
		}
		return new ResponseEntity<AccountStatusDto>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ResponseEntity<List<AccountStatusDto>> findAccount() throws Exception {
		List<AccountStatusDto> accountStatusDto = accountStatusService.findAccountStatus();
		if (Objects.nonNull(accountStatusDto)) {
			return new ResponseEntity<List<AccountStatusDto>>(accountStatusDto, HttpStatus.OK);
		}
		return new ResponseEntity<List<AccountStatusDto>>(HttpStatus.BAD_REQUEST);
	}

}
