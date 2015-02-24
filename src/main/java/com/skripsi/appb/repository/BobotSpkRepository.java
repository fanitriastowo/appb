package com.skripsi.apsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skripsi.apsb.entity.BobotSpk;
import com.skripsi.apsb.entity.News;

@Repository
public interface BobotSpkRepository extends JpaRepository<BobotSpk, Long> {

	BobotSpk findOneByNews(News one);

}
