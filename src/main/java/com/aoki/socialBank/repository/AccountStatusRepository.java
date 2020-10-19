package com.aoki.socialBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aoki.socialBank.entity.AccountStatus;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long>{
	
	AccountStatus findById(long id);
	
}
