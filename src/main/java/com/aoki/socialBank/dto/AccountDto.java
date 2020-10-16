package com.aoki.socialBank.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PrePersist;

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

	private int number;

	private int agency;

	private float amount;
	
	private PersonDto personDto;

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
		return "AccountDto [id=" + id + ", number=" + number 
				+ ", agency=" + agency + ", amount=" + amount
				+ ", personDto=" + personDto + ", dateCreate=" 
				+ dateCreate + ", dateModify=" + dateModify + "]";
	}

	

}
