package com.letter.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letter.project.model.ExpLetter;
import com.letter.project.repo.ExpLetterRepo;
import com.letter.project.service.ExpLetterService;

@Service
public class ExpLetterServiceImpl implements ExpLetterService{
	
	@Autowired
	private ExpLetterRepo expLetterRepo;

	@Override
	public ExpLetter addData(ExpLetter expLetter) {
		return expLetterRepo.saveAndFlush(expLetter);
	}

	@Override
	public List<ExpLetter> getAllData() {
		return expLetterRepo.findAll();
	}

}
