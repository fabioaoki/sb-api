package com.aoki.socialBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aoki.socialBank.entity.Person;

public interface PersonRepopsitory extends JpaRepository<Person, Long>{

	Person findById(long id);
	
}
