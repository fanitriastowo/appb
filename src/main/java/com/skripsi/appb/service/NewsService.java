package com.skripsi.apsb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.Role;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.repository.NewsRepository;
import com.skripsi.apsb.repository.RoleRepository;
import com.skripsi.apsb.repository.UserRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public List<News> findAll() {
		return newsRepository.findAll();
	}

	public News save(News news) {
		Role role = roleRepository.findByName("ROLE_ADMIN");
		news.setPublishedDate(new Date());
		news.setRole(role);
		news.setCompleted(false);
		return newsRepository.save(news);
	}

	public void delete(Long id) {
		News news = newsRepository.findOne(id);
		List<User> list = userRepository.findAllByNews(news);
		for (User user : list) {
			user.setNews(null);
			user.setJoined(false);
			userRepository.save(user);
		}
		newsRepository.delete(id);
	}

	public News findOne(Long id) {
		return newsRepository.findOne(id);
	}

	public News update(News news) {
		News updated = newsRepository.findOne(news.getId());
		updated.update(news.getId(), news.getTitle(), news.getDescription(), news.getContent(), news.getPublishedDate(), news.getExpiredDate(), news.getRole(), news.isCompleted(), news.getUsers(), news.getQuote());
		return newsRepository.save(updated);
	}

	public Page<News> findAllLimit(int page, int size) {
		return newsRepository.findAll(new PageRequest(page - 1, size, Direction.DESC, "publishedDate"));
	}

	public News findTitleById(Long newsId) {
		return newsRepository.findTitleById(newsId);
	}

}
