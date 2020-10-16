package com.aoki.socialBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.entity.Account;
import com.aoki.socialBank.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public void register(AccountDto accountDto) {
		accountDto.prePersist(accountDto);
		Account account = Account.builder().agency(accountDto.getAgency())
				.dateCreate(accountDto.getDateCreate())
				.number(accountDto.getNumber()).personId(accountDto.getPersonDto().getId()).build();
		accountRepository.save(account);
	}
}
