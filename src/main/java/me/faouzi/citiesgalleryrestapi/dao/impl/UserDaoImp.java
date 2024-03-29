package me.faouzi.citiesgalleryrestapi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import me.faouzi.citiesgalleryrestapi.dao.UserDAO;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;
import me.faouzi.citiesgalleryrestapi.model.entity.City;

@Repository
public class UserDaoImp implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public AuthUser findByUsername(String username) {
		Query query = entityManager.createQuery("SELECT u FROM AuthUser u WHERE u.username = :username ")
				.setParameter("username", username);
		
		List<AuthUser> results = query.getResultList();
		if (results.isEmpty()) {
		    return null; // handle no-results case
		} else {
			AuthUser user = (AuthUser)results.get(0);
		    return user;
		}
		
		
	}
	
	@Transactional
	@Override
	public void persiste(AuthUser user) throws Exception {
		 entityManager.persist(user);
	}

}
