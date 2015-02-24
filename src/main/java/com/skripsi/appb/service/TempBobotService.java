package com.skripsi.apsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skripsi.apsb.entity.TempBobot;
import com.skripsi.apsb.repository.TempBobotRepository;

@Service
public class TempBobotService {

	@Autowired
	private TempBobotRepository bobotRepository;

	public void save(TempBobot bobotEntity) {
		bobotRepository.save(bobotEntity);
	}

	public List<TempBobot> findAll() {
		return bobotRepository.findAll();
	}

	public TempBobot findOne(long id) {
		return bobotRepository.findOne(id);
	}

	public TempBobot update(TempBobot bobot) {
		TempBobot updated = bobotRepository.findOne(bobot.getId());
		updated.update(bobot.getId(), bobot.getbJarak(), bobot.getbKendaraan(), bobot.getbPenghasilanOrtu(), bobot.getbRapot1(), bobot.getbRapot2());
		bobotRepository.save(updated);
		return updated;
	}
}
