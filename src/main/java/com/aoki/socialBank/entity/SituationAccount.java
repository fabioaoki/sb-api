package com.aoki.socialBank.entity;

import lombok.Getter;

@Getter
public enum SituationAccount {

	BLOQUEIO_SITUACAO_INICIAL("bloqueio_situacao_inicial"),
	ATIVO("ativo"),
	BLOQUEIO_SUSPEITA_FRAUDE("bloqueio_suspeita_fraude");

	private String descricao;
	
	SituationAccount(String descricao) {
		this.descricao = descricao;
	}
	
}
