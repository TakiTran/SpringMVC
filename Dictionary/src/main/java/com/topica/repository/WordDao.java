package com.topica.repository;

import java.util.List;

import com.topica.model.Word;

public interface WordDao {
	public Word findById(int id);
	public void saveWord(Word word);
	public void delete(Word word);
	public List<Word> getAll();
	public List<Word> getByType(int type);
	public List<Word> absoluteSearch(String word, int type);
	public List<Word> relativeSearch(String word, int type);
	public boolean checkKeyExists (String key);
}
