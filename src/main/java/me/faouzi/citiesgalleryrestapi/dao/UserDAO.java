package me.faouzi.citiesgalleryrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;

@Repository
public interface UserDAO extends JpaRepository<AuthUser, Long> {

	AuthUser findByUsername(String username);

}
