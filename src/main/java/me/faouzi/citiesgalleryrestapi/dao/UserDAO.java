package me.faouzi.citiesgalleryrestapi.dao;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;


public interface UserDAO  {

	public AuthUser findByUsername(String username);
	public void persiste (AuthUser user) throws Exception;

}
