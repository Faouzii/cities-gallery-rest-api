package me.faouzi.citiesgalleryrestapi.dao;

import java.util.List;

import me.faouzi.citiesgalleryrestapi.model.entity.City;

public interface CityDAO {

	public void save(City city) throws Exception;
	public List<City> getByPagination(int pageNumber, int pageSize) throws Exception;
	public long countTotalCities() throws Exception;
	public City getByUid(String uid) throws Exception;
	public City update(City city) throws Exception;
	public List<City> searchByNamePaginated(String keyword,int pageNumber, int pageSize) throws Exception;
	public long countCitiesByName(String keyword) throws Exception;
}
