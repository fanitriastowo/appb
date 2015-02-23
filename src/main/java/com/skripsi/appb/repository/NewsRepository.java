package com.skripsi.apsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skripsi.apsb.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

	long count();

	News findTitleById(Long newsId);

}
