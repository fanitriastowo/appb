package com.skripsi.apsb.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.service.NewsService;
import com.skripsi.apsb.service.SPKService;
import com.skripsi.apsb.service.UserService;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private SPKService spkService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		binder.registerCustomEditor(Date.class, "publishedDate", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Date.class, "expiredDate", new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute(value = "news")
	public News construct() {
		return new News();
	}

	@RequestMapping(value = "/news/{page}", method = RequestMethod.GET)
	public String news(Model model, @PathVariable(value = "page") int page, Principal principal) {
		Page<News> news = newsService.findAllLimit(page, 10);
		int current = news.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, news.getTotalPages());
		String username = principal.getName();
		User user = userService.findOneByUsername(username);
		
		model.addAttribute("newsList", news);
		model.addAttribute("current", current);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("user", user);
		model.addAttribute("sekarang", new Date());
		return "news-user";
	}

	@RequestMapping(value = "/news-anonim/{page}", method = RequestMethod.GET)
	public String newsAnonymous(Model model, @PathVariable(value = "page") int page) {
		Page<News> news = newsService.findAllLimit(page, 10);
		int current = news.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, news.getTotalPages());
		model.addAttribute("newsList", news);
		model.addAttribute("current", current);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		return "news-anonim";
	}

	@RequestMapping(value = "/news/join/{id}")
	public String joinNews(Principal principal, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		News news = newsService.findOne(id);
		if (user.isJoined()) {
			redirectAttributes.addFlashAttribute("sudahTerdaftar", true);
			return "redirect:/news/1.apsb";
		} else if (!user.isComplete()) {
			redirectAttributes.addFlashAttribute("notCompleted", true);
			return "redirect:/news/1.apsb";
		} else if (!user.isScanned()) {
			redirectAttributes.addFlashAttribute("isScanned", true);
			return "redirect:/news/1.apsb";
		}
		user.setNews(news);
		user.setJoined(true);
		userService.save(user);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/news/1.apsb";
	}

	@RequestMapping(value = "/news/detail/{newsId}", method = RequestMethod.GET)
	public String detail(@PathVariable(value = "newsId") Long id, Model model) {
		News news = newsService.findOne(id);
		model.addAttribute("news", news);
		return "user-news-detail";
	}
	
	@RequestMapping(value = "/news/hasil/{newsId}", method = RequestMethod.GET)
	public String hasil(@PathVariable(value = "newsId") Long newsId, Model model) {
		News news = newsService.findOne(newsId);
		model.addAttribute("spks", spkService.findAllByNewsDesc(news));
		return "hasil";
	}
}
