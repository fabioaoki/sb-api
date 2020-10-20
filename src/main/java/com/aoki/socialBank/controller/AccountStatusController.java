package com.aoki.socialBank.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.dto.AccountStatusDto;
import com.aoki.socialBank.exception.AccountStatusException;
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
			try {
				accountStatusService.unblock(accountDto.getId(), accountStatusDto);
				return new ResponseEntity<AccountStatusDto>(HttpStatus.OK);
			} catch (AccountStatusException e) {
				System.out.println(e.getLocalizedMessage());
				return new ResponseEntity<AccountStatusDto>(HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new AccountStatusException("Conta nao encontrada");
		}
	}

	@RequestMapping(value = "/account/block/{id}", method = RequestMethod.POST)
	public ResponseEntity<AccountStatusDto> block(@PathVariable(value = "id") long id) throws Exception {
		AccountDto dto = accountService.findAccount(id);
		if (Objects.nonNull(dto)) {
			AccountStatusDto accountStatusDto = new AccountStatusDto();
			accountStatusDto.prePersist(accountStatusDto);
			try {
				accountStatusService.block(dto.getId(), accountStatusDto);
				return new ResponseEntity<AccountStatusDto>(HttpStatus.OK);
			} catch (AccountStatusException e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<AccountStatusDto>(HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new AccountStatusException("Conta nao encontrada");
		}
	}

	@RequestMapping(value = "/account/{id}/status", method = RequestMethod.GET)
	public ResponseEntity<List<AccountStatusDto>> findAccoountStatus(@PathVariable(value = "id") long id)
			throws AccountStatusException {
		if (Objects.nonNull(id)) {
			try {
				List<AccountStatusDto> accountStatusDto = accountStatusService.findAccountStatusById(id);
				return new ResponseEntity<List<AccountStatusDto>>(accountStatusDto, HttpStatus.OK);
			} catch (AccountStatusException e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new AccountStatusException("Problema ao passar o numero da conta");
		}

	}
}
