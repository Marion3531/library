package com.example.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="genres")
public class Genre {

	private @Id @GeneratedValue Long id;
	private String label;
	
	public Genre() {
		
	}
	
	public Genre(String label) {
		this.label = label;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", label=" + label + "]";
	}
	
}
