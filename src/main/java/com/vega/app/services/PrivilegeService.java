package com.vega.app.services;

import java.util.Set;

import com.vega.app.dtos.PrivilegeDTO;
import com.vega.app.entities.Privilege;

public interface PrivilegeService {

	Set<PrivilegeDTO> getAll();

	Set<PrivilegeDTO> getAllDeleted();

	Set<PrivilegeDTO> getByRoleId(Long id);

	Privilege getById(Long id);

	Set<PrivilegeDTO> getByUserId(Long id);

	PrivilegeDTO create(String name);

	PrivilegeDTO edit(PrivilegeDTO privilegeDTO);

	PrivilegeDTO softDelete(Long id);

	PrivilegeDTO undelete(Long id);

	void delete(Long id);

	Privilege mapDTOToEntity(PrivilegeDTO dto);

	PrivilegeDTO mapEntityToDTO(Privilege privilege);

	void unlinkPrivilegesFromRoles(Long privilegeId);

	void unlinkPrivilegesFromUsers(Long privilegeId);

}
