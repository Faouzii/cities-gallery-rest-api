package me.faouzi.citiesgalleryrestapi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.faouzi.citiesgalleryrestapi.dao.CityDAO;
import me.faouzi.citiesgalleryrestapi.model.entity.City;
import me.faouzi.citiesgalleryrestapi.service.CityService;

@Transactional
@Service
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityDAO cityDao;
	
	
	@Override
	public List<City> getCities(int pageNumber, int pageSize) throws Exception {
		
		System.out.println(cityDao.getByPagination(pageNumber, pageSize).size());
		return cityDao.getByPagination(pageNumber, pageSize);
	}


}
