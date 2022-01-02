package me.faouzi.citiesgalleryrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;


public interface UserDAO  {

	AuthUser findByUsername(String username);

}
