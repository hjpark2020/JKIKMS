package com.jkikms.Mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jkikms.vo.MenuVO;

@Repository("com.jkikms.Mapper.MenuMapper")
public interface MenuMapper {
	public List<MenuVO> selectMenu();
}
