package com.aoki.socialBank.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.aoki.socialBank.repository.PersonRepopsitory;

public class PersonServiceTest {
	
	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepopsitory personRepopsitory;
	
	@Test
	public void testeCadastrar() throws Exception {
	}

}
