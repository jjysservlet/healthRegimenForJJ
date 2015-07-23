package com.yc.dao.orm.commons;

import org.springframework.context.annotation.*;

import com.yc.entity.Collection;
import com.yc.entity.RecommendationResult;
import com.yc.entity.user.DepartAndPositions;
import com.yc.entity.user.Department;
import com.yc.entity.user.MembersFamily;
import com.yc.entity.user.MembersUser;
import com.yc.entity.user.Personnel;
import com.yc.entity.user.Positions;

@Configuration
public class GenericDaoConfig {

	@Bean
	public GenericDao<DepartAndPositions> getDepartAndPositionsDao() {
		return new GenericDaoSupport<DepartAndPositions>(DepartAndPositions.class);
	}
	@Bean
	public GenericDao<Department> getDepartmentDao() {
		return new GenericDaoSupport<Department>(Department.class);
	}
	@Bean
	public GenericDao<MembersFamily> getMembersFamilyDao() {
		return new GenericDaoSupport<MembersFamily>(MembersFamily.class);
	}
	@Bean
	public GenericDao<MembersUser> getMembersUserDao() {
		return new GenericDaoSupport<MembersUser>(MembersUser.class);
	}
	@Bean
	public GenericDao<Personnel> getPersonnelDao() {
		return new GenericDaoSupport<Personnel>(Personnel.class);
	}
	@Bean
	public GenericDao<Positions> getPositionsDao() {
		return new GenericDaoSupport<Positions>(Positions.class);
	}
	@Bean
	public GenericDao<RecommendationResult> getRecommendationResultDao() {
		return new GenericDaoSupport<RecommendationResult>(RecommendationResult.class);
	}
	@Bean
	public GenericDao<Collection> getCollectionDao() {
		return new GenericDaoSupport<Collection>(Collection.class);
	}
}
