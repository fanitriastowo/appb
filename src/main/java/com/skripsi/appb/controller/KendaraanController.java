package com.skripsi.apsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skripsi.apsb.entity.Kendaraan;
import com.skripsi.apsb.service.KendaraanService;

@Controller
@RequestMapping("/master")
public class KendaraanController {

	@Autowired
	private KendaraanService kendaraanService;

	@ModelAttribute(value = "kendaraan")
	public Kendaraan constructKendaraan() {
		return new Kendaraan();
	}

	@RequestMapping(value = "/kendaraan/{page}")
	public String kendaraanMenu(Model model, @PathVariable(value = "page") int page) {
		Page<Kendaraan> kendaraan = kendaraanService.findAllWithoutLimit("Pilih", page, 5);

		int current = kendaraan.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, kendaraan.getTotalPages());

		model.addAttribute("kendaraanList", kendaraan);
		model.addAttribute("current", current);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		return "kendaraan";
	}

	@RequestMapping(value = "/kendaraan/save", method = RequestMethod.POST)
	public String tambahKendaraan(@ModelAttribute(value = "kendaraan") Kendaraan kendaraan) {
		kendaraanService.save(kendaraan);
		return "redirect:/master/kendaraan/1.apsb";
	}

	@RequestMapping(value = "/kendaraan/update/{id}", method = RequestMethod.GET)
	public String prepareUpdate(@PathVariable(value = "id") long id, Model model) {
		Kendaraan kendaraan = kendaraanService.findOne(id);
		model.addAttribute("kendaraanUpdate", kendaraan);
		return "kendaraanUpdate";
	}

	@RequestMapping(value = "/kendaraan/update", method = RequestMethod.POST)
	public String updateKendaraan(@ModelAttribute(value = "kendaraanUpdate") Kendaraan updated) {
		kendaraanService.update(updated);
		return "redirect:/master/kendaraan/1.apsb";
	}

	@RequestMapping(value = "/kendaraan/delete/{id}")
	public String deleteKendaraan(@PathVariable(value = "id") int id) {
		Kendaraan kendaraan = kendaraanService.findOne(id);
		kendaraanService.delete(kendaraan);
		return "redirect:/master/kendaraan/1.apsb";
	}

	@RequestMapping(value = "/kendaraan/exportPdf")
	public String exportPdfKendaraan(ModelMap model) {
		model.addAttribute("dataSource", kendaraanService.findAllWithout("Pilih"));
		return "kendaraanPdf";
	}

}
