package com.aoki.socialBank.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

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
		PersonDto personDto = personService.register(dto);
		// verificacao
		Assert.assertNotNull(personDto);

	}

	@Test(expected = PersonException.class)
	public void testeCadastrarCamposObrigatoriosNulos() throws PersonException {
		PersonDto dto = new PersonDto(null, null, null, null, null);

		error.checkThat(personService.register(dto), is(nullValue()));
	}

}
