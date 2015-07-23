package com.yc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RecommendationResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer recommendResultID;
	
	@Column
	private String title;
	
	@Column
	private String describe;
	
	@ElementCollection
    @Column(name = "solarTerms")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "solarTerm_name")
    private List<SolarTerm> solarTerms = new ArrayList<SolarTerm>();
	
	@ElementCollection
    @Column(name = "secommendationTypes")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "type_name")
    private List<RecommendationType> types = new ArrayList<RecommendationType>();

	public Integer getRecommendResultID() {
		return recommendResultID;
	}

	public void setRecommendResultID(Integer recommendResultID) {
		this.recommendResultID = recommendResultID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<SolarTerm> getSolarTerms() {
		return solarTerms;
	}

	public void setSolarTerms(List<SolarTerm> solarTerms) {
		this.solarTerms = solarTerms;
	}

	public List<RecommendationType> getTypes() {
		return types;
	}

	public void setTypes(List<RecommendationType> types) {
		this.types = types;
	}
	
}
