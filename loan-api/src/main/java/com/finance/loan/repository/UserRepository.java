package com.finance.loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.finance.loan.model.Profile;
import com.finance.loan.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<Profile> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	User findByUsernameAndPassword(String userame, String password);
}
