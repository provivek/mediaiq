package com.mediaiq.movie.beans;

import java.io.Serializable;

public class MoviesBean implements Serializable {

	private static final long serialVersionUID = -9101763171588467575L;
	
	private String Title;
	private int Year;
	private String imdbID;
	private String Type;
	private String Poster;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getPoster() {
		return Poster;
	}
	public void setPoster(String poster) {
		Poster = poster;
	}
}
