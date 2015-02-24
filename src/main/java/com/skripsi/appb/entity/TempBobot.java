package com.skripsi.apsb.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.skripsi.apsb.entity.base.BaseEntity;

@Entity
@Table(name = "TEMP_BOBOT")
public class TempBobot extends BaseEntity {

	private Integer bRapot1;
	private Integer bRapot2;
	private Integer bKendaraan;
	private Integer bJarak;
	private Integer bPenghasilanOrtu;

	public Integer getbRapot1() {
		return bRapot1;
	}

	public void setbRapot1(Integer bRapot1) {
		this.bRapot1 = bRapot1;
	}

	public Integer getbRapot2() {
		return bRapot2;
	}

	public void setbRapot2(Integer bRapot2) {
		this.bRapot2 = bRapot2;
	}

	public Integer getbKendaraan() {
		return bKendaraan;
	}

	public void setbKendaraan(Integer bKendaraan) {
		this.bKendaraan = bKendaraan;
	}

	public Integer getbJarak() {
		return bJarak;
	}

	public void setbJarak(Integer bJarak) {
		this.bJarak = bJarak;
	}

	public Integer getbPenghasilanOrtu() {
		return bPenghasilanOrtu;
	}

	public void setbPenghasilanOrtu(Integer bPenghasilanOrtu) {
		this.bPenghasilanOrtu = bPenghasilanOrtu;
	}

	public void update(Long id, Integer bJarak, Integer bKendaraan, Integer bPenghasilanOrtu, Integer bRapot1, Integer bRapot2) {
		super.setId(id);
		this.bJarak = bJarak;
		this.bKendaraan = bKendaraan;
		this.bPenghasilanOrtu = bPenghasilanOrtu;
		this.bRapot1 = bRapot1;
		this.bRapot2 = bRapot2;
	}
}
