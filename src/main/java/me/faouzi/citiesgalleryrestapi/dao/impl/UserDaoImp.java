package me.faouzi.citiesgalleryrestapi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import me.faouzi.citiesgalleryrestapi.dao.UserDAO;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;

@Repository
public class UserDaoImp implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AuthUser findByUsername(String username) {
		Query query = entityManager.createQuery("SELECT u FROM AuthUser u WHERE u.username = :username ")
				.setParameter("username", username);
		AuthUser user = (AuthUser) query.getSingleResult();
		return user;
	}

}
