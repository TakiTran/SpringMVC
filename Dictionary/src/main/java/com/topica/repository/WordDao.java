package com.topica.repository;

import java.util.List;

import com.topica.model.Word;
import com.topica.pagination.PaginationResult;

public interface WordDao {
	public Word findById(int id);
	public void saveWord(Word word);
	public void delete(Word word);
	public PaginationResult<Word> getAll(int page);
	public List<Word> getByType(int type);
	public List<Word> absoluteSearch(String word, int type);
	public List<Word> relativeSearch(String word, int type);
	public boolean checkKeyExists (String key);
}
