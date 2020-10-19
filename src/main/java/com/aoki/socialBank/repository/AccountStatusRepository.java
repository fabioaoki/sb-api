package com.aoki.socialBank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aoki.socialBank.entity.AccountStatus;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long>{
	
	AccountStatus findById(long id);

	AccountStatus findByAccountId(long id);

	List<AccountStatus> findAllByAccountId(long id);
	
}
