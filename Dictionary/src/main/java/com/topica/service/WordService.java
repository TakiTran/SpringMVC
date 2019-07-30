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
	
	public boolean updateWord(Word word) {
		int id = word.getId();
		Word w = wordDao.findById(id);
		boolean result = true;
		if (!w.getKey().equals(word.getKey()) || (w.getType() != word.getType())) {
			word.setKey(w.getKey());
			word.setType(w.getType());
			result = false;
		}
		wordDao.saveWord(word);
		return result;
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
	
	public PaginationResult<Word> relativeSearchPage(String word, int type, int page){
		return wordDao.relativeSearchPage(word, type, page);
	}
	
	public boolean checkKeyExists(String key) {
		return wordDao.checkKeyExists(key);
	}
}
