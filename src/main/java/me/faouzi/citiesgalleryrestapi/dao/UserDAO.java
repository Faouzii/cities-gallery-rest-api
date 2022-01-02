package me.faouzi.citiesgalleryrestapi.dao;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;


public interface UserDAO  {

	AuthUser findByUsername(String username);
	void persiste (AuthUser user) throws Exception;

}
