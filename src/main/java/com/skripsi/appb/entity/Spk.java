package com.skripsi.apsb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skripsi.apsb.entity.base.BaseEntity;

@Entity
@Table(name = "SPK")
public class Spk extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	private Double rapot1;
	private Double rapot2;
	private Double jarak;
	private Double penghasilanOrtu;
	private Double kendaraan;
	private Double jumlahS;
	private Double vectorS;
	private Double vectorV;

	@OneToOne
	@JoinColumn(name = "news_id")
	private News news;

	@OneToOne
	@JoinColumn(name = "bobot_id")
	private BobotSpk bobotSpk;

	public User getUser() {
		return user;
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

	public Double getJumlahS() {
		return jumlahS;
	}

	public Double getVectorS() {
		return vectorS;
	}

	public Double getVectorV() {
		return vectorV;
	}

	public void setUser(User user) {
		this.user = user;
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

	public void setJumlahS(Double jumlahS) {
		this.jumlahS = jumlahS;
	}

	public void setVectorS(Double vectorS) {
		this.vectorS = vectorS;
	}

	public void setVectorV(Double vectorV) {
		this.vectorV = vectorV;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public BobotSpk getBobotSpk() {
		return bobotSpk;
	}

	public void setBobotSpk(BobotSpk bobotSpk) {
		this.bobotSpk = bobotSpk;
	}

}
