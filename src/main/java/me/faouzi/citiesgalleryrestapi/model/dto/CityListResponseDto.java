package me.faouzi.citiesgalleryrestapi.model.dto;

import java.util.List;

public class CityListResponseDto {
	
	private List<CityDto> cities;
	private long totalCities;
	
	public CityListResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CityListResponseDto(List<CityDto> cities, long totalCities) {
		super();
		this.cities = cities;
		this.totalCities = totalCities;
	}
	public List<CityDto> getCities() {
		return cities;
	}
	public void setCities(List<CityDto> cities) {
		this.cities = cities;
	}
	public long getTotalCities() {
		return totalCities;
	}
	public void setTotalCities(long totalCities) {
		this.totalCities = totalCities;
	}
	
	
}
