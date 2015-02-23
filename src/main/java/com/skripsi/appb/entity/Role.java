package com.skripsi.apsb.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skripsi.apsb.entity.base.BaseEntity;

@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	@OneToMany(mappedBy = "role")
	private List<News> news;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

}
