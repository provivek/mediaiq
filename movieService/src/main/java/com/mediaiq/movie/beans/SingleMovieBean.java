package com.mediaiq.movie.beans;

import java.io.Serializable;

public class SingleMovieBean implements Serializable {

	private static final long serialVersionUID = -2137887540979600292L;
			  
	private String imdbID;
	private String Title;
	private int Year;
	private float imdbRating;
	private String Genre;
	private String Director;
	private String Actors;
	private String Plot;
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String Title) {
		this.Title = Title;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int Year) {
		this.Year = Year;
	}
	public float getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(float imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String Genre) {
		this.Genre = Genre;
	}
	public String getDirector() {
		return Director;
	}
	public void setDirector(String Director) {
		this.Director = Director;
	}
	public String getActors() {
		return Actors;
	}
	public void setActors(String Actors) {
		this.Actors = Actors;
	}
	public String getPlot() {
		return Plot;
	}
	public void setPlot(String Plot) {
		this.Plot = Plot;
	}
}