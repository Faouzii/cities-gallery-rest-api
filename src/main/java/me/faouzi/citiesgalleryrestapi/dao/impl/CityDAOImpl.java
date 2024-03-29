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
		Query query = entityManager.createQuery("FROM City");
		    query.setFirstResult((pageNumber - 1) * pageSize);
		    query.setMaxResults(pageSize);
		    List <City> cities = query.getResultList();
		   return cities;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public long countTotalCities() throws Exception {
		Query queryTotal = entityManager.createQuery ("SELECT COUNT(c.id) FROM City c");
	    long countResult = (long)queryTotal.getSingleResult();
	    return countResult;
	}

	@Override
	public City getByUid(String uid) throws Exception {
		Query query = entityManager.createQuery ("SELECT c FROM City c WHERE c.uid = :uid ")
		.setParameter("uid", uid);
		City city = (City) query.getSingleResult();
		return city;
	}

	@Override
	public City update(City city) throws Exception {
		return this.entityManager.merge(city);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> searchByNamePaginated(String keyword, int pageNumber, int pageSize) throws Exception {
		Query query = entityManager.createQuery("SELECT c FROM City c WHERE lower(c.label) LIKE lower(:keyword)")
		.setParameter("keyword", "%"+keyword+"%");
	    query.setFirstResult((pageNumber - 1) * pageSize);
	    query.setMaxResults(pageSize);
		List <City> cities = query.getResultList();
	   return cities;		
	}

	@Override
	public long countCitiesByName(String keyword) throws Exception {
		Query queryTotal = entityManager.createQuery ("SELECT COUNT(c.id) FROM City c WHERE LOWER(c.label) LIKE LOWER(:keyword)")
		.setParameter("keyword", "%"+keyword+"%");
	    long countResult = (long)queryTotal.getSingleResult();
	    return countResult;
	}

}
