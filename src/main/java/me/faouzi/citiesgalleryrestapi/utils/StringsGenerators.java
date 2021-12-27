package me.faouzi.citiesgalleryrestapi.utils;

import java.util.UUID;

public class StringsGenerators {
	
	//the UID generator method has to be Thread safe
	synchronized public static String generateUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
