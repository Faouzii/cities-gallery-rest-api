# cities-gallery-rest-api

This is the backend application, developed with JAVA, Spring boot, Jersey, Spring security and Hibernate

The RESTFUL endpoints are the following :

GET | cities/status : check if the application is up
GET | /cities?page=pageNumer&size=pageSize  : display the list of city depanding on the page number and page size
GET | /cities/filter?name=keyword&page=pageNumber&size=pageSize : search a city by name, the search is pageable if a list of result is returned
PUT | /cities/uid : update a given city passed on the body using its uid
POST | /auth/login : authenticate a user by { username : string, password : string}  

