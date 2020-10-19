package com.aoki.socialBank.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PrePersist;

import com.aoki.socialBank.entity.SituationAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountStatusDto implements Serializable {

	private static final long serialVersionUID = -4930934796205841467L;

	private Long id;

	private AccountDto account;

	private Date dateModify;
	
	private SituationAccount accountSituation;
	
	@PrePersist
	public AccountDto prePersist(AccountStatusDto accountDtoStatusDto) {
		final Date atual = new Date();
		accountDtoStatusDto.setDateModify(atual);
		return account;
	}


	public AccountStatusDto() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "AccountStatusDto [id=" + id + ", accountDto=" + account + ", dateModify=" + dateModify + "]";
	}
}
