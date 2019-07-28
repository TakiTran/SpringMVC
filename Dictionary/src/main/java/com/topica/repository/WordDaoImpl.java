package com.topica.repository;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topica.model.Word;
import com.topica.pagination.PaginationResult;

@Repository
@Transactional
public class WordDaoImpl implements WordDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public PaginationResult<Word> getAll(int page) {
		Session session = sessionFactory.openSession();
		org.hibernate.query.Query<Word> query = session.createQuery("FROM Word", Word.class);
		PaginationResult<Word> result = new PaginationResult<Word>(query, page, 3, 5);
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Word> absoluteSearch(String word, int type) {
		List<Word> words = null;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Word as w where w.type = :type and w.key = :word");
		query.setParameter("type", type);
		query.setParameter("word", word);
		words = query.getResultList();
		session.close();
		return words;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> relativeSearch(String word, int type) {
		List<Word> words = null;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Word as w where w.type = :type and w.key like concat('%',:word,'%')");
		query.setParameter("type", type);
		query.setParameter("word", word);
		words = query.getResultList();
		session.close();
		return words;
	}

	@Transactional
	@Override
	public void saveWord(Word word) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(word);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void delete(Word word) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(word);
	}

	@Override
	public boolean checkKeyExists(String key) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Word as w where w.key = :key");
		query.setParameter("key", key);
		@SuppressWarnings("unchecked")
		List<Word> words = query.getResultList();
		if(words.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getByType(int type) {
		List<Word> words = null;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Word as w where w.type = :type");
		query.setParameter("type", type);
		words = query.getResultList();
		session.close();
		return words;
	}

	@Override
	public Word findById(int id) {
		Session session = sessionFactory.openSession();
		return session.get(Word.class, id);
	}

}
