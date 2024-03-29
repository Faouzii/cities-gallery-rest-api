package me.faouzi.citiesgalleryrestapi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import me.faouzi.citiesgalleryrestapi.dao.CityDAO;
import me.faouzi.citiesgalleryrestapi.dao.UserDAO;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;
import me.faouzi.citiesgalleryrestapi.model.entity.City;
import me.faouzi.citiesgalleryrestapi.model.entity.Role;
import me.faouzi.citiesgalleryrestapi.utils.Constants;
import me.faouzi.citiesgalleryrestapi.utils.ERole;


@Component
public class DataInitializer{
	
	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateOperation;
	
	@Value("${cities-csv-filename}")
	private String citiesCsvFilename;

	@Autowired
	private CityDAO cityDao;
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	@PostConstruct
	private void initializeData() throws Exception{
		
		if (hibernateOperation.equals("create")) {
			createUser(new AuthUser("user", "user01"));
			createadmin(new AuthUser("admin", "admin01"));
			logger.info("Initializing application data tables on application startup..");
			persisteCitiesFromCVS();
			
		}		
	}
	private void createadmin(AuthUser requestUser) throws Exception {
		 // Create user with role ALLOW_EDIT
		logger.info("Creating new user with ROLE_ALLOW_EDIT..");
		requestUser.setPassword(encoder.encode(requestUser.getPassword()));
		Set<Role> roles_ = new HashSet<>();
		Role defaultRole = new Role(ERole.ROLE_ALLOW_EDIT);
		roles_.add(defaultRole);
		requestUser.setRoles(roles_);
		userDao.persiste(requestUser);
	}
	
	private void createUser(AuthUser requestUser) throws Exception {
		 // Create new user
		logger.info("Creating new user WITHOUT ROLE_ALLOW_EDIT..");
		requestUser.setPassword(encoder.encode(requestUser.getPassword()));
		userDao.persiste(requestUser);
	}
	
	private void persisteCitiesFromCVS() throws Exception{
		logger.info("Initializing cities tables..");
		logger.info("Reading CVS file");
		try (BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(citiesCsvFilename).getFile()))) {
			logger.info("CVS file found ! Persisting cities..");
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] cityArray = line.split(Constants.COMMA_DELIMITER);
		        City city = new City(cityArray[1], cityArray[2]);
		        cityDao.save(city);
		    }
		    logger.info("All cities has been persisted successfully !");
		} catch (FileNotFoundException e) {
			logger.error("CVS file not found at please double check");
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}













