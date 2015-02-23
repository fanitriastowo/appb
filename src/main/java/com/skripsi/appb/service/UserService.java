package com.skripsi.apsb.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skripsi.apsb.entity.Kendaraan;
import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.Role;
import com.skripsi.apsb.entity.Spk;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.entity.dto.UserDTO;
import com.skripsi.apsb.repository.KendaraanRepository;
import com.skripsi.apsb.repository.RoleRepository;
import com.skripsi.apsb.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private KendaraanRepository kendaraanRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(long id) {
		return userRepository.findOne(id);
	}

	public Page<User> findAllByRolesUserLimit(Integer pageNumber, Integer size) {
		Role roleUser = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		roles.add(roleUser);
		PageRequest pageRequest = new PageRequest(pageNumber - 1, size, Direction.ASC, "nickname");
		Page<User> userList = userRepository.findAllByRoles(roles, pageRequest);
		return userList;
	}

	@Transactional
	public void register(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setJarak(2.0);
		user.setPenghasilanOrtu(200000l);
		user.setKendaraan(kendaraanRepository.findOneByName("Pilih"));
		user.setRoles(roles);
		user.setEnabled(true);
		user.setJoined(false);
		user.setComplete(false);
		userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public byte[] loadImage(String username) {
		return userRepository.findPictureByUsername(username);
	}

	@Transactional
	public void delete(long id) {
		userRepository.delete(id);
	}

	public User findOneByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	public User updateUserAccount(User updated) {
		User user = userRepository.findOne(updated.getId());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.update(updated.getId(), updated.getUsername(), encoder.encode(updated.getPassword()), updated.getEmail(), user.getNis(), user.getNickname(), user.getFamilyName(), user.getAddress(),
				user.getBirthday(), user.getRapot1(), user.getRapot2(), user.getJarak(), user.getPenghasilanOrtu(), user.getKendaraan(), user.getRoles(), user.isJoined(), user.isComplete(),
				user.getGender(), user.getPhone(), user.getAgama(), user.isScanned());
		userRepository.save(user);
		return user;
	}

	@Transactional
	public User updateUserDetail(UserDTO updated) {
		User user = userRepository.findOne(updated.getId());
		Kendaraan kendaraan = kendaraanRepository.findOneByName(updated.getKendaraan());
		user.update(updated.getId(), user.getUsername(), user.getPassword(), user.getEmail(), updated.getNis(), updated.getNickname(), updated.getFamilyName(), updated.getAddress(),
				updated.getBirthday(), updated.getRapot1(), updated.getRapot2(), updated.getJarak(), updated.getPenghasilanOrtu(), kendaraan, user.getRoles(), user.isJoined(), true, user.getGender(),
				updated.getPhone(), user.getAgama(), user.isScanned());
		userRepository.save(user);
		return user;
	}

	public User findOneById(Long id) {
		return userRepository.findOneById(id);
	}

	public byte[] findPictureByUsername(String username) {
		return userRepository.findPictureByUsername(username);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public Long countByNews(News news) {
		return userRepository.countByNews(news);
	}

	public List<User> findAllByNews(News news) {
		return userRepository.findAllByNews(news);
	}

	public List<User> findAllByRolesUser() {
		Role roleUser = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		roles.add(roleUser);
		List<User> usersList = userRepository.findAllByRoles(roles);
		return usersList;
	}

	public List<User> findAllByNewsLimit(News news, Integer pageNumber, Integer pageDisplayLength) {
		return userRepository.findAllByNews(news, new PageRequest(pageNumber, pageDisplayLength));
	}

	public Long countUser() {
		return userRepository.count() - 1;
	}

	public List<User> findAllByRolesUserAndNews(News news) {
		Role roleUser = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		roles.add(roleUser);
		List<User> list = userRepository.findAllByRolesAndNews(roles, news);
		return list;
	}

	public User findOneByNews(News news) {
		return userRepository.findOneByNews(news);
	}

	public User findOneByNis(Integer nis) {
		return userRepository.findOneByNis(nis);
	}

	public User findOneBySpk(Spk spk) {
		return userRepository.findOneBySpk(spk);
	}

	public List<User> findAllByNewsAndKet(News news) {
		return userRepository.findAllByNewsAndKet(news, "Diterima");
	}

}
