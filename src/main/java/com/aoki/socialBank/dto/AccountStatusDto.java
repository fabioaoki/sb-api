package com.aoki.socialBank.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountStatusDto implements Serializable {

	private static final long serialVersionUID = -4930934796205841467L;

	private Long id;

	private AccountDto accountDto;

	private String reason;

	private Date dateModify;

	@PrePersist
	public AccountDto prePersist(AccountStatusDto accountDtoStatusDto) {
		final Date atual = new Date();
		accountDtoStatusDto.setDateModify(atual);
		return accountDto;
	}

	@Override
	public String toString() {
		return "AccountStatusDto [id=" + id + ", accountDto=" 
				+ accountDto + ", reason=" + reason + ", dateModify="
				+ dateModify + "]";
	}

}
