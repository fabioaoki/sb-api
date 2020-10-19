package com.aoki.socialBank.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {

	private static final long serialVersionUID = 2285242557000746948L;

	private Long id;

	@NotEmpty(message = "number nao poder ser vazio")
	private int number;

	private float amount;

	private PersonDto person;

	private Date dateCreate;

	private Date dateModify;

	@PrePersist
	public AccountDto prePersist(AccountDto accountDto) {
		final Date atual = new Date();
		if (accountDto.dateCreate == null) {
			accountDto.setDateCreate(atual);
			return accountDto;
		} else {
			accountDto.setDateModify(atual);
			return accountDto;
		}
	}

	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", number=" + number + ", amount=" + amount + ", personDto=" + person
				+ ", dateCreate=" + dateCreate + ", dateModify=" + dateModify + "]";
	}

}
