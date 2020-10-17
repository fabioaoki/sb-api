package com.aoki.socialBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.entity.Account;
import com.aoki.socialBank.entity.AccountStatus;
import com.aoki.socialBank.entity.SituacaoConta;
import com.aoki.socialBank.repository.AccountRepository;
import com.aoki.socialBank.repository.AccountStatusRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountStatusRepository accountStatusRepository;

	@Autowired
	SituacaoConta situacaoConta;

	public void register(AccountDto accountDto) {
		accountDto.prePersist(accountDto);
		Account account = Account.builder().dateCreate(accountDto.getDateCreate())
				.number(accountDto.getNumber()).personId(accountDto.getPerson()).build();
		accountRepository.save(account);
		AccountStatus accountStatus = new AccountStatus();
		accountStatus.setSituacaoConta(SituacaoConta.BLOQUEIO_SITUACAO_INICIAL);
		accountStatus.setAccountDto(accountDto);
		accountStatusRepository.save(accountStatus);
	}
}
