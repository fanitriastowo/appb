package com.skripsi.apsb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skripsi.apsb.entity.BobotSpk;
import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.Spk;
import com.skripsi.apsb.entity.TempBobot;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.entity.dto.BobotModel;
import com.skripsi.apsb.entity.dto.NormalisasiBobot;
import com.skripsi.apsb.entity.dto.UserSpk;
import com.skripsi.apsb.service.BobotSpkService;
import com.skripsi.apsb.service.NewsService;
import com.skripsi.apsb.service.SPKService;
import com.skripsi.apsb.service.TempBobotService;
import com.skripsi.apsb.service.UserService;
import com.skripsi.apsb.util.RoundDecimal;

@Controller
@RequestMapping(value = "/master")
public class SPKController {

	@Autowired
	private UserService userService;

	@Autowired
	private SPKService spkService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private TempBobotService tempBobotService;

	@Autowired
	private BobotSpkService bobotSpkService;

	/**
	 * Fase 1
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hitungrekomendasi/{newsId}", method = RequestMethod.GET)
	public String spkMenu(Model model, HttpSession session, @PathVariable(value = "newsId") Long id) {
		News news = newsService.findOne(id);
		if (session.getAttribute("bobotSession") != null) {
			session.removeAttribute("bobotSession");
		}
		if (session.getAttribute("news") != null) {
			session.removeAttribute("news");
		}
		TempBobot tempBobot = tempBobotService.findOne(1l);
		model.addAttribute("userList", userService.findAllByRolesUserAndNews(news));
		model.addAttribute("bobot", tempBobot);
		BobotModel bobotModel = new BobotModel();
		bobotModel.setRaport1(Double.valueOf(tempBobot.getbRapot1()));
		bobotModel.setRaport2(Double.valueOf(tempBobot.getbRapot2()));
		bobotModel.setJarak(Double.valueOf(tempBobot.getbJarak()));
		bobotModel.setKendaraan(Double.valueOf(tempBobot.getbKendaraan()));
		bobotModel.setPenghasilanOrtu(Double.valueOf(tempBobot.getbPenghasilanOrtu()));
		session.setAttribute("news", news);
		session.setAttribute("bobotSession", bobotModel);
		return "hitungrekomendasi";
	}

	/**
	 * Fase 2
	 * 
	 * @param tempBobot
	 * @return
	 */
	@RequestMapping(value = "/hitungrekomendasi/bobot", method = RequestMethod.POST)
	public String tentukanBobot(@ModelAttribute("bobot") TempBobot tempBobot, HttpSession session) {
		News news = (News) session.getAttribute("news");
		tempBobotService.update(tempBobot);
		return "redirect:/master/hitungrekomendasi/" + news.getId() + ".apsb";
	}

