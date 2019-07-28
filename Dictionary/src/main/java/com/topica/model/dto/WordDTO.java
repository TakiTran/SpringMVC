package com.topica.model.dto;

public class WordDTO {

	private int id;
	private String key;
	private String mean;
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

	public WordDTO() {
		super();
	}

	public WordDTO(int id, String key, String mean, int type) {
		super();
		this.id = id;
		this.key = key;
		this.mean = mean;
		this.type = type;
	}

	
}
