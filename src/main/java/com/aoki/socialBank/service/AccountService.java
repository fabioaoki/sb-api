package com.aoki.socialBank.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoki.socialBank.dto.AccountDto;
import com.aoki.socialBank.dto.PersonDto;
import com.aoki.socialBank.entity.Account;
import com.aoki.socialBank.entity.AccountStatus;
import com.aoki.socialBank.entity.Person;
import com.aoki.socialBank.entity.SituacaoConta;
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

	SituacaoConta situacaoConta;

	public void register(AccountDto accountDto, long id) {
		accountDto.prePersist(accountDto);
		Person person = personRepopsitory.findById(id);
		Account account = Account.builder().dateCreate(accountDto.getDateCreate()).number(accountDto.getNumber())
				.person(person).build();
		accountRepository.save(account);
		AccountStatus accountStatus = new AccountStatus();
		accountStatus.setSituacaoConta(SituacaoConta.BLOQUEIO_SITUACAO_INICIAL.toString());
		accountStatus.setAccount(account);
		accountStatusRepository.save(accountStatus);
	}

	public AccountDto findAccount(long id) throws Exception {
		Account account = accountRepository.findById(id);
		Person person = personRepopsitory.findById(id);
		;
		if (Objects.nonNull(account)) {
			return AccountDto.builder().id(account.getId()).number(account.getNumber())
					.amount(account.getAmount()).dateCreate(account.getDateCreate())
					.person(PersonDto.builder().id(person.getId()).email(person.getEmail())
								.address(person.getAddress()).birthDate(person.getBirthDate())
								.name(person.getName())
					.build()).build();
		}
		throw new Exception("id nao encontrado");
	}

	public void delete(long id) {
		accountRepository.deleteById(id);
	}

}
