package com.vega.app.services.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.PrivilegeDTO;
import com.vega.app.dtos.RoleDTO;
import com.vega.app.dtos.SimpleRoleDTO;
import com.vega.app.entities.Privilege;
import com.vega.app.entities.Role;
import com.vega.app.exceptions.ObjectAlreadyExists;
import com.vega.app.repositories.RoleRepository;
import com.vega.app.services.PrivilegeService;
import com.vega.app.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PrivilegeService privilegeService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Set<RoleDTO> getAll() {
		Set<RoleDTO> roles = roleRepository.getAll();

		if (CollectionUtils.isNotEmpty(roles)) {
			for (RoleDTO role : roles) {
				Set<PrivilegeDTO> privileges = privilegeService.getByRoleId(role.getId());
				if (CollectionUtils.isNotEmpty(privileges)) {
					role.setPrivileges(privileges);
				}
			}
		}

		return roles;
	}

	@Override
	public Set<RoleDTO> getAllDeleted(Boolean deleted) {
		Set<RoleDTO> roles = roleRepository.findDtoByDeleted(deleted);

		if (CollectionUtils.isNotEmpty(roles)) {
			for (RoleDTO role : roles) {
				Set<PrivilegeDTO> privileges = privilegeService.getByRoleId(role.getId());
				if (CollectionUtils.isNotEmpty(privileges)) {
					role.setPrivileges(privileges);
				}
			}
		}
		return roles;
	}

	@Override
	public Role getById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);

		Optional<Role> data = roleRepository.findById(id);

		if (data.isPresent()) return data.get();

		return null;
	}

	@Override
	public Role getByIdAndDeleted(Long id, Boolean deleted) {
		return roleRepository.findByIdAndDeleted(id, deleted);
	}

	@Override
	public Set<RoleDTO> getByUserId(Long userId) {
		Assert.notNull(userId, ErrorMessages.ID_NOT_FOUND);
		return roleRepository.getRolesByUserId(userId);
	}
	
	@Override
	public SimpleRoleDTO getByNameAndDeleted(String name, Boolean deleted) {
		Assert.notNull(name, ErrorMessages.MISSING_NAME);
		return roleRepository.getByNameAndDeleted(name.trim(),deleted);
	}

	@Override
	public RoleDTO createWithName(String name) {
		Assert.notNull(name, "Role name is missing");
		int existingRoles = roleRepository.countByNameIgnoreCase(name);
		if (existingRoles > 1) throw new ObjectAlreadyExists(Role.class.getName());
		var role = new Role();
		role.setName(name.trim());

		return mapEntityToDTO(roleRepository.save(role));
	}

	@Override
	public SimpleRoleDTO createWithPrivileges(RoleDTO dto) {
		Assert.notNull(dto, ErrorMessages.OBJECT_NOT_FOUND);

		int existingRoles = roleRepository.countByNameIgnoreCase(dto.getName());
		if (existingRoles > 0) throw new ObjectAlreadyExists(Role.class.getName());

		var role = new Role();

		Assert.notNull(dto.getName(), ErrorMessages.MISSING_NAME);
		role.setName(dto.getName());

		if (CollectionUtils.isEmpty(dto.getPrivileges())) {
			throw new NullPointerException("Privilges list is empty");
		}

		Set<Privilege> privileges = dto.getPrivileges().parallelStream().map(p -> mapper.map(p, Privilege.class))
				.collect(Collectors.toSet());
		role.setPrivileges(privileges);
		return mapEntityToSimpleDTO(roleRepository.save(role));
	}

	@Override
	public SimpleRoleDTO edit(RoleDTO dto) {
		Assert.notNull(dto.getId(), ErrorMessages.ID_NOT_FOUND);

		var existingRole = getById(dto.getId());

		Assert.notNull(existingRole, ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(dto.getName(), ErrorMessages.MISSING_NAME);
		Assert.notNull(dto.getName().trim(), ErrorMessages.MISSING_NAME);

		existingRole.setName(dto.getName().trim());

		if (!CollectionUtils.isEmpty(dto.getPrivileges())) {
			existingRole.getPrivileges().clear();

			Set<Privilege> privileges = dto.getPrivileges().parallelStream().map(e -> mapper.map(e, Privilege.class))
					.collect(Collectors.toSet());

			existingRole.setPrivileges(privileges);
		}

		return mapEntityToSimpleDTO(roleRepository.save(existingRole));
	}

	@Override
	public void delete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);

		var role = getById(id);

		Assert.notNull(role, ErrorMessages.OBJECT_NOT_FOUND);

		unlinkRoleFromPrivileges(id);
		unlinkRoleFromUsers(id);

		roleRepository.delete(role);

	}

	@Override
	public void unlinkRoleFromPrivileges(Long roleId) {
		Assert.notNull(roleId, ErrorMessages.ID_NOT_FOUND);

		var role = getById(roleId);

		Assert.notNull(role, ErrorMessages.OBJECT_NOT_FOUND);

		role.removePrivileges();

	}

	@Override
	public void unlinkRoleFromUsers(Long roleId) {
		Assert.notNull(roleId, ErrorMessages.ID_NOT_FOUND);

		var role = getById(roleId);

		Assert.notNull(role, ErrorMessages.OBJECT_NOT_FOUND);

		role.removeUsers();

	}

	@Override
	public RoleDTO mapEntityToDTO(Role role) {
		return mapper.map(role, RoleDTO.class);
	}

	@Override
	public Role mapDTOToEntity(RoleDTO dto) {
		return mapper.map(dto, Role.class);
	}

	@Override
	public SimpleRoleDTO mapEntityToSimpleDTO(Role role) {
		return mapper.map(role, SimpleRoleDTO.class);
	}
	
	@Override
	public Role mapSimpleDTOToEntity(SimpleRoleDTO dto) {
		return mapper.map(dto, Role.class);
	}





}
