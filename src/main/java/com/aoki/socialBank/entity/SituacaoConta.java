package com.aoki.socialBank.entity;

import lombok.Getter;

@Getter
public enum SituacaoConta {

	BLOQUEIO_SITUACAO_INICIAL("bloqueio_situacao_inicial"),
	ATIVO("ativo"),
	BLOQUEIO_SUSPEITA_FRAUDE("bloqueio_suspeita_fraude");

	private String descricao;
	
	SituacaoConta(String descricao) {
		this.descricao = descricao;
	}
	
}
