package com.vega.app.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.RoleDTO;
import com.vega.app.dtos.simple.SimpleRoleDTO;
import com.vega.app.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Integer countByNameIgnoreCase(String name);

	@Override
	Optional<Role> findById(Long id);

	Role findByIdAndDeleted(Long id, Boolean deleted);

	@Query("SELECT new com.vega.app.dtos.RoleDTO(r.id, r.name, r.createdAt, r.modifiedAt, r.deleted) FROM Role r WHERE r.deleted = ?1")
	Set<RoleDTO> findDtoByDeleted(Boolean deleted);

	@Query("SELECT new com.vega.app.dtos.simple.SimpleRoleDTO(r.id, r.name) FROM Role r WHERE r.name = ?1 AND r.deleted = ?2")
	SimpleRoleDTO getByNameAndDeleted(String name, Boolean deleted);

	@Query("SELECT new com.vega.app.dtos.RoleDTO(r.id, r.name, r.createdAt, r.modifiedAt, r.deleted) FROM Role r ")
	Set<RoleDTO> getAll();

	@Query("SELECT new com.vega.app.dtos.RoleDTO(r.id, r.name) FROM Role r LEFT JOIN r.users user WHERE r.deleted = false AND user.id = ?1")
	Set<RoleDTO> getRolesByUserId(Long userId);

	@Query("SELECT new com.vega.app.dtos.RoleDTO(r.id, r.name) FROM Role r WHERE r.id IN (:ids)")
	Set<RoleDTO> getByIds(Set<Long> ids);

	@Query("SELECT new com.vega.app.dtos.simple.SimpleRoleDTO(r.id, r.name) FROM Role r WHERE r.id IN (:ids)")
	Set<SimpleRoleDTO> getSimpleDTOByIds(Set<Long> ids);
}
