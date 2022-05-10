package com.escalab.mediappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.mediappbackend.model.ResetToken;

public interface IResetTokenRepo extends JpaRepository<ResetToken, Integer> {
	
	//from ResetToken where token = :?
	ResetToken findByToken(String token);

}

