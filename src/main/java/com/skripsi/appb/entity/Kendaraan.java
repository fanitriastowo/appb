package com.skripsi.apsb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.skripsi.apsb.entity.base.BaseEntity;

@Entity
@Table(name = "KENDARAAN")
public class Kendaraan extends BaseEntity {

	@Size(min = 1, message = "Minimal 1 karakter")
	@NotNull(message = "Jenis kendaraan harap diisi")
	private String name;

	@NotNull(message = "Point harap diisi")
	private Integer point;

	@OneToMany(mappedBy = "kendaraan", cascade = CascadeType.MERGE)
	private List<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public List<User> getUserDetails() {
		return users;
	}

	public void setUserDetails(List<User> users) {
		this.users = users;
	}

	public void update(Long id, String name, Integer point) {
		super.setId(id);
		this.name = name;
		this.point = point;
	}

}
