package com.aoki.socialBank.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoki.socialBank.dto.PersonDto;
import com.aoki.socialBank.entity.Person;
import com.aoki.socialBank.repository.PersonRepopsitory;

@Service
public class PersonService {
	
	@Autowired
	PersonRepopsitory personRepository;

	public void register(PersonDto personDto) {
		Person person = Person.builder().name(personDto.getName()).address(personDto.getAddress())
				.email(personDto.getEmail()).birthDate(personDto.getBirthDate()).build();
		personRepository.save(person);
	}

	public PersonDto findId(long id) throws Exception {
		Person person = personRepository.findById(id);
		if(Objects.nonNull(person)) {
			return PersonDto.builder().id(person.getId()).name(person.getName())
					.address(person.getAddress()).birthDate(person.getBirthDate())
					.email(person.getEmail()).build();
		}
		throw new Exception("id nao encontrado");
	}

	public void alterRegister(PersonDto dto) {
		Person person = Person.builder().id(dto.getId()).name(dto.getName()).address(dto.getAddress())
				.email(dto.getEmail()).birthDate(dto.getBirthDate()).build();
		personRepository.save(person);
	}

	public void delete(long id) {
		personRepository.deleteById(id);
	}
}
