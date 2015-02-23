package com.skripsi.apsb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skripsi.apsb.entity.BobotSpk;
import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.Spk;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.service.BobotSpkService;
import com.skripsi.apsb.service.NewsService;
import com.skripsi.apsb.service.SPKService;
import com.skripsi.apsb.service.TempBobotService;
import com.skripsi.apsb.service.UserService;

@Controller
@RequestMapping("/master")
public class AdminNewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;

	@Autowired
	private SPKService spkService;

	@Autowired
	private TempBobotService bobotTempService;

	@Autowired
	private BobotSpkService bobotSpkService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		binder.registerCustomEditor(Date.class, "publishedDate", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Date.class, "expiredDate", new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute("news")
	public News construct() {
		return new News();
	}

	@RequestMapping(value = "/news-admin/detail/{id}", method = RequestMethod.GET)
	public String newsDetailById(@PathVariable(value = "id") Long id, Model model) {
		News news = newsService.findOne(id);
		Long count = userService.countByNews(news);
		model.addAttribute("news", news);
		model.addAttribute("countUser", count);
		model.addAttribute("userList", userService.findAllByRolesUserAndNews(news));
		return "news-detail";
	}

	@RequestMapping(value = "/news-admin/{page}", method = RequestMethod.GET)
	public String news(Model model, @PathVariable(value = "page") int page) {
		Page<News> list = newsService.findAllLimit(page, 10);

		int current = list.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, list.getTotalPages());

		model.addAttribute("current", current);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("newsAdmin", list);
		return "news-admin";
	}

	@RequestMapping(value = "/news-hitung/{page}", method = RequestMethod.GET)
	public String hitung(Model model, @PathVariable(value = "page") int page) {
		Page<News> list = newsService.findAllLimit(page, 10);

		int current = list.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, list.getTotalPages());

		model.addAttribute("current", current);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("newsAdmin", list);
		return "news-hitung";
	}

	@RequestMapping(value = "/news-admin/save", method = RequestMethod.POST)
	public String addNews(@ModelAttribute(value = "news") News news) {
		newsService.save(news);
		return "redirect:/master/news-admin/1.apsb";
	}

	@RequestMapping(value = "/news-admin/update/{id}", method = RequestMethod.GET)
	public String prepareUpdate(@PathVariable(value = "id") Long id, Model model) {
		News news = newsService.findOne(id);
		model.addAttribute("newsUpdate", news);
		return "news-update";
	}

	@RequestMapping(value = "/news-admin/update/save", method = RequestMethod.POST)
	public String updateSave(@ModelAttribute("newsUpdate") News news) {
		newsService.update(news);
		return "redirect:/master/news-admin/1.apsb";
	}

	@RequestMapping(value = "/news-admin/delete/{id}")
	public String deleteNews(@PathVariable(value = "id") Long id) {
		newsService.delete(id);
		return "redirect:/master/news-admin/1.apsb";
	}

	@RequestMapping(value = "/final/{newsId}", method = RequestMethod.GET)
	public String hasilFinal(Model model, @PathVariable(value = "newsId") Long newsId) {
		News one = newsService.findOne(newsId);
		List<Spk> userSpk = spkService.findAllByNewsDesc(one);
		BobotSpk bobotSpk = bobotSpkService.findOneByNews(one);
		model.addAttribute("spks", userSpk);
		model.addAttribute("bobot", bobotSpk);
		return "final";
	}

	@RequestMapping(value = "/final/exportPdf/{newsId}", method = RequestMethod.GET)
	public String cetakNews(ModelMap model, @PathVariable(value = "newsId") Long newsId) {
		News news = newsService.findOne(newsId);
		model.addAttribute("dataSource", spkService.findAllByNewsDesc(news));
		return "newsPdf";
	}

	@RequestMapping(value = "/final/exportDetailPdf/{newsId}", method = RequestMethod.GET)
	public String cetakDetailNews(ModelMap model, @PathVariable(value = "newsId") Long newsId) {
		News news = newsService.findOne(newsId);
		List<User> data = null;
		List<User> result = null;
		data = userService.findAllByNewsAndKet(news);
		ListIterator<User> listIter = data.listIterator();
		result = new ArrayList<>();
		ImageIcon image;
		ImageIcon imageScan;
		ImageIcon imageScan2;
		while (listIter.hasNext()) {
			User user = listIter.next();
			if (user.getPicture() != null) {
				image = new ImageIcon(user.getPicture());
			} else {
				image = new ImageIcon(getClass().getResource("/com/skripsi/apsb/util/blank_user.png"));
			}

			if (user.getScan() != null) {
				imageScan = new ImageIcon(user.getScan());
			} else {
				imageScan = new ImageIcon(getClass().getResource("/com/skripsi/apsb/util/blank.jpg"));
			}

			if (user.getScan2() != null) {
				imageScan2 = new ImageIcon(user.getScan2());
			} else {
				imageScan2 = new ImageIcon(getClass().getResource("/com/skripsi/apsb/util/blank.jpg"));
			}
			
			user.setImage(image.getImage());
			user.setImageScan(imageScan.getImage());
			user.setImageScan2(imageScan2.getImage());
			result.add(user);
		}
		model.addAttribute("dataSource", result);
		return "newsDetailPdf";

	}
}
