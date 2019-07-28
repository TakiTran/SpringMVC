package com.topica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "word")
public class Word {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "word_key")
	private String key;
	
	@Column(name = "meaning")
	private String mean;
	
	// type = 1 ENG => VIE
	// type = 2 VIE => ENG
	@Column(name = "word_type")
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Word() {
		super();
	}

	public Word(int id, String key, String mean, int type) {
		super();
		this.id = id;
		this.key = key;
		this.mean = mean;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", key=" + key + ", mean=" + mean + ", type=" + type + "]";
	}
	
}