	@RequestMapping(value = "/hitungrekomendasi2", method = RequestMethod.GET)
	public String fase2(Model model, HttpSession session) {
		BobotModel bobotModel = (BobotModel) session.getAttribute("bobotSession");
		NormalisasiBobot normalisasi = new NormalisasiBobot();

		// bobot normalisasi
		Double raport1 = bobotModel.getRaport1() / bobotModel.getJumlahBobot();
		Double raport2 = bobotModel.getRaport2() / bobotModel.getJumlahBobot();
		Double jarak = bobotModel.getJarak() / bobotModel.getJumlahBobot();
		Double kendaraan = bobotModel.getKendaraan() / bobotModel.getJumlahBobot();
		Double penghasilanOrtu = bobotModel.getPenghasilanOrtu() / bobotModel.getJumlahBobot();

		// keterangan
		normalisasi.setRaport1(RoundDecimal.doubleWithDecimalPlace(raport1, 4));
		normalisasi.setRaport2(RoundDecimal.doubleWithDecimalPlace(raport2, 4));
		normalisasi.setJarak(RoundDecimal.doubleWithDecimalPlace(jarak, 4));
		normalisasi.setKendaraan(RoundDecimal.doubleWithDecimalPlace(kendaraan, 4));
		normalisasi.setPenghasilanOrtu(RoundDecimal.doubleWithDecimalPlace(penghasilanOrtu, 4));

		// pemangkatan bobot ()
		News news = (News) session.getAttribute("news");
		List<User> users = userService.findAllByRolesUserAndNews(news);
		List<UserSpk> userSpk = new ArrayList<>();
		if (!userSpk.isEmpty()) {
			userSpk.clear();
		}

		// Cari jumlah S
		Double vectorS = 0.0;
		for (User user : users) {
			Double pangkatRaport1 = Math.pow(user.getRapot1(), raport1);
			Double pangkatRaport2 = Math.pow(user.getRapot2(), raport2);
			Double pangkatJarak = Math.pow(user.getJarak(), jarak);
			Double pangkatKendaraan = Math.pow(user.getKendaraan().getPoint(), kendaraan);
			Double pangkatPenghasilanOrtu = Math.pow(user.getPenghasilanOrtu(), -penghasilanOrtu);
			Double jumlahS = pangkatRaport1 * pangkatRaport2 * pangkatJarak * pangkatKendaraan * pangkatPenghasilanOrtu;
			vectorS += jumlahS;
		}

		// Cari Jumlah Vector S
		for (User user2 : users) {
			Double pangkatRaport1 = Math.pow(user2.getRapot1(), raport1);
			Double pangkatRaport2 = Math.pow(user2.getRapot2(), raport2);
			Double pangkatJarak = Math.pow(user2.getJarak(), jarak);
			Double pangkatKendaraan = Math.pow(user2.getKendaraan().getPoint(), kendaraan);
			Double pangkatPenghasilanOrtu = Math.pow(user2.getPenghasilanOrtu(), -penghasilanOrtu);
			Double jumlahS = pangkatRaport1 * pangkatRaport2 * pangkatJarak * pangkatKendaraan * pangkatPenghasilanOrtu;

			UserSpk userSpk1 = new UserSpk();
			userSpk1.setNis(user2.getNis());
			userSpk1.setNickname(user2.getNickname());
			userSpk1.setEmail(user2.getEmail());
			userSpk1.setRapot1(RoundDecimal.doubleWithDecimalPlace(pangkatRaport1, 3));
			userSpk1.setRapot2(RoundDecimal.doubleWithDecimalPlace(pangkatRaport2, 3));
			userSpk1.setJarak(RoundDecimal.doubleWithDecimalPlace(pangkatJarak, 3));
			userSpk1.setKendaraan(RoundDecimal.doubleWithDecimalPlace(pangkatKendaraan, 3));
			userSpk1.setPenghasilanOrtu(RoundDecimal.doubleWithDecimalPlace(pangkatPenghasilanOrtu, 3));
			userSpk1.setJumlahS(RoundDecimal.doubleWithDecimalPlace(jumlahS, 3));
			userSpk1.setVectorS(RoundDecimal.doubleWithDecimalPlace(vectorS, 3));
			userSpk.add(userSpk1);
		}
		model.addAttribute("userList", users);
		model.addAttribute("userSpk", userSpk);
		model.addAttribute("bobot", bobotModel);
		model.addAttribute("normalisasi", normalisasi);
		return "hitungrekomendasi2";
	}

