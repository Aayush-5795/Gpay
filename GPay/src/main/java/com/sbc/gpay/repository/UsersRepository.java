package com.sbc.gpay.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbc.gpay.entity.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByPhone(String fromPhone);
	
	
}
