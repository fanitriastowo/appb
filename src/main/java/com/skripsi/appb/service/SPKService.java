package com.skripsi.apsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.skripsi.apsb.entity.News;
import com.skripsi.apsb.entity.Spk;
import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.repository.SPKRepository;

@Service
public class SPKService {

	@Autowired
	private SPKRepository spkRepository;

	public Spk save(Spk spk) {
		return spkRepository.save(spk);
	}

	public Spk findOneByUser(User user) {
		return spkRepository.findOneByUser(user);
	}

	public Spk findOneByUserAndNews(User user, News one) {
		return spkRepository.findOneByUserAndNews(user, one);
	}

	public List<Spk> findAllByNews(News one) {
		return spkRepository.findAllByNews(one);
	}

	public List<Spk> findAllByNewsDesc(News one) {
		return spkRepository.findAllByNewsOrderByVectorVDesc(one);
	}

	public List<Spk> findAllByNewsDescLimit(News one, Integer quote) {
		return spkRepository.findAllByNews(one, new PageRequest(0, quote, Direction.DESC, "vectorV"));
	}
	
}
