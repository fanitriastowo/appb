package com.skripsi.apsb.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skripsi.apsb.entity.Kendaraan;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.entity.dto.UserDTO;
import com.skripsi.apsb.service.KendaraanService;
import com.skripsi.apsb.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;

	@Autowired
	private KendaraanService kendaraanService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute(value = "kendaraanList")
	public List<Kendaraan> kendaraanOptions() {
		List<Kendaraan> kendaraanList = new ArrayList<>();
		kendaraanList = kendaraanService.findAllWithout("Pilih");
		return kendaraanList;
	}

	@RequestMapping
	public String account(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("account", userService.findOneByUsername(username));
		return "account";
	}

	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public void loadImage(HttpServletResponse response, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		response.setContentType("image/jpg");
		try {
			response.getOutputStream().write(user.getPicture());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String doUploadImage(@RequestParam(value = "fileUpload") MultipartFile fileUpload, Principal principal, RedirectAttributes redirectAttributes) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		Kendaraan kendaraan = kendaraanService.findOne(user.getKendaraan().getId());
		if (fileUpload.isEmpty()) {
			redirectAttributes.addFlashAttribute("kosong", true);
		} else if (!fileUpload.isEmpty() && fileUpload != null) {
			User userBaru = new User();
			userBaru.setId(user.getId());
			userBaru.setAddress(user.getAddress());
			userBaru.setBirthday(user.getBirthday());
			userBaru.setEmail(user.getEmail());
			userBaru.setEnabled(user.isEnabled());
			userBaru.setFamilyName(user.getFamilyName());
			userBaru.setJarak(user.getJarak());
			userBaru.setKendaraan(kendaraan);
			userBaru.setNickname(user.getNickname());
			userBaru.setPassword(user.getPassword());
			userBaru.setNis(user.getNis());
			userBaru.setPenghasilanOrtu(user.getPenghasilanOrtu());
			userBaru.setRapot1(user.getRapot1());
			userBaru.setRapot2(user.getRapot2());
			userBaru.setRoles(user.getRoles());
			userBaru.setUsername(user.getUsername());
			userBaru.setJoined(user.isJoined());
			userBaru.setComplete(user.isComplete());
			userBaru.setGender(user.getGender());
			userBaru.setPhone(user.getPhone());
			userBaru.setAgama(user.getAgama());
			userBaru.setScanned(user.isScanned());
			try {
				userBaru.setScan(user.getScan());
				userBaru.setScan2(user.getScan2());
				userBaru.setPicture(fileUpload.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			userService.save(userBaru);
		}
		return "redirect:/account.apsb";
	}

	@RequestMapping(value = "/upload/rapot", method = RequestMethod.POST)
	public String doUploadImageRapot(@RequestParam(value = "rapot1") MultipartFile rapot1, @RequestParam(value = "rapot2") MultipartFile rapot2, Principal principal,
			RedirectAttributes redirectAttributes) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		Kendaraan kendaraan = kendaraanService.findOne(user.getKendaraan().getId());
		if (rapot1.isEmpty() && rapot2.isEmpty()) {
			redirectAttributes.addFlashAttribute("kosong", true);
		} else if (!rapot1.isEmpty() && rapot1 != null && !rapot2.isEmpty() && rapot2 != null) {
			User userBaru = new User();
			userBaru.setId(user.getId());
			userBaru.setAddress(user.getAddress());
			userBaru.setBirthday(user.getBirthday());
			userBaru.setEmail(user.getEmail());
			userBaru.setEnabled(user.isEnabled());
			userBaru.setFamilyName(user.getFamilyName());
			userBaru.setJarak(user.getJarak());
			userBaru.setKendaraan(kendaraan);
			userBaru.setNickname(user.getNickname());
			userBaru.setPassword(user.getPassword());
			userBaru.setNis(user.getNis());
			userBaru.setPenghasilanOrtu(user.getPenghasilanOrtu());
			userBaru.setRapot1(user.getRapot1());
			userBaru.setRapot2(user.getRapot2());
			userBaru.setRoles(user.getRoles());
			userBaru.setUsername(user.getUsername());
			userBaru.setJoined(user.isJoined());
			userBaru.setComplete(user.isComplete());
			userBaru.setGender(user.getGender());
			userBaru.setPhone(user.getPhone());
			userBaru.setAgama(user.getAgama());
			userBaru.setScanned(true);
			try {
				userBaru.setScan(rapot1.getBytes());
				userBaru.setScan2(rapot2.getBytes());
				userBaru.setPicture(user.getPicture());
			} catch (IOException e) {
				e.printStackTrace();
			}
			userService.save(userBaru);
		}
		return "redirect:/account.apsb";
	}

	@RequestMapping(value = "/setting-admin", method = RequestMethod.GET)
	public String prepareUpdateAdmin(Model model, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("userAdmin", user);
		return "setting-admin";
	}

	@RequestMapping(value = "/setting-admin/save", method = RequestMethod.POST)
	public String saveUserDetailAdmin(@ModelAttribute(value = "userAdmin") User updated) {
		userService.updateUserAccount(updated);
		return "redirect:/logout.apsb";
	}

	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String prepareUpdate(Model model, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return "setting";
	}

	@RequestMapping(value = "/setting/save", method = RequestMethod.POST)
	public String saveUserDetail(@ModelAttribute(value = "user") User updated) {
		userService.updateUserAccount(updated);
		return "redirect:/logout.apsb";
	}

	@RequestMapping(value = "/account-edit", method = RequestMethod.GET)
	public String accountPrepareUpdate(Model model, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		if (user.getNis() != null) {
			UserDTO userDTO = new UserDTO(user);
			model.addAttribute("accountEdit", userDTO);
		} else {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setAddress(user.getAddress());
			userDTO.setBirthday(user.getBirthday());
			userDTO.setEmail(user.getEmail());
			userDTO.setFamilyName(user.getFamilyName());
			userDTO.setJarak(user.getJarak());
			userDTO.setKendaraan(user.getKendaraan().getName());
			userDTO.setNickname(user.getNickname());
			userDTO.setNis(user.getNis());
			userDTO.setPenghasilanOrtu(user.getPenghasilanOrtu());
			userDTO.setPhone(user.getPhone());
			userDTO.setRapot1(user.getRapot1());
			userDTO.setRapot2(user.getRapot2());
			model.addAttribute("accountEdit", userDTO);
		}
		return "account-edit";
	}

	@RequestMapping(value = "/account-edit/update", method = RequestMethod.POST)
	public String accountSave(@ModelAttribute(value = "accountEdit") UserDTO updated) {
		userService.updateUserDetail(updated);
		return "redirect:/account.apsb";
	}

}
