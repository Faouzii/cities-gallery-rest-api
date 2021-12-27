package me.faouzi.citiesgalleryrestapi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import me.faouzi.citiesgalleryrestapi.dao.CityDAO;
import me.faouzi.citiesgalleryrestapi.model.entity.City;
import me.faouzi.citiesgalleryrestapi.utils.Constants;

@Component
public class DataInitializer{
	
	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateOperation;
	
	@Value("${cities-csv-filename}")
	private String citiesCsvFilename;

	@Autowired
	private CityDAO cityDao;
	
	
	
	
	@PostConstruct
	private void initializeData(){
		
		if (hibernateOperation.equals("create")) {
			logger.info("Initializing application data tables on application startup..");
			persisteCitiesFromCVS();
		}		
	}
	
	private void persisteCitiesFromCVS(){
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













