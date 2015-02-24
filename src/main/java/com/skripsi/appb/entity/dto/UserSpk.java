package com.skripsi.apsb.entity.dto;

import java.io.Serializable;

public class UserSpk implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nis;
	private String nickname;
	private String email;
	private Double rapot1;
	private Double rapot2;
	private Double jarak;
	private Double penghasilanOrtu;
	private Double kendaraan;
	private Double jumlahS;
	private Double vectorS;
	private Double vectorV;

	public Integer getNis() {
		return nis;
	}

	public void setNis(Integer nis) {
		this.nis = nis;
	}

	public Double getVectorV() {
		return vectorV;
	}

	public void setVectorV(Double vectorV) {
		this.vectorV = vectorV;
	}

	public Double getJumlahS() {
		return jumlahS;
	}

	public Double getVectorS() {
		return vectorS;
	}

	public void setJumlahS(Double jumlahS) {
		this.jumlahS = jumlahS;
	}

	public void setVectorS(Double vectorS) {
		this.vectorS = vectorS;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public Double getRapot1() {
		return rapot1;
	}

	public Double getRapot2() {
		return rapot2;
	}

	public Double getJarak() {
		return jarak;
	}

	public Double getPenghasilanOrtu() {
		return penghasilanOrtu;
	}

	public Double getKendaraan() {
		return kendaraan;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRapot1(Double rapot1) {
		this.rapot1 = rapot1;
	}

	public void setRapot2(Double rapot2) {
		this.rapot2 = rapot2;
	}

	public void setJarak(Double jarak) {
		this.jarak = jarak;
	}

	public void setPenghasilanOrtu(Double penghasilanOrtu) {
		this.penghasilanOrtu = penghasilanOrtu;
	}

	public void setKendaraan(Double kendaraan) {
		this.kendaraan = kendaraan;
	}

}
