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
import me.faouzi.citiesgalleryrestapi.exceptionmapper.CityNotFoundExceptionMapper;
import me.faouzi.citiesgalleryrestapi.model.dto.CityDto;
import me.faouzi.citiesgalleryrestapi.model.dto.CityListResponseDto;
import me.faouzi.citiesgalleryrestapi.model.entity.City;
import me.faouzi.citiesgalleryrestapi.service.CityService;

@Transactional
@Service
public class CityServiceImpl implements CityService {

	private static final Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired
	private CityDAO cityDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CityListResponseDto getCities(int pageNumber, int pageSize) throws Exception {
		// Getting list of cities as requested for the page number and page size
		logger.info("Getting list of " + pageSize + " cities for page : " + pageNumber);
		List<City> cities = cityDao.getByPagination(pageNumber, pageSize);
		// getting total cities entry in the database
		long totalEntries = cityDao.countTotalCities();
		logger.info("Total cities entry : " + totalEntries);
		// mapping persisting cities into DTOs in order to serve theme to the front end
		List<CityDto> cityDtos = new ArrayList<CityDto>();
		logger.info("Mapping found persisting cities to DTOs.. : ");
		for (City city : cities) {
			cityDtos.add(modelMapper.map(city, CityDto.class));
		}
		return new CityListResponseDto(cityDtos, totalEntries);
	}

	@Override
	public CityDto updateCity(CityDto cityDto, String uid) throws Exception {
		logger.info("Trying to update city : " + cityDto.getLabel());
		City city = cityDao.getByUid(uid);
		if (city == null) {
			logger.info("City Not Found !");
			throw new CityNotFoundExceptionMapper();
		}
		city.updateFromDto(cityDto);
		city = cityDao.update(city);
		logger.info("City updated successfully !");
		return modelMapper.map(city, CityDto.class);
	}

	@Override
	public CityListResponseDto getCitiesByName(String keyword, int pageNumber, int pageSize) throws Exception {
		logger.info("Searching cities whose name contains " + keyword);
		List<City> cities = cityDao.searchByNamePaginated(keyword, pageNumber, pageSize);
		long totalEntries = cityDao.countCitiesByName(keyword);
		logger.info("Total cities contaning the keyword '" + keyword + "' are : " + totalEntries);
		// mapping persisting cities into DTOs in order to serve theme to the front end
		List<CityDto> cityDtos = new ArrayList<CityDto>();
		for (City city : cities) {
			cityDtos.add(modelMapper.map(city, CityDto.class));
		}
		return new CityListResponseDto(cityDtos, totalEntries);
	}

}
