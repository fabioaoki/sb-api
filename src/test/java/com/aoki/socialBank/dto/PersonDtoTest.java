package com.aoki.socialBank.dto;

import java.util.Date;

public class PersonDtoTest {
	
	private PersonDto personDto;
	
	public  PersonDtoTest() {}	
	

	public static PersonDtoTest cadastroTodosOsCampos(){
		PersonDtoTest personDtoTeste = new PersonDtoTest();
		personDtoTeste.personDto = new PersonDto(null,"fabio",new Date(1993, 9, 11),"fabio@aoki","rua osasco");
		return personDtoTeste;
	}
	
	public static PersonDtoTest cadastroCamposObrigatorios(){
		PersonDtoTest personDtoTeste = new PersonDtoTest();
		personDtoTeste.personDto = new PersonDto(null,"fabio",new Date(1993, 9, 11),"fabio@aoki","rua osasco");
		return personDtoTeste;
	}
	
	public static PersonDtoTest cadastroCamposObrigatoriosNulos(){
		PersonDtoTest personDtoTeste = new PersonDtoTest();
		personDtoTeste.personDto = new PersonDto(null,"fabio",new Date(1993, 9, 11),"fabio@aoki",null);
		return personDtoTeste;
	}
	
	public PersonDtoTest comNome(String nome) {
		personDto.setName(nome);
		return this;
	}
	
	public PersonDto agora(){
		return personDto;
	}
}
