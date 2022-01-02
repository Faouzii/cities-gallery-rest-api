package me.faouzi.citiesgalleryrestapi.service;

import me.faouzi.citiesgalleryrestapi.model.dto.JwtResponse;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;

public interface UserService {

	JwtResponse login(AuthUser requestUser) throws Exception;

}
