package com.bmdb.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private int year;
	private String rating;
	private String director;
//	@OneToMany(mappedBy="movie")
//	private List<Credit> credits;
	
	public Movie(int id, String title, int year, String rating, String director) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.director = director;
	}

	public Movie() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

//	public List<Credit> getCredits() {
//		return credits;
//	}
//
//	public void setCredits(List<Credit> credits) {
//		this.credits = credits;
//	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", rating=" + rating + ", director="
				+ director + "]";
	}
	
	

}
