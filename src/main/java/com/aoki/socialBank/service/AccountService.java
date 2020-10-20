package com.aoki.socialBank.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.dto.PersonDto;
import com.aoki.socialBank.entity.Account;
import com.aoki.socialBank.entity.AccountStatus;
import com.aoki.socialBank.entity.Person;
import com.aoki.socialBank.entity.SituationAccount;
import com.aoki.socialBank.exception.AccountExceptions;
import com.aoki.socialBank.repository.AccountRepository;
import com.aoki.socialBank.repository.AccountStatusRepository;
import com.aoki.socialBank.repository.PersonRepopsitory;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountStatusRepository accountStatusRepository;

	@Autowired
	PersonRepopsitory personRepopsitory;

	SituationAccount situacaoConta;

	public void register(AccountDto accountDto, long id) throws AccountExceptions {
		if(accountDto.getNumber() != 0) {
			accountDto.prePersist(accountDto);
			Person person = personRepopsitory.findById(id);
			Account account = Account.builder().dateCreate(accountDto.getDateCreate()).number(accountDto.getNumber())
					.person(person).build();
			accountRepository.save(account);
			AccountStatus accountStatus = new AccountStatus();
			accountStatus.setAccountSituation(SituationAccount.BLOQUEIO_SITUACAO_INICIAL.toString());
			accountStatus.setAccount(account);
			accountStatusRepository.save(accountStatus);
		} else {
			throw new AccountExceptions("Problema nos dados da conta.");
		}
		
	}

	public AccountDto findAccount(long id) throws AccountExceptions {
		Account account = accountRepository.findById(id);
		Person person = personRepopsitory.findById(id);
		if (Objects.nonNull(account)) {
			return AccountDto.builder().id(account.getId()).number(account.getNumber())
					.amount(account.getAmount()).dateCreate(account.getDateCreate())
					.person(PersonDto.builder().id(person.getId()).email(person.getEmail())
								.address(person.getAddress()).birthDate(person.getBirthDate())
								.name(person.getName())
					.build()).build();
		}
		throw new AccountExceptions("id da conta nao encontrado");
	}

	public void delete(long id) throws AccountExceptions {
		Account account = accountRepository.findById(id);
		if(Objects.nonNull(account)) {
			accountRepository.deleteById(id);
		} else {
			throw new AccountExceptions("Nenhuma conta encontrada.");
		}
	}

}
