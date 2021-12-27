package me.faouzi.citiesgalleryrestapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import me.faouzi.citiesgalleryrestapi.utils.StringsGenerators;

@Entity
@Table(name = "CITY")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uid;
	private String label;
	@Column(length = 1200)
	private String imgUrl;
	
	
	public City(String label, String imgUrl) {
		super();
		//We generate the city unique ID 
		this.uid = StringsGenerators.generateUID();
		this.label = label;
		this.imgUrl = imgUrl;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
