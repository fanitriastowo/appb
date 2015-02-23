package com.skripsi.apsb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skripsi.apsb.entity.Role;
import com.skripsi.apsb.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role findByName(String role) {
		return roleRepository.findByName(role);
	}
}
