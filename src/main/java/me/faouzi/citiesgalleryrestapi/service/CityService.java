package me.faouzi.citiesgalleryrestapi.service;

import java.util.List;

import me.faouzi.citiesgalleryrestapi.model.dto.CityListResponseDto;

public interface CityService {
	
	public CityListResponseDto getCities(int pageNumber, int pageSize) throws Exception;
	
}
