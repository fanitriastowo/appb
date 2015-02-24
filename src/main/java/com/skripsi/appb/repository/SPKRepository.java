package com.skripsi.apsb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.Spk;
import com.skripsi.apsb.entity.User;

public interface SPKRepository extends JpaRepository<Spk, Long> {

	Spk findOneByUser(User user);

	Spk findOneByUserAndNews(User user, News one);

	List<Spk> findAllByNews(News one);
	
	List<Spk> findAllByNews(News one, Pageable pageable);
	
	List<Spk> findAllByNewsOrderByVectorVDesc(News one);
	
}
