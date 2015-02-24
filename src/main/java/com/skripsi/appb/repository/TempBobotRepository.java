package com.skripsi.apsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skripsi.apsb.entity.TempBobot;

@Repository
public interface TempBobotRepository extends JpaRepository<TempBobot, Long> {

}
