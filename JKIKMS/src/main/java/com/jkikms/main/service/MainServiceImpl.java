package com.jkikms.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.jkikms.Mapper.LoginMapper;
import com.jkikms.vo.UserVO;

@Service("com.jkikms.main.service.MainService")
public class MainServiceImpl implements MainService {
	
	@Resource(name="com.jkikms.Mapper.LoginMapper")
	LoginMapper loginMapper;

	@Override
	public Map<String, Object> mainView(String userId) {
		
		
		Map<String, Object> rm = new HashMap<>();
		
		// 세션 체크
		if(userId != null) {
			rm.put("loginChk", "login");
		} else {
			rm.put("loginChk", "logout");
		}

		// 로고
		rm.put("mainLogo", "logoUrl");
		
		// 상단메뉴
		ArrayList<Map<String, String>> menuList = new ArrayList<>();
		
		// 테스트데이터->db select 필요
		Map<String, String> menuMap = new HashMap<String, String>();
		menuMap.put("홈", "홈url");
		menuList.add(menuMap);
		
		menuMap = new HashMap<String, String>();
		menuMap.put("위밋플레이스", "위밋플레이스url");
		menuList.add(menuMap);
		
		menuMap = new HashMap<String, String>();
		menuMap.put("SMTM", "SMTMurl");
		menuList.add(menuMap);
		
		menuMap = new HashMap<String, String>();
		menuMap.put("공용게시판", "공용게시판url");
		menuList.add(menuMap);
		
		menuMap = new HashMap<String, String>();
		menuMap.put("개인공간", "개인공간url");
		menuList.add(menuMap);
		
		menuMap = new HashMap<String, String>();
		menuMap.put("관리자", "관리자url");
		menuList.add(menuMap);
		
		rm.put("menuList",menuList);
		
		
		// 메인화면 구성
		

		return rm;
	}

	@Override
	public Map<String, Object> wemeetView(String userId) {
		Map<String, Object> rm = new HashMap<>();
		return rm;
	}

	@Override
	public Map<String, Object> smtmView(String userId) {
		Map<String, Object> rm = new HashMap<>();
		return rm;
	}

	@Override
	public Map<String, Object> publicbbsView(String userId) {
		Map<String, Object> rm = new HashMap<>();
		return rm;
	}

	@Override
	public Map<String, Object> privatebbsView(String userId) {
		Map<String, Object> rm = new HashMap<>();
		return rm;
	}

	@Override
	public Map<String, Object> adminView(String userId) {
		Map<String, Object> rm = new HashMap<>();
		return rm;
	}

}
