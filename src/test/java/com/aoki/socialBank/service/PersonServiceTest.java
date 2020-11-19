package com.aoki.socialBank.service;

import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aoki.socialBank.dto.PersonDto;
import com.aoki.socialBank.entity.Person;
import com.aoki.socialBank.exception.PersonException;
import com.aoki.socialBank.repository.PersonRepopsitory;

public class PersonServiceTest {

	@InjectMocks
	PersonService personService;

	@Mock
	PersonRepopsitory personRepopsitory;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Test
	public void testeCadastrar() throws PersonException {
		// cenario
		PersonDto dto = new PersonDto(null, "fabio", new Date(1993, 9, 11), "fabio@aoki", "rua osasco");

		// acao
		PersonDto personDto = personService.register(dto);
		// verificacao
		Assert.assertNotNull(personDto);

	}

	@Test
	public void testeCadastrarTodosOsCampos() throws PersonException {
		// cenario
		PersonDto dto = new PersonDto(null, "fabio", new Date(1993, 9, 11), "fabio@aoki", "rua osasco");

		// acao
		personService.register(dto);
	}

	@Test(expected = PersonException.class)
	public void testeCadastrarCamposObrigatoriosNulos() throws PersonException {
		PersonDto dto = new PersonDto(null, null, null, null, null);

		personService.register(dto);
	}
	
	@Test
	public void testeDeletarCadastro() throws PersonException {
		// cenario
		Person daoFalso = mock(Person.class);
		daoFalso.setId(1l);
		personRepopsitory.deleteById(daoFalso.getId());
	}
	
//	@Test(expected = PersonException.class)
//	public void testeDeletarCadastroInexistente() throws PersonException {
//		// cenario
//		PersonDto dto = new PersonDto(null, "fabio", new Date(1993, 9, 11), "fabio@aoki", "rua osasco");
//		
//		Person person = new Person();
//		
//		personService.delete(dto.getId());
//		
//		//when(personRepopsitory.findById(dto.getId()).thenReturn(person));
//	}

}
