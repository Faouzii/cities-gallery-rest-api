package me.faouzi.citiesgalleryrestapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.faouzi.citiesgalleryrestapi.dao.CityDAO;
import me.faouzi.citiesgalleryrestapi.exception.CityNotFoundException;
import me.faouzi.citiesgalleryrestapi.model.dto.CityDto;
import me.faouzi.citiesgalleryrestapi.model.dto.CityListResponseDto;
import me.faouzi.citiesgalleryrestapi.model.entity.City;
import me.faouzi.citiesgalleryrestapi.service.CityService;

@Transactional
@Service
public class CityServiceImpl implements CityService{
	
	private static final Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired
	private CityDAO cityDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CityListResponseDto getCities(int pageNumber, int pageSize) throws Exception {
		//Getting list of cities as requested for the page number and page size
		logger.info("Getting list of "+ pageSize +" cities for page : " + pageNumber);
		List<City> cities =  cityDao.getByPagination(pageNumber, pageSize);
		//getting total cities entry in the database
		long totalEntries = cityDao.countTotalCities();
		logger.info("Total cities entry : "+ totalEntries);
		//mapping persisting cities into DTOs in order to serve theme to the front end
		List<CityDto> cityDtos = new ArrayList<CityDto>();
		logger.info("Mapping found persisting cities to DTOs.. : ");
		for(City city : cities) {
			cityDtos.add(modelMapper.map(city, CityDto.class));
		}
		CityListResponseDto citiesResponse = new CityListResponseDto(cityDtos, totalEntries);
		return citiesResponse;
	}

	@Override
	public CityDto updateCity(CityDto cityDto, String uid) throws Exception {
		City city = cityDao.getByUid(uid);
		if(city == null) {
			throw new CityNotFoundException();
		}
		city.setImgUrl(cityDto.getImgUrl());
		city.setLabel(cityDto.getLabel());
		cityDao.update(city);
		return null;
	}


}



