package com.skripsi.apsb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skripsi.apsb.entity.base.BaseEntity;

@Entity
@Table(name = "BOBOT")
public class BobotSpk extends BaseEntity {

	private Integer bRapot1;
	private Integer bRapot2;
	private Integer bKendaraan;
	private Integer bJarak;
	private Integer bPenghasilanOrtu;

	@OneToOne
	@JoinColumn(name = "news_id")
	private News news;

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

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

}
