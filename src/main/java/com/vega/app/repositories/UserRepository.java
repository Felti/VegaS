package com.vega.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vega.app.dtos.UserDTO;
import com.vega.app.dtos.simple.SimpleUserDTO;
import com.vega.app.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT new com.vega.app.dtos.simple.SimpleUserDTO(id, firstName, lastName, login, password, isEnabled) FROM User u WHERE u.deleted = false AND u.login = ?1")
	SimpleUserDTO findByLogin(String login);
	
	@Query("SELECT new com.vega.app.dtos.simple.SimpleUserDTO(id, firstName, lastName, login, password, isEnabled) FROM User u WHERE u.deleted = false AND lower(u.login) = ?1")
	SimpleUserDTO getByLoginIgnoreCase(String login);
	
	
	@Query("SELECT new com.vega.app.dtos.simple.SimpleUserDTO(u.id,u.firstName, u.lastName, u.pictureUrl) FROM User u WHERE u.deleted = false AND u.id = ?1")
	SimpleUserDTO findDTOBasicById(Long id);
	
	@Query("SELECT new com.vega.app.dtos.simple.SimpleUserDTO(u.id,u.firstName, u.lastName, u.pictureUrl) FROM User u WHERE u.deleted = false AND u.id = ?1")
	UserDTO findDTOById(Long id);

	Optional<User> findById(Long id);
	
	@Query("SELECT COUNT(u.id) from User u WHERE LOWER(u.email) = :email" )
	int countByEmail(@Param("email") String email);
	
	@Query("SELECT COUNT(u.id) from User u WHERE LOWER(u.login) = :login" )
	int countByLogin(@Param("login") String login);
	

}
