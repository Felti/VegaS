package com.vega.app.services.impl;

import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.PrivilegeDTO;
import com.vega.app.entities.Privilege;
import com.vega.app.exceptions.ObjectAlreadyExists;
import com.vega.app.repositories.PrivilegeRepository;
import com.vega.app.services.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	PrivilegeRepository privilegeRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Set<PrivilegeDTO> getAll() {
		return privilegeRepository.getAll();
	}

	@Override
	public Set<PrivilegeDTO> getAllDeleted() {
		return privilegeRepository.getAllDeleted();
	}

	@Override
	public Set<PrivilegeDTO> getByRoleId(Long id) {
		return privilegeRepository.getPrivilegesByRoleId(id);
	}

	@Override
	public Set<PrivilegeDTO> getByUserId(Long id) {
		return privilegeRepository.getPrivilegesByUserId(id);
	}

	@Override
	public Privilege getById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		Optional<Privilege> privilege = privilegeRepository.findById(id);

		if (privilege.isPresent()) return privilege.get();
		return null;
	}

	@Override
	public PrivilegeDTO create(String name) {
		if (ObjectUtils.isNotEmpty(name) && ObjectUtils.isNotEmpty(name.trim())) {
			var privilege = new Privilege();

			int countExistingPrivilegesWithSameName = privilegeRepository.countByNameIgnoreCase(name);
			if (countExistingPrivilegesWithSameName > 0) {
				throw new ObjectAlreadyExists(Privilege.class.getSimpleName());
			}

			privilege.setName(name.trim());

			return mapEntityToDTO(privilegeRepository.save(privilege));
		} else throw new NullPointerException("Missing name");
	}

	@Override
	public PrivilegeDTO edit(PrivilegeDTO privilegeDTO) {
		Assert.notNull(privilegeDTO.getId(), ErrorMessages.ID_NOT_FOUND);
		var privilege = privilegeRepository.findByIdAndDeleted(privilegeDTO.getId(), false);

		Assert.notNull(privilege, ErrorMessages.OBJECT_NOT_FOUND);

		privilege.setName(privilegeDTO.getName().trim());

		return mapEntityToDTO(privilegeRepository.save(privilege));
	}

	@Override
	public PrivilegeDTO softDelete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var privilege = privilegeRepository.getById(id);

		Assert.notNull(privilege, ErrorMessages.OBJECT_NOT_FOUND);
		privilege.setDeleted(true);

		return mapEntityToDTO(privilegeRepository.save(privilege));
	}

	@Override
	public PrivilegeDTO undelete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var privilege = privilegeRepository.getById(id);

		Assert.notNull(privilege, ErrorMessages.OBJECT_NOT_FOUND);
		privilege.setDeleted(false);

		return mapEntityToDTO(privilegeRepository.save(privilege));
	}

	@Override
	public void delete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);

		var privilege = privilegeRepository.findByIdAndDeleted(id, Boolean.FALSE);

		Assert.notNull(privilege, "Not found");

		unlinkPrivilegesFromUsers(id);
		unlinkPrivilegesFromRoles(id);

		privilegeRepository.delete(privilege);

	}

	@Override
	public void unlinkPrivilegesFromRoles(Long privilegeId) {
		Assert.notNull(privilegeId, ErrorMessages.ID_NOT_FOUND);

		var privilege = privilegeRepository.findByIdAndDeleted(privilegeId, Boolean.FALSE);

		Assert.notNull(privilege, ErrorMessages.OBJECT_NOT_FOUND);

		privilege.removeRoles();
	}

	@Override
	public void unlinkPrivilegesFromUsers(Long privilegeId) {
		Assert.notNull(privilegeId, ErrorMessages.ID_NOT_FOUND);

		var privilege = privilegeRepository.findByIdAndDeleted(privilegeId, Boolean.FALSE);

		Assert.notNull(privilege, ErrorMessages.OBJECT_NOT_FOUND);

		privilege.removeUsers();
	}

	@Override
	public Privilege mapDTOToEntity(PrivilegeDTO dto) {
		return mapper.map(dto, Privilege.class);
	}

	@Override
	public PrivilegeDTO mapEntityToDTO(Privilege privilege) {
		return mapper.map(privilege, PrivilegeDTO.class);
	}

}
