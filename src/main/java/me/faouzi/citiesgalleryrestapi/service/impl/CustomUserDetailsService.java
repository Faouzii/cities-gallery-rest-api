package me.faouzi.citiesgalleryrestapi.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import me.faouzi.citiesgalleryrestapi.dao.UserDAO;
import me.faouzi.citiesgalleryrestapi.model.dto.CustomUserDetails;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;
import me.faouzi.citiesgalleryrestapi.utils.Constants;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userRepository;

	@Override
	@Transactional
	public CustomUserDetails loadUserByUsername(String username) throws BadCredentialsException {

		AuthUser user = userRepository.findByUsername(username);
		if (user == null) {

			throw new BadCredentialsException(Constants.SYS_USER_NOT_FOUND + username);
		}
		return CustomUserDetails.build(user);
	}

}
