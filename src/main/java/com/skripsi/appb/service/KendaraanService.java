package com.skripsi.apsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.skripsi.apsb.entity.Kendaraan;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.repository.KendaraanRepository;
import com.skripsi.apsb.repository.UserRepository;

@Service
public class KendaraanService {

	@Autowired
	private KendaraanRepository kendaraanRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Kendaraan> findAll() {
		return kendaraanRepository.findAll();
	}

	public void save(Kendaraan kendaraan) {
		kendaraanRepository.save(kendaraan);
	}

	public void delete(long id) {
		kendaraanRepository.delete(id);
	}

	public Kendaraan findOne(long id) {
		return kendaraanRepository.findOne(id);
	}

	public Kendaraan update(Kendaraan updated) {
		Kendaraan kendaraan = findOne(updated.getId());
		kendaraan.update(updated.getId(), updated.getName(), updated.getPoint());
		kendaraanRepository.save(kendaraan);
		return kendaraan;
	}

	public void delete(Kendaraan kendaraan) {
		Kendaraan kendaraan2 = kendaraanRepository.findOneByName("Pilih");
		List<User> userList = userRepository.findAllBykendaraan(kendaraan);
		for (User user : userList) {
			User userUpdated = new User();
			userUpdated.setId(user.getId());
			userUpdated.setAddress(user.getAddress());
			userUpdated.setBirthday(user.getBirthday());
			userUpdated.setEmail(user.getEmail());
			userUpdated.setEnabled(user.isEnabled());
			userUpdated.setFamilyName(user.getFamilyName());
			userUpdated.setJarak(user.getJarak());
			userUpdated.setKendaraan(kendaraan2);
			userUpdated.setNickname(user.getNickname());
			userUpdated.setNis(user.getNis());
			userUpdated.setPassword(user.getPassword());
			userUpdated.setPenghasilanOrtu(user.getPenghasilanOrtu());
			userUpdated.setPicture(user.getPicture());
			userUpdated.setRapot1(user.getRapot1());
			userUpdated.setRapot2(user.getRapot2());
			userUpdated.setRoles(user.getRoles());
			userUpdated.setUsername(user.getUsername());
			userRepository.save(userUpdated);
		}
		kendaraanRepository.delete(kendaraan);
	}

	public List<Kendaraan> findAllWithout(String kendaraan) {
		return kendaraanRepository.findAllWithout(kendaraan);
	}

	public Page<Kendaraan> findAllWithoutLimit(String kendaraan, int page, int size) {
		return kendaraanRepository.findAllWithout(kendaraan, new PageRequest(page - 1, size, Direction.ASC, "point"));
	}
}
