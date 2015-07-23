package com.yc.controller.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import com.yc.entity.user.Personnel;
import com.yc.service.IPersonnelService;

@SuppressWarnings("rawtypes")
@Repository
public class InitDate implements ApplicationListener{
	
	@Autowired
	IPersonnelService personnelService;
	
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		initArticleCategory();
	}

	private void initArticleCategory() {
		Personnel  nat = personnelService.findById(1);
		if (nat==null) {
			nat = new Personnel();
			nat.setUserName("管理员");
			nat.setLoginName("admin");
			nat.setPassword("000000");
			nat.setForbidden(false);
			nat = personnelService.save(nat);
		}
	}

}
