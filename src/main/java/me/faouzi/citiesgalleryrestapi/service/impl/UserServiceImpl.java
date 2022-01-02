package me.faouzi.citiesgalleryrestapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import me.faouzi.citiesgalleryrestapi.dao.UserDAO;
import me.faouzi.citiesgalleryrestapi.model.dto.CustomUserDetails;
import me.faouzi.citiesgalleryrestapi.model.dto.JwtResponse;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;
import me.faouzi.citiesgalleryrestapi.model.entity.Role;
import me.faouzi.citiesgalleryrestapi.security.jwt.JwtUtils;
import me.faouzi.citiesgalleryrestapi.service.UserService;
import me.faouzi.citiesgalleryrestapi.utils.Constants;
import me.faouzi.citiesgalleryrestapi.utils.ERole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private HttpServletResponse httpServletResponse;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	@Autowired
	UserDAO userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	public JwtResponse login(AuthUser requestUser) throws Exception{
	 
		logger.info("===== Loging user with Email: " + requestUser.getEmail());
		String username = requestUser.getUsername();
		String password = requestUser.getPassword();

		logger.info("===== Serching by username : " + username);

		CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		if ((username.equals(userDetails.getUsername())) && (password.equals(userDetails.getPassword()))) {

			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
					userDetails.getPassword(), userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			CustomUserDetails userDetails_ = (CustomUserDetails) authentication.getPrincipal();

			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			logger.info("===== login succesfull with username : " + username);
			return new JwtResponse(jwt, userDetails_, roles);
		} else {
			throw new BadCredentialsException(Constants.SYS_USER_NOT_FOUND + username);
		}

	}

	

}
