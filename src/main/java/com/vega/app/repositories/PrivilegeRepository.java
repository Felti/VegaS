package com.vega.app.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.PrivilegeDTO;
import com.vega.app.entities.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{
	
	Integer countByNameIgnoreCase(String name);

	@Query("SELECT new com.vega.app.dtos.PrivilegeDTO(p.id, p.name, p.createdAt, p.modifiedAt, p.deleted) FROM Privilege p WHERE p.deleted = ?1 ")
	Set<PrivilegeDTO> findByDeleted(Boolean deleted);

	Privilege findByIdAndDeleted(Long id, Boolean deleted);

	@Query("SELECT new com.vega.app.dtos.PrivilegeDTO(p.id, p.name, p.createdAt, p.modifiedAt, p.deleted) FROM Privilege p")
	Set<PrivilegeDTO> getAll();
	
	@Query("SELECT new com.vega.app.dtos.PrivilegeDTO(p.id, p.name, p.createdAt, p.modifiedAt, p.deleted) FROM Privilege p where p.deleted is false")
	Set<PrivilegeDTO> getAllDeleted();
	
	@Query("SELECT new com.vega.app.dtos.PrivilegeDTO(p.id, p.name) FROM Privilege p LEFT JOIN p.roles role WHERE p.deleted = false AND role.id = ?1")
	Set<PrivilegeDTO> getPrivilegesByRoleId(Long roleId);
	
	@Query("SELECT new com.vega.app.dtos.PrivilegeDTO(p.id, p.name) FROM Privilege p LEFT JOIN p.users user WHERE p.deleted = false AND user.id = ?1")
	Set<PrivilegeDTO> getPrivilegesByUserId(Long userId);
	
	
}
