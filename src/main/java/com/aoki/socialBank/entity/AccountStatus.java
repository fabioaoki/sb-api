package com.aoki.socialBank.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aoki.socialBank.dto.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Entity
@Getter
@Setter
@Table(name="account_status")
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatus implements Serializable {

	private static final long serialVersionUID = 712077955892392435L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private AccountDto accountDto;

	private Date dateModify;
	
	@Column(name = "situacao_conta", nullable = false)
	private SituacaoConta situacaoConta;
	

}
