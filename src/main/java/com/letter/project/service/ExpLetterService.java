package com.letter.project.service;

import java.util.List;

import com.letter.project.model.ExpLetter;

public interface ExpLetterService {
	
	ExpLetter addData(ExpLetter expLetter);
	
	List<ExpLetter> getAllData();

}
