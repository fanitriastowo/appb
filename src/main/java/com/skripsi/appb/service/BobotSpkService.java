package com.skripsi.apsb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skripsi.apsb.entity.BobotSpk;
import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.repository.BobotSpkRepository;

@Service
public class BobotSpkService {

	@Autowired
	private BobotSpkRepository bobotSpkRepository;

	public void save(BobotSpk bobotSpk) {
		bobotSpkRepository.save(bobotSpk);
	}

	public BobotSpk findOneByNews(News one) {
		return bobotSpkRepository.findOneByNews(one);
	}
}
