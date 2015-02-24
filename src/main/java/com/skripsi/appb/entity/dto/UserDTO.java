package com.skripsi.apsb.entity.dto;

import java.util.Date;

import com.skripsi.apsb.entity.User;

public class UserDTO {

	private Long id;

	private String username;

	private String password;

	private String email;

	private Integer nis;

	private String nickname;

	private byte[] picture;

	private String familyName;

	private String address;

	private Date birthday;

	private Double rapot1;

	private Double rapot2;

	private String kendaraan;

	private Double jarak;

	private Long penghasilanOrtu;

	private String phone;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.nis = user.getNis();
		this.nickname = user.getNickname();
		this.picture = user.getPicture();
		this.familyName = user.getFamilyName();
		this.address = user.getAddress();
		this.birthday = user.getBirthday();
		this.rapot1 = user.getRapot1();
		this.rapot2 = user.getRapot2();
		this.kendaraan = user.getKendaraan().getName();
		this.jarak = user.getJarak();
		this.phone = user.getPhone();
		this.penghasilanOrtu = user.getPenghasilanOrtu();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getNis() {
		return nis;
	}

	public void setNis(Integer nis) {
		this.nis = nis;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getRapot1() {
		return rapot1;
	}

	public void setRapot1(Double rapot1) {
		this.rapot1 = rapot1;
	}

	public Double getRapot2() {
		return rapot2;
	}

	public void setRapot2(Double rapot2) {
		this.rapot2 = rapot2;
	}

	public String getKendaraan() {
		return kendaraan;
	}

	public void setKendaraan(String kendaraan) {
		this.kendaraan = kendaraan;
	}

	public Double getJarak() {
		return jarak;
	}

	public void setJarak(Double jarak) {
		this.jarak = jarak;
	}

	public Long getPenghasilanOrtu() {
		return penghasilanOrtu;
	}

	public void setPenghasilanOrtu(Long penghasilanOrtu) {
		this.penghasilanOrtu = penghasilanOrtu;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