	/**
	 * Fase 3
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hitungrekomendasi3", method = RequestMethod.GET)
	public String fase3menu(Model model, HttpSession session) {
		BobotModel bobotModel = (BobotModel) session.getAttribute("bobotSession");
		NormalisasiBobot normalisasi = new NormalisasiBobot();

		// bobot normalisasi
		Double raport1 = bobotModel.getRaport1() / bobotModel.getJumlahBobot();
		Double raport2 = bobotModel.getRaport2() / bobotModel.getJumlahBobot();
		Double jarak = bobotModel.getJarak() / bobotModel.getJumlahBobot();
		Double kendaraan = bobotModel.getKendaraan() / bobotModel.getJumlahBobot();
		Double penghasilanOrtu = bobotModel.getPenghasilanOrtu() / bobotModel.getJumlahBobot();

		// keterangan
		normalisasi.setRaport1(RoundDecimal.doubleWithDecimalPlace(raport1, 4));
		normalisasi.setRaport2(RoundDecimal.doubleWithDecimalPlace(raport2, 4));
		normalisasi.setJarak(RoundDecimal.doubleWithDecimalPlace(jarak, 4));
		normalisasi.setKendaraan(RoundDecimal.doubleWithDecimalPlace(kendaraan, 4));
		normalisasi.setPenghasilanOrtu(RoundDecimal.doubleWithDecimalPlace(penghasilanOrtu, 4));

		// pemangkatan bobot ()
		News news = (News) session.getAttribute("news");
		List<User> users = userService.findAllByRolesUserAndNews(news);
		List<UserSpk> userSpk = new ArrayList<>();
		if (!userSpk.isEmpty()) {
			userSpk.clear();
		}

		// Cari jumlah S
		Double vectorS = 0.0;
		for (User user : users) {
			Double pangkatRaport1 = Math.pow(user.getRapot1(), raport1);
			Double pangkatRaport2 = Math.pow(user.getRapot2(), raport2);
			Double pangkatJarak = Math.pow(user.getJarak(), jarak);
			Double pangkatKendaraan = Math.pow(user.getKendaraan().getPoint(), kendaraan);
			Double pangkatPenghasilanOrtu = Math.pow(user.getPenghasilanOrtu(), -penghasilanOrtu);
			Double jumlahS = pangkatRaport1 * pangkatRaport2 * pangkatJarak * pangkatKendaraan * pangkatPenghasilanOrtu;
			vectorS += jumlahS;
		}

		// Cari Jumlah Vector S
		for (User user2 : users) {
			Double pangkatRaport1 = Math.pow(user2.getRapot1(), raport1);
			Double pangkatRaport2 = Math.pow(user2.getRapot2(), raport2);
			Double pangkatJarak = Math.pow(user2.getJarak(), jarak);
			Double pangkatKendaraan = Math.pow(user2.getKendaraan().getPoint(), kendaraan);
			Double pangkatPenghasilanOrtu = Math.pow(user2.getPenghasilanOrtu(), -penghasilanOrtu);
			Double jumlahS = pangkatRaport1 * pangkatRaport2 * pangkatJarak * pangkatKendaraan * pangkatPenghasilanOrtu;

			UserSpk userSpk1 = new UserSpk();
			userSpk1.setNis(user2.getNis());
			userSpk1.setNickname(user2.getNickname());
			userSpk1.setEmail(user2.getEmail());
			userSpk1.setRapot1(RoundDecimal.doubleWithDecimalPlace(pangkatRaport1, 3));
			userSpk1.setRapot2(RoundDecimal.doubleWithDecimalPlace(pangkatRaport2, 3));
			userSpk1.setJarak(RoundDecimal.doubleWithDecimalPlace(pangkatJarak, 3));
			userSpk1.setKendaraan(RoundDecimal.doubleWithDecimalPlace(pangkatKendaraan, 3));
			userSpk1.setPenghasilanOrtu(RoundDecimal.doubleWithDecimalPlace(pangkatPenghasilanOrtu, 3));
			userSpk1.setJumlahS(RoundDecimal.doubleWithDecimalPlace(jumlahS, 3));
			userSpk1.setVectorS(RoundDecimal.doubleWithDecimalPlace(vectorS, 3));
			userSpk1.setVectorV(RoundDecimal.doubleWithDecimalPlace(jumlahS / vectorS, 3));
			userSpk.add(userSpk1);
		}
		model.addAttribute("userSpk", userSpk);
		model.addAttribute("bobot", bobotModel);
		model.addAttribute("normalisasi", normalisasi);
		return "hitungrekomendasi3";
	}

	@RequestMapping(value = "/hitungrekomendasi3/save", method = RequestMethod.GET)
	public String fase3save(Model model, HttpSession session) {
		BobotModel bobot = (BobotModel) session.getAttribute("bobotSession");

		NormalisasiBobot normalisasi = new NormalisasiBobot();

		// bobot normalisasi
		Double raport1 = bobot.getRaport1() / bobot.getJumlahBobot();
		Double raport2 = bobot.getRaport2() / bobot.getJumlahBobot();
		Double jarak = bobot.getJarak() / bobot.getJumlahBobot();
		Double kendaraan = bobot.getKendaraan() / bobot.getJumlahBobot();
		Double penghasilanOrtu = bobot.getPenghasilanOrtu() / bobot.getJumlahBobot();

		// keterangan
		normalisasi.setRaport1(RoundDecimal.doubleWithDecimalPlace(raport1, 4));
		normalisasi.setRaport2(RoundDecimal.doubleWithDecimalPlace(raport2, 4));
		normalisasi.setJarak(RoundDecimal.doubleWithDecimalPlace(jarak, 4));
		normalisasi.setKendaraan(RoundDecimal.doubleWithDecimalPlace(kendaraan, 4));
		normalisasi.setPenghasilanOrtu(RoundDecimal.doubleWithDecimalPlace(penghasilanOrtu, 4));

		// pemangkatan bobot ()
		News news = (News) session.getAttribute("news");
		BobotSpk bobotEntity = new BobotSpk();
		bobotEntity.setbRapot1(bobot.getRaport1().intValue());
		bobotEntity.setbRapot2(bobot.getRaport2().intValue());
		bobotEntity.setbJarak(bobot.getJarak().intValue());
		bobotEntity.setbKendaraan(bobot.getKendaraan().intValue());
		bobotEntity.setbPenghasilanOrtu(bobot.getPenghasilanOrtu().intValue());
		bobotEntity.setNews(news);
		bobotSpkService.save(bobotEntity);
		List<User> users = userService.findAllByRolesUserAndNews(news);
		List<UserSpk> userSpk = new ArrayList<>();
		if (!userSpk.isEmpty()) {
			userSpk.clear();
		}

		// Cari jumlah S
		Double vectorS = 0.0;
		for (User user : users) {
			Double pangkatRaport1 = Math.pow(user.getRapot1(), raport1);
			Double pangkatRaport2 = Math.pow(user.getRapot2(), raport2);
			Double pangkatJarak = Math.pow(user.getJarak(), jarak);
			Double pangkatKendaraan = Math.pow(user.getKendaraan().getPoint(), kendaraan);
			Double pangkatPenghasilanOrtu = Math.pow(user.getPenghasilanOrtu(), -penghasilanOrtu);
			Double jumlahS = pangkatRaport1 * pangkatRaport2 * pangkatJarak * pangkatKendaraan * pangkatPenghasilanOrtu;
			vectorS += jumlahS;
		}

		// Cari Jumlah Vector S
		for (User user2 : users) {
			Double pangkatRaport1 = Math.pow(user2.getRapot1(), raport1);
			Double pangkatRaport2 = Math.pow(user2.getRapot2(), raport2);
			Double pangkatJarak = Math.pow(user2.getJarak(), jarak);
			Double pangkatKendaraan = Math.pow(user2.getKendaraan().getPoint(), kendaraan);
			Double pangkatPenghasilanOrtu = Math.pow(user2.getPenghasilanOrtu(), -penghasilanOrtu);
			Double jumlahS = pangkatRaport1 * pangkatRaport2 * pangkatJarak * pangkatKendaraan * pangkatPenghasilanOrtu;

			UserSpk userSpk1 = new UserSpk();
			userSpk1.setNis(user2.getNis());
			userSpk1.setNickname(user2.getNickname());
			userSpk1.setEmail(user2.getEmail());
			userSpk1.setRapot1(RoundDecimal.doubleWithDecimalPlace(pangkatRaport1, 3));
			userSpk1.setRapot2(RoundDecimal.doubleWithDecimalPlace(pangkatRaport2, 3));
			userSpk1.setJarak(RoundDecimal.doubleWithDecimalPlace(pangkatJarak, 3));
			userSpk1.setKendaraan(RoundDecimal.doubleWithDecimalPlace(pangkatKendaraan, 3));
			userSpk1.setPenghasilanOrtu(RoundDecimal.doubleWithDecimalPlace(pangkatPenghasilanOrtu, 3));
			userSpk1.setJumlahS(RoundDecimal.doubleWithDecimalPlace(jumlahS, 3));
			userSpk1.setVectorS(RoundDecimal.doubleWithDecimalPlace(vectorS, 3));
			userSpk1.setVectorV(RoundDecimal.doubleWithDecimalPlace(jumlahS / vectorS, 3));

			// Simpan Hasil akhir
			Spk spk = new Spk();
			spk.setJarak(RoundDecimal.doubleWithDecimalPlace(pangkatJarak, 3));
			spk.setJumlahS(RoundDecimal.doubleWithDecimalPlace(jumlahS, 3));
			spk.setKendaraan(RoundDecimal.doubleWithDecimalPlace(pangkatKendaraan, 3));
			spk.setPenghasilanOrtu(RoundDecimal.doubleWithDecimalPlace(pangkatPenghasilanOrtu, 3));
			spk.setRapot1(RoundDecimal.doubleWithDecimalPlace(pangkatRaport1, 3));
			spk.setRapot2(RoundDecimal.doubleWithDecimalPlace(pangkatRaport2, 3));
			spk.setVectorS(RoundDecimal.doubleWithDecimalPlace(vectorS, 3));
			spk.setVectorV(RoundDecimal.doubleWithDecimalPlace(jumlahS / vectorS, 3));
			spk.setUser(user2);
			spk.setNews(news);
			spk.setBobotSpk(bobotEntity);
			spkService.save(spk);
			user2.setSpk(spk);
			userService.save(user2);
		}

		List<Spk> desc = spkService.findAllByNewsDescLimit(news, news.getQuote());
		for (Spk spk : desc) {
			User user = userService.findOneBySpk(spk);
			user.setKet("Diterima");
			userService.save(user);
		}

		News one = newsService.findOne(news.getId());
		one.update(news.getId(), news.getTitle(), news.getDescription(), news.getContent(), news.getPublishedDate(), news.getExpiredDate(), news.getRole(), true, news.getUsers(), news.getQuote());
		newsService.update(one);
		session.removeAttribute("bobotSession");
		session.removeAttribute("news");
		return "redirect:/master/final/" + one.getId() + ".apsb";
	}

}
