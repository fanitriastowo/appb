package com.skripsi.apsb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skripsi.apsb.entity.Kendaraan;

public interface KendaraanRepository extends JpaRepository<Kendaraan, Long> {

	Kendaraan findOneByName(String kendaraan);

	@Query("SELECT K FROM Kendaraan K WHERE K.name != :kendaraan")
	List<Kendaraan> findAllWithout(@Param("kendaraan") String kendaraan);
	
	@Query("SELECT K FROM Kendaraan K WHERE K.name != :kendaraan")
	Page<Kendaraan> findAllWithout(@Param("kendaraan") String kendaraan, Pageable pageable);

}
