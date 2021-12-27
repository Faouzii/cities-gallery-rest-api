package me.faouzi.citiesgalleryrestapi.service;

import java.util.List;

import me.faouzi.citiesgalleryrestapi.model.entity.City;

public interface CityService {
	
	public List<City> getCities(int pageNumber, int pageSize) throws Exception;
	
}
