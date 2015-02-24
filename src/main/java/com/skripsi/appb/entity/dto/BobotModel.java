package com.skripsi.apsb.entity.dto;

public class BobotModel {

	private Double raport1;
	private Double raport2;
	private Double jarak;
	private Double penghasilanOrtu;
	private Double kendaraan;
	private Double jumlahBobot;

	// cari jumlahbobot
	public Double getJumlahBobot() {
		jumlahBobot = raport1 + raport2 + jarak + penghasilanOrtu + kendaraan;
		return jumlahBobot;
	}

	public Double getRaport1() {
		return raport1;
	}

	public void setRaport1(Double raport1) {
		this.raport1 = raport1;
	}

	public Double getRaport2() {
		return raport2;
	}

	public void setRaport2(Double raport2) {
		this.raport2 = raport2;
	}

	public Double getJarak() {
		return jarak;
	}

	public void setJarak(Double jarak) {
		this.jarak = jarak;
	}

	public Double getPenghasilanOrtu() {
		return penghasilanOrtu;
	}

	public void setPenghasilanOrtu(Double penghasilanOrtu) {
		this.penghasilanOrtu = penghasilanOrtu;
	}

	public Double getKendaraan() {
		return kendaraan;
	}

	public void setKendaraan(Double kendaraan) {
		this.kendaraan = kendaraan;
	}
}
