package com.topica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topica.repository.WordDao;
import com.topica.model.Word;
import com.topica.pagination.PaginationResult;

@Service
@Transactional
public class WordService {
	
	@Autowired
	private WordDao wordDao;
	
	public Word findById(int id) {
		return wordDao.findById(id);
	}
	
	public void saveWord(Word word) {
		wordDao.saveWord(word);
	}
	
	public void deleteWord(Word word) {
		wordDao.delete(word);
	}
	
	public PaginationResult<Word> getAll(int page) {
		return wordDao.getAll(page);
	}
	
	public List<Word> getByType(int type) {
		return wordDao.getByType(type);
	}
	
	public List<Word> absoluteSearch(String word, int type) {
		return wordDao.absoluteSearch(word, type);
	}
	
	public List<Word> relativeSearch(String word, int type) {
		return wordDao.relativeSearch(word, type);
	}
	
	public boolean checkKeyExists(String key) {
		return wordDao.checkKeyExists(key);
	}
}
