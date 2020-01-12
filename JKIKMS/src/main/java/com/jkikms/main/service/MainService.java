package com.jkikms.main.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

public interface MainService {

	Map<String, Object> mainView(String userId);

}
