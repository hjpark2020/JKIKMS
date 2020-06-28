package com.jkikms.login.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;



public interface LoginService {

	Map<String, Object> loginCheck(Map<String, Object> param);
	
	String getKakaoAccessToken(String authorizeCdoe);
	
	Map<String, Object>	getKakaoUserInfo(String accesseToken);
	
	JSONObject idCheck(String userId);
	
	JSONObject registerUser(Map<String, Object> param);
}
