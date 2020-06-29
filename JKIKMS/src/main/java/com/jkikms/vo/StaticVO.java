package com.jkikms.vo;

import java.util.List;

public class StaticVO {
	private static List<MenuVO> menuList = null;

	public static List<MenuVO> getMenuList() {
		return menuList;
	}

	public static void setMenuList(List<MenuVO> menuList) {
		StaticVO.menuList = menuList;
	}

	
}
