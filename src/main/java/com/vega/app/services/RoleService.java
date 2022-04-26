package com.vega.app.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.vega.app.dtos.RoleDTO;
import com.vega.app.dtos.simple.SimpleRoleDTO;
import com.vega.app.entities.Role;

@Service
public interface RoleService {

	Set<RoleDTO> getAll();

	Set<RoleDTO> getAllDeleted(Boolean deleted);

	Role getById(Long id);

	Role getByIdAndDeleted(Long id, Boolean deleted);

	Set<RoleDTO> getByUserId(Long userId);

	RoleDTO createWithName(String name);
	
	SimpleRoleDTO getByNameAndDeleted(String name, Boolean deleted);

	SimpleRoleDTO createWithPrivileges(RoleDTO dto);

	SimpleRoleDTO edit(RoleDTO roledDto);

	void delete(Long id);

	void unlinkRoleFromPrivileges(Long roleId);

	void unlinkRoleFromUsers(Long roleId);

	RoleDTO mapEntityToDTO(Role role);

	Role mapDTOToEntity(RoleDTO dto);

	SimpleRoleDTO mapEntityToSimpleDTO(Role role);
	
	Role mapSimpleDTOToEntity(SimpleRoleDTO dto);
}
