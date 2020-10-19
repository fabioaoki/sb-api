package com.aoki.socialBank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.dto.AccountStatusDto;
import com.aoki.socialBank.entity.AccountStatus;
import com.aoki.socialBank.entity.SituacaoConta;
import com.aoki.socialBank.repository.AccountRepository;
import com.aoki.socialBank.repository.AccountStatusRepository;

@Service
public class AccountStatusService {

	@Autowired
	AccountStatusRepository accountStatusRepository;
	
	@Autowired
	AccountRepository accountRepository;

	public void unblock(AccountDto accountDto, AccountStatusDto accountStatusDto) {
		
		AccountStatus accountStatus = AccountStatus.builder().situacaoConta(SituacaoConta.ATIVO.toString())
				.dateModify(accountStatusDto.getDateModify())
				//.account(accountDto.getId())
				.build();
		accountStatusRepository.save(accountStatus);
	}

	public void block(AccountDto accountDto, AccountStatusDto accountStatusDto) {
		AccountStatus accountStatus = AccountStatus.builder().situacaoConta(SituacaoConta.ATIVO.toString())
				.dateModify(accountStatusDto.getDateModify())
				//.account(accountDto.getId())
				.build();
		accountStatusRepository.save(accountStatus);
	}

	public List<AccountStatusDto> findAccountStatus() {

		List<AccountStatus> accountStatus = accountStatusRepository.findAll();
		List<AccountStatusDto> accountStatusDtos = new ArrayList<>();

		for (AccountStatus accountStatusList : accountStatus) {
			accountStatusDtos.add(AccountStatusDto.builder().id(accountStatusList.getId())
					.dateModify(accountStatusList.getDateModify()).build());
		}
		return accountStatusDtos;
	}

}
