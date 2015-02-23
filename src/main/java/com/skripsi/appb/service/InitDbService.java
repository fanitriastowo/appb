package com.skripsi.apsb.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skripsi.apsb.entity.Kendaraan;
import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.Role;
import com.skripsi.apsb.entity.TempBobot;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.entity.User.Agama;
import com.skripsi.apsb.entity.User.Gender;
import com.skripsi.apsb.repository.KendaraanRepository;
import com.skripsi.apsb.repository.NewsRepository;
import com.skripsi.apsb.repository.RoleRepository;
import com.skripsi.apsb.repository.TempBobotRepository;
import com.skripsi.apsb.repository.UserRepository;

@Service
@Transactional
public class InitDbService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private KendaraanRepository kendaraanRepository;

	@Autowired
	private TempBobotRepository bobotRepository;

	@PostConstruct
	public void initDb() {
		if (roleRepository.findByName("ROLE_ADMIN") == null) {

			// Role
			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);

			// Master Kendaraan
			Kendaraan kendaraanPilih = new Kendaraan();
			kendaraanPilih.setName("Pilih");
			kendaraanPilih.setPoint(1);
			kendaraanRepository.save(kendaraanPilih);

			Kendaraan kendaraanJalanKaki = new Kendaraan();
			kendaraanJalanKaki.setName("Jalan Kaki");
			kendaraanJalanKaki.setPoint(50);
			kendaraanRepository.save(kendaraanJalanKaki);

			Kendaraan kendaraanSepeda = new Kendaraan();
			kendaraanSepeda.setName("Sepeda");
			kendaraanSepeda.setPoint(40);
			kendaraanRepository.save(kendaraanSepeda);

			Kendaraan kendaraanUmum = new Kendaraan();
			kendaraanUmum.setName("Kendaraan Umum");
			kendaraanUmum.setPoint(30);
			kendaraanRepository.save(kendaraanUmum);

			Kendaraan kendaraanLain = new Kendaraan();
			kendaraanLain.setName("Lain-lain");
			kendaraanLain.setPoint(20);
			kendaraanRepository.save(kendaraanLain);

			Kendaraan kendaraanMotor = new Kendaraan();
			kendaraanMotor.setName("Motor");
			kendaraanMotor.setPoint(15);
			kendaraanRepository.save(kendaraanMotor);
			
			Kendaraan kendaraanMobil = new Kendaraan();
			kendaraanMobil.setName("Mobil");
			kendaraanMobil.setPoint(10);
			kendaraanRepository.save(kendaraanMobil);

			// USER ADMIN
			User userAdmin = new User();
			userAdmin.setEnabled(true);
			userAdmin.setUsername("admin");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userAdmin.setPassword(encoder.encode("admin"));
			userAdmin.setEmail("mikeybestdrummer@gmail.com");

			List<Role> roles = new ArrayList<>();
			roles.add(roleAdmin);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);

			// News
			News newsAdmin = new News();
			newsAdmin.setTitle("Beasiswa Sampoerna");
			newsAdmin.setDescription("Beasiswa bagi siswa berprestasi dan tidak mampu");
			newsAdmin.setContent("Syarat : Fotocopy KTS, Kartu KK, Surat keterangan tidak mampu dari kelurahan, minimal jarak tempuh 2 Km");
			newsAdmin.setPublishedDate(new Date());
			newsAdmin.setCompleted(false);
			newsAdmin.setQuote(3);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 7);
			newsAdmin.setExpiredDate(calendar.getTime());
			newsAdmin.setRole(roleAdmin);
			newsRepository.save(newsAdmin);

			// Temporary Bobot
			TempBobot bobot = new TempBobot();
			bobot.setId(1l);
			bobot.setbJarak(3);
			bobot.setbKendaraan(3);
			bobot.setbPenghasilanOrtu(5);
			bobot.setbRapot1(5);
			bobot.setbRapot2(5);
			bobotRepository.save(bobot);

