package com.aoki.socialBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aoki.socialBank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findById(long id);

}
