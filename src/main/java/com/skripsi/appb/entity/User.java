package com.skripsi.apsb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.skripsi.apsb.annotation.UniqueUsername;
import com.skripsi.apsb.entity.base.BaseEntity;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	@Size(min = 3, message = "Minimal 3 Character")
	@NotBlank(message = "Username cannot empty")
	@UniqueUsername(message = "Username sudah ada")
	private String username;

	@Size(min = 3, message = "Minimal 3 Character")
	@NotBlank(message = "Password cannot empty")
	private String password;

	@NotBlank
	@Email
	private String email;

	private Gender gender;

	private Agama agama;

	private String phone;

	private boolean enabled;

	private boolean joined;

	private boolean complete;

	@ManyToMany
	@JoinTable
	private List<Role> roles;

	private Integer nis;

	private String nickname;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = Integer.MAX_VALUE)
	private byte[] picture;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = Integer.MAX_VALUE)
	private byte[] scan;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = Integer.MAX_VALUE)
	private byte[] scan2;

	private boolean scanned;

	private String familyName;

	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(length = Integer.MAX_VALUE)
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Past(message = "Tanggal lahir harus sebelum hari ini")
	private Date birthday;

	private Double rapot1;

	private Double rapot2;

	private Double jarak;

	private Long penghasilanOrtu;

	@ManyToOne
	@JoinColumn(name = "kendaraan_id")
	private Kendaraan kendaraan;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "spk_id")
	private Spk spk;
	
	private String ket;

	@ManyToOne
	@JoinColumn(name = "news_id")
	private News news;
	
	@Transient
	private Object image;
	
	@Transient
	private Object imageScan;
	
	@Transient
	private Object imageScan2;

	// Enum

	public enum Gender {
		L, P
	}

	public enum Agama {
		Islam, Kristen, Katolik, Hindu, Budha, Lain
	}

	// Getter Setter

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public Kendaraan getKendaraan() {
		return kendaraan;
	}

	public void setKendaraan(Kendaraan kendaraan) {
		this.kendaraan = kendaraan;
	}

	public Spk getSpk() {
		return spk;
	}

	public void setSpk(Spk spk) {
		this.spk = spk;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public boolean isJoined() {
		return joined;
	}

	public void setJoined(boolean joined) {
		this.joined = joined;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getScan() {
		return scan;
	}

	public void setScan(byte[] scan) {
		this.scan = scan;
	}

	public Agama getAgama() {
		return agama;
	}

	public void setAgama(Agama agama) {
		this.agama = agama;
	}

	public byte[] getScan2() {
		return scan2;
	}

	public void setScan2(byte[] scan2) {
		this.scan2 = scan2;
	}

	public boolean isScanned() {
		return scanned;
	}

	public void setScanned(boolean scanned) {
		this.scanned = scanned;
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

	public String getKet() {
		return ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}

	public Object getImage() {
		return image;
	}

	public void setImage(Object image) {
		this.image = image;
	}

	public Object getImageScan() {
		return imageScan;
	}

	public void setImageScan(Object imageScan) {
		this.imageScan = imageScan;
	}

	public Object getImageScan2() {
		return imageScan2;
	}

	public void setImageScan2(Object imageScan2) {
		this.imageScan2 = imageScan2;
	}

	public void update(Long id, String username, String password, String email, Integer nis, String nickname, String familyName, String address, Date birthday, Double rapot1, Double rapot2,
			Double jarak, Long penghasilanOrtu, Kendaraan kendaraan, List<Role> roles, boolean joined, boolean completed, Gender gender, String phone, Agama agama, boolean scanned) {
		super.setId(id);
		this.username = username;
		this.password = password;
		this.email = email;
		this.nis = nis;
		this.nickname = nickname;
		this.familyName = familyName;
		this.address = address;
		this.birthday = birthday;
		this.rapot1 = rapot1;
		this.rapot2 = rapot2;
		this.kendaraan = kendaraan;
		this.jarak = jarak;
		this.penghasilanOrtu = penghasilanOrtu;
		this.roles = roles;
		this.joined = joined;
		this.complete = completed;
		this.gender = gender;
		this.phone = phone;
		this.scanned = scanned;
	}
}