//			// Temporary User
//			byte[] photo = {23};
//			byte[] scan = {23};
//			byte[] scan2 = {23};
			List<Role> roles2 = new ArrayList<>();
			roles2.add(roleUser);
			calendar.add(Calendar.DATE, -39);
			
			User user1 = new User();
			user1.setAddress("RT. 2 RW. 7 PATIKRAJA");
			user1.setAgama(Agama.Islam);
			user1.setBirthday(calendar.getTime());
			user1.setComplete(true);
			user1.setEmail("alfi@gmail.com");
			user1.setEnabled(true);
			user1.setFamilyName("Nur Khasanah");
			user1.setGender(Gender.P);
			user1.setJarak(3.0);
			user1.setKendaraan(kendaraanSepeda);
			user1.setNickname("Alfi");
			user1.setNis(5193);
			user1.setPassword(encoder.encode("asd"));
			user1.setPenghasilanOrtu(1_200_000l);
			user1.setPhone("085291070216");
			user1.setRapot1(78.9);
			user1.setRapot2(79.6);
			user1.setRoles(roles2);
			user1.setScanned(false);
			user1.setUsername("alfi");
			userRepository.save(user1);
			
			User user2 = new User();
			user2.setAddress("RT. 2 RW. 2 SAWANGAN WETAN");
			user2.setAgama(Agama.Islam);
			user2.setBirthday(calendar.getTime());
			user2.setComplete(true);
			user2.setEmail("laelatun@gmail.com");
			user2.setEnabled(true);
			user2.setFamilyName("Cahyani");
			user2.setGender(Gender.P);
			user2.setJarak(3.5);
			user2.setKendaraan(kendaraanSepeda);
			user2.setNickname("Laelatun");
			user2.setNis(5129);
			user2.setPassword(encoder.encode("asd"));
			user2.setPenghasilanOrtu(1_000_000l);
			user2.setPhone("085354223456");
			user2.setRapot1(77.5);
			user2.setRapot2(73.3);
			user2.setRoles(roles2);
			user2.setScanned(false);
			user2.setUsername("laelatun");
			userRepository.save(user2);
			
			User user3 = new User();
			user3.setAddress("RT. 1 RW. 1 KARANGENDEP");
			user3.setAgama(Agama.Kristen);
			user3.setBirthday(calendar.getTime());
			user3.setComplete(true);
			user3.setEmail("irawan@gmail.com");
			user3.setEnabled(true);
			user3.setFamilyName("Yuni Kristiana");
			user3.setGender(Gender.P);
			user3.setJarak(3.5);
			user3.setKendaraan(kendaraanUmum);
			user3.setNickname("Irawan");
			user3.setNis(5267);
			user3.setPassword(encoder.encode("asd"));
			user3.setPenghasilanOrtu(1_300_000l);
			user3.setPhone("08944384996");
			user3.setRapot1(78.3);
			user3.setRapot2(80.6);
			user3.setRoles(roles2);
			user3.setScanned(false);
			user3.setUsername("irawan");
			userRepository.save(user3);
			
			User user4 = new User();
			user4.setAddress("RT. 4 RW. 13 SUKAPURA");
			user4.setAgama(Agama.Islam);
			user4.setBirthday(calendar.getTime());
			user4.setComplete(true);
			user4.setEmail("melani@gmail.com");
			user4.setEnabled(true);
			user4.setFamilyName("Nur Hidayanti Rahayu");
			user4.setGender(Gender.P);
			user4.setJarak(2.0);
			user4.setKendaraan(kendaraanLain);
			user4.setNickname("Melani");
			user4.setNis(5270);
			user4.setPassword(encoder.encode("asd"));
			user4.setPenghasilanOrtu(1_300_000l);
			user4.setPhone("085227583948");
			user4.setRapot1(78.4);
			user4.setRapot2(75.6);
			user4.setRoles(roles2);
			user4.setScanned(false);
			user4.setUsername("melani");
			userRepository.save(user4);
		}
	}
}
