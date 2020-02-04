package com.jkikms.login.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jkikms.login.service.LoginService;

@RestController
public class LoginController {

	@Resource(name = "com.jkikms.login.service.LoginService")
	LoginService loginService;

	@RequestMapping("/loginCheck")
	public Map<String, Object> login(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		// 로그인체크시 아이디/패스워드 파라미터는 암복호화 필요, 일단 암복호화없이 진행
		return loginService.loginCheck(param);
	}

	@RequestMapping("/loginSuccess")
	void loginSuccess(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param)
			throws IOException {
		// 로그인 성공시 메인페이지로 리다이렉트
		HttpSession session = request.getSession();
		session.setAttribute("userId", param.get("userId"));

		response.sendRedirect("/");
	}

	// 카카오 로그인 테스트
	@RequestMapping("/kakaoLogin")
	void kakaoTest(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) throws IOException {
		System.out.println(code);

		String kakaoAccessToken = loginService.getKakaoAccessToken(code);
		
		Map<String, Object> kakaoUserInfo = null;
		
		// 카카오 로그인 성공시
		if(!"".equals(kakaoAccessToken)) {
			
			kakaoUserInfo = loginService.getKakaoUserInfo(kakaoAccessToken);
			
			if((int) kakaoUserInfo.get("resultCode") != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("kakaoAccessToken", kakaoAccessToken);
				session.setAttribute("userId", kakaoUserInfo.get("kakaoId"));

				System.out.println("==========================");
				System.out.println("kakaoAccessToken : " +kakaoAccessToken);
				
				System.out.println(session.getAttribute("userId"));
				for (String key : kakaoUserInfo.keySet()) {
					System.out.println(key + " : " + kakaoUserInfo.get(key));
				}
				System.out.println("==========================");
				
				// 카카오 로그인 성공후 회원가입이 되어있는지 아닌지 확인
				// 회원가입이 안되어있을경우 자동으로 가입을 시키고 추가 정보입력 페이지로 이동
				// 회원가입이 되어있을경우 인덱스 페이지로 이동
				// 추가 정보를 넣는 페이지는 아직 미개발
			} else {
				// 카카오 유저정보 불러오기 실패
				
			}

		} else {
			// 카카오 accessToken 불러오기 실패
			
		}
		
		response.sendRedirect("/");
	}

	// 카카오 로그인 테스트
	@RequestMapping("/kakaoLogout")
	public String kakaoLogout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String accessToken = (String) session.getAttribute("kakaoAccessToken");
		System.out.println(accessToken);

		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "kakaoLogout";
	}
}
