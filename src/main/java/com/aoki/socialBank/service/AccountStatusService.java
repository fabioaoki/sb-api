package com.aoki.socialBank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoki.socialBank.dto.AccountStatusDto;
import com.aoki.socialBank.entity.Account;
import com.aoki.socialBank.entity.AccountStatus;
import com.aoki.socialBank.entity.SituationAccount;
import com.aoki.socialBank.repository.AccountRepository;
import com.aoki.socialBank.repository.AccountStatusRepository;

@Service
public class AccountStatusService {

	@Autowired
	AccountStatusRepository accountStatusRepository;
	
	@Autowired
	AccountRepository accountRepository;

	public void unblock(long  id, AccountStatusDto accountStatusDto) {
		alterStatusAcount(id, accountStatusDto, SituationAccount.ATIVO);
	}

	public void block(long id, AccountStatusDto accountStatusDto) {
		alterStatusAcount(id, accountStatusDto, SituationAccount.BLOQUEIO_SUSPEITA_FRAUDE);
	}

	private void alterStatusAcount(long id, AccountStatusDto accountStatusDto, SituationAccount typeStatus) {
		Account account = accountRepository.findById(id);
		AccountStatus accountStatus = AccountStatus.builder().situacaoConta(typeStatus.toString())
				.dateModify(accountStatusDto.getDateModify()).account(account).build();
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
