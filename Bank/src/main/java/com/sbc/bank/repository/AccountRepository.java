package com.sbc.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbc.bank.entity.Account;
import com.sbc.bank.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumber(long fromAccount);

	Optional<Account> findByUser(User user);

}
