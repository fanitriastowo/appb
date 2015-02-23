package com.skripsi.apsb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

import org.hibernate.annotations.Type;

import com.skripsi.apsb.entity.base.BaseEntity;

@Entity
@Table(name = "NEWS")
public class News extends BaseEntity {

	@Column(length = 1000)
	private String title;

	@Column(length = 1000)
	private String description;

	@Lob
	@Column(length = Integer.MAX_VALUE)
	@Type(type = "org.hibernate.type.StringClobType")
	private String content;

	@Column(name = "published_Date")
	@Temporal(TemporalType.DATE)
	private Date publishedDate;

	@Column(name = "expiredDate")
	@Temporal(TemporalType.DATE)
	@Future(message = "Tanggal Berakhir Harus Sesudah Hari Ini")
	private Date expiredDate;
	
	private Integer quote;

	private boolean completed;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
	private List<User> users;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Integer getQuote() {
		return quote;
	}

	public void setQuote(Integer quote) {
		this.quote = quote;
	}

	public void update(Long id, String title2, String description2, String content2, Date publishedDate2, Date expiredDate2, Role role2, boolean completed, List<User> users, Integer quote) {
		super.setId(id);
		this.title = title2;
		this.description = description2;
		this.content = content2;
		this.publishedDate = publishedDate2;
		this.expiredDate = expiredDate2;
		this.role = role2;
		this.completed = completed;
		this.users = users;
		this.quote = quote;
	}

}
