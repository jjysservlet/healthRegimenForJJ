package com.yc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yc.dao.orm.commons.GenericDao;
import com.yc.entity.RecommendationResult;
import com.yc.service.IRecommendationResultService;

@Component
public class RecommendationResultService extends GenericService<RecommendationResult> implements IRecommendationResultService{

	@Autowired
	GenericDao<RecommendationResult> resultDao;
	
	@Override
	GenericDao<RecommendationResult> getDao() {
		return resultDao;
	}

}
