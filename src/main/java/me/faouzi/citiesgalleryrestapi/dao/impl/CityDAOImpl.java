package me.faouzi.citiesgalleryrestapi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import me.faouzi.citiesgalleryrestapi.dao.CityDAO;
import me.faouzi.citiesgalleryrestapi.model.entity.City;

@Repository
public class CityDAOImpl implements CityDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void save(City city) throws Exception{
		entityManager.persist(city);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getByPagination(int pageNumber, int pageSize) throws Exception {
		Query query = entityManager.createQuery("From City");
		    query.setFirstResult((pageNumber - 1) * pageSize);
		    query.setMaxResults(pageSize);
		    List <City> cities = query.getResultList();
		   return cities;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public long countTotalCities() throws Exception {
		Query queryTotal = entityManager.createQuery ("Select count(c.id) From City c");
	    long countResult = (long)queryTotal.getSingleResult();
	    return countResult;
	}

}
