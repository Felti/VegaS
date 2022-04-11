package com.vega.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega.app.entities.AuthUser;

public interface UserAuthRepo extends JpaRepository<AuthUser, Long> {

}
