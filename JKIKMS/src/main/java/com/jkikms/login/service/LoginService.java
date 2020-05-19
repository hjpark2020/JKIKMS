package com.jkikms.login.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

public interface LoginService {

	Map<String, Object> loginCheck(Map<String, Object> param);
	
	String getKakaoAccessToken(String authorizeCdoe);
	
	Map<String, Object>	getKakaoUserInfo(String accesseToken);
	
}
