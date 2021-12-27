package me.faouzi.citiesgalleryrestapi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import me.faouzi.citiesgalleryrestapi.model.entity.City;

@Transactional 
@Repository
public class CityDAOImpl implements CityDAO{
	
	 @PersistenceContext 
	    private EntityManager entityManager;

	@Override
	public void save(City city) {
		entityManager.persist(city);		
	}

}
