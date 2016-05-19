package com.mediaiq.movie.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.mediaiq.movie.beans.MoviesBean;
import com.mediaiq.movie.beans.SingleMovieBean;

@RestController
@RequestMapping("/movies")
public class MoviesController {
	
	
	/**
	 * Value of IMDB REST service stored in the application.properties
	 */
	@Value("${imdb.rest.url.search}")
	private String imdbRestUrlSearch;
	
	
	@Value("${imdb.rest.url.search.id}")
	private String imdbRestUrlSearchId;
	
	
	/**
	 * This method would call the IMDB service and essentially going to prevent the Cross Domain request from the UI.
	 * 
	 * @param searchMovieString
	 * @return
	 */
	@RequestMapping("/search")
	public String getMoviesBySearchString (@RequestParam String searchMovieString) {
		String moviesJson = "";
		try {
			RestTemplate restTemplate = new RestTemplate();
			String imdbRestUrl = imdbRestUrlSearch+searchMovieString;
			LinkedHashMap<?, ?> moviesMap = restTemplate.getForObject(imdbRestUrl, LinkedHashMap.class);
			Gson gs = new Gson();
			
			/*System.out.println(moviesMap.get("Search"));
			
			List<LinkedHashMap<?, ?>> allMovies =  (List<LinkedHashMap<?, ?>>) moviesMap.get("Search");
			
			for(LinkedHashMap<?, ?> linkedMovieMap : allMovies) {
				
				System.out.println(linkedMovieMap.get("imdbID"));
				
				LinkedHashMap<?, ?> movieData = getMoviesById(linkedMovieMap.get("imdbID").toString());
				allMoviesList.add(movieData);
			}*/
			moviesJson = gs.toJson(moviesMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moviesJson;
	}
	
	private LinkedHashMap<?, ?> getMoviesById (String searchMovieId) {
		LinkedHashMap<?, ?> singleMovieMap = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			String imdbRestUrl = imdbRestUrlSearchId+searchMovieId+"&plot=full&r=json";
			singleMovieMap = restTemplate.getForObject(imdbRestUrl, LinkedHashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return singleMovieMap;
	}
	
	@RequestMapping("/searchById")
	public String getMoviesBySearchAllId (@RequestParam List<String> id) {
		String jsonMovies = "";
		List<LinkedHashMap<?, ?>> allMoviesList = new ArrayList<LinkedHashMap<?,?>>();
		try {
			for (String imdbID : id) {
				allMoviesList.add(getMoviesById(imdbID));
			}
			
			Gson gs = new Gson();
			jsonMovies = gs.toJson(allMoviesList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMovies; 
		
		
	}
}
