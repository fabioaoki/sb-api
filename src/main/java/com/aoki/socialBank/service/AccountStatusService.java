package com.aoki.socialBank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.dto.AccountStatusDto;
import com.aoki.socialBank.entity.Account;
import com.aoki.socialBank.entity.AccountStatus;
import com.aoki.socialBank.entity.SituationAccount;
import com.aoki.socialBank.exception.AccountStatusException;
import com.aoki.socialBank.repository.AccountRepository;
import com.aoki.socialBank.repository.AccountStatusRepository;

@Service
public class AccountStatusService {

	@Autowired
	AccountStatusRepository accountStatusRepository;
	
	@Autowired
	AccountRepository accountRepository;

	public void unblock(long  id, AccountStatusDto accountStatusDto) throws AccountStatusException {
		AccountStatus accountSituation = accountStatusRepository.findByAccountId(id);
		if(accountSituation.getAccountSituation() == SituationAccount.BLOQUEIO_SITUACAO_INICIAL.toString()) {
			alterStatusAcount(accountSituation.getId(), accountStatusDto, SituationAccount.ATIVO);
		} else {
			throw new AccountStatusException("Cartao nao esta no status de bloqueio inicial");
		}
	}

	public void block(long id, AccountStatusDto accountStatusDto) throws AccountStatusException {
		AccountStatus accountSituation = accountStatusRepository.findByAccountId(id);
		if(accountSituation.getAccountSituation() == SituationAccount.ATIVO.toString()) {
			alterStatusAcount(accountSituation.getId(), accountStatusDto, SituationAccount.BLOQUEIO_SUSPEITA_FRAUDE);
		} else {
			throw new AccountStatusException("Cartao nao esta ativo para realizar o bloqueio por fraude");
		}
	}

	private void alterStatusAcount(long id, AccountStatusDto accountStatusDto, SituationAccount typeStatus) {
		Account account = accountRepository.findById(id);
		AccountStatus accountStatus = AccountStatus.builder().accountSituation(typeStatus.toString())
				.dateModify(accountStatusDto.getDateModify()).account(account).id(id).build();
		accountStatusRepository.save(accountStatus);
	}

	public List<AccountStatusDto> findAccountStatus() {
		List<AccountStatus> accountStatus = accountStatusRepository.findAll();
		List<AccountStatusDto> accountStatusDtos = new ArrayList<>();
		

		for (AccountStatus accountStatusList : accountStatus) {
			Account account = accountRepository.findById(1);
			SituationAccount accountSituation = Enum.valueOf(SituationAccount.class, accountStatusList.getAccountSituation().toString());
			accountStatusDtos.add(AccountStatusDto.builder().id(accountStatusList.getId())
					.dateModify(accountStatusList.getDateModify())
					.accountSituation(accountSituation)
					.account(AccountDto.builder().id(account.getId()).build())
					.build());
		}
		return accountStatusDtos;
	}

	public List<AccountStatusDto> findAccountStatusById(long id) throws AccountStatusException {
		List<AccountStatus> accountStatus = accountStatusRepository.findAllByAccountId(id);
		if(Objects.nonNull(accountStatus)) {
			List<AccountStatusDto> accountStatusDtos = new ArrayList<>();
			Account account = accountRepository.findById(id);
			
			for (AccountStatus accountStatusList : accountStatus) {
				SituationAccount accountSituation = Enum.valueOf(SituationAccount.class, accountStatusList.getAccountSituation().toString());
				accountStatusDtos.add(AccountStatusDto.builder().id(accountStatusList.getId())
						.dateModify(accountStatusList.getDateModify())
						.accountSituation(accountSituation)
						.account(AccountDto.builder().id(account.getId())
								.number(account.getNumber()).amount(account.getAmount()).dateCreate(account.getDateCreate()).build())
						.build());
			}
			return accountStatusDtos;
		} else {
			throw new AccountStatusException("Conta nao encontrada.");
		}
	}

}
