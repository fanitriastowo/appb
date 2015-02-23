package com.skripsi.apsb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skripsi.apsb.entity.Kendaraan;
import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.Role;
import com.skripsi.apsb.entity.Spk;
import com.skripsi.apsb.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	byte[] findPictureByUsername(String username);

	User findOneById(Long id);

	List<User> findAllBykendaraan(Kendaraan kendaraan);

	List<User> findAllByRoles(List<Role> roles);

	Page<User> findAllByRoles(List<Role> roles, Pageable pageable);

	Long countByNews(News news);

	List<User> findAllByNews(News news);

	List<User> findAllByNews(News news, Pageable pageable);

	List<User> findAllByRolesAndNews(List<Role> roles, News news);

	User findOneByNews(News news);

	User findOneByNis(Integer nis);

	User findOneBySpk(Spk spk);

	List<User> findAllByNewsAndKet(News news, String string);

}
