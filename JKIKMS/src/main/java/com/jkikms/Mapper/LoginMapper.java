package com.jkikms.Mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jkikms.vo.UserVO;

@Repository("com.jkikms.Mapper.LoginMapper")
public interface LoginMapper {
	
	public List<UserVO> selectUserInfo(UserVO userVo);
	
	public UserVO loginChk(UserVO userVo);
					
}
