package com.jkikms.common.util;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.jkikms.main.service.MainService;
import com.jkikms.main.service.MainServiceImpl;
import com.jkikms.vo.MenuVO;
import com.jkikms.vo.StaticVO;

//@Configuration
//@DependsOn(value = {"com.jkikms.main.service.MainService"})
@Component
public class StartConfig implements ApplicationRunner{
	
	@Autowired
	MainService mainService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("STart!");
		//menuList = mainService.selectMenu();
		StaticVO.setMenuList(mainService.selectMenu());
	}
	
	
	/*
	 * public List<MenuVO> getMenuList() { return this.menuList; }
	 */
	
	
}
