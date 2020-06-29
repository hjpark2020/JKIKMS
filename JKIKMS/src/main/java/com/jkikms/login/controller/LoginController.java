package com.jkikms.login.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jkikms.login.service.LoginService;
import com.jkikms.vo.UserVO;

@RestController
public class LoginController {

	@Resource(name = "com.jkikms.login.service.LoginService")
	LoginService loginService;

	@RequestMapping(value="/loginCheck" , method=RequestMethod.POST)
	void login(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param, Model md) throws IOException {
		// 로그인체크시 아이디/패스워드 파라미터는 암복호화 필요, 일단 암복호화없이 진행
		
		Map<String, Object> result = loginService.loginCheck(param);
		if ("Y".equals( result.get("result")) ) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", result.get("userInfo"));
			response.sendRedirect("/"+param.get("beforeMenu"));
		} else {
			response.sendRedirect("/login?result=N");
		}

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
	
	// 카카오 메시지 테스트
	@RequestMapping("/kakaoSend")
	public void kakaoSend(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String accessToken = (String) session.getAttribute("kakaoAccessToken");
		System.out.println(accessToken);

		//String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
		String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/send";
        String result = "";
        int responseCode = 0;
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            
            
            JSONObject jObj = new JSONObject();
            jObj.put("object_type", "text");
            jObj.put("text", "TEST!");
            
            JSONObject jObj2 = new JSONObject();
            jObj2.put("web_url", "http://localhost");
            jObj.put("link", jObj2);            
            
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            System.out.println(jObj.toString());
            //sb.append("template_object=" + jObj.toString());
            sb.append("template_id=28743");
            bw.write(sb.toString());
            System.out.println(bw.toString());
            bw.flush();
            
            //    결과 코드가 200이라면 성공
            responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            if(responseCode == 200) {
        		//요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                //String result = "";
                
                while ((line = br.readLine()) != null) {
                    result += line;
                }
                System.out.println("response body : " + result);


    			br.close();
    			bw.close();
            } else {
            	
            }
 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            accessToken = "";
        } 
	}
	
	@RequestMapping("/addKakao")
	void addKakao(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) throws IOException {
		System.out.println(code);
	}
	
	
	@RequestMapping("/registerCheck")
	public String register(HttpServletRequest request, Model md) {
		String returnUrl = "";
		UserVO userInfo = (UserVO) request.getSession().getAttribute("userInfo");
		if(userInfo != null) {
			returnUrl = "login/redirectIndex";
		} else {
			returnUrl = "login/register";
		}
		
		return returnUrl;
	}
	
	@RequestMapping(value="/idCheck" , method=RequestMethod.POST)
	public JSONObject idCheck(HttpServletRequest request) {

		return loginService.idCheck(request.getParameter("userId"));
	}

	@RequestMapping(value="/registerCheck" , method=RequestMethod.POST)
	void registerCheck(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirect, @RequestParam Map<String, Object> param, Model md) throws IOException {
		JSONObject jRes = loginService.registerUser(param);
		
		
		response.sendRedirect("/registerResult?jRes="+URLEncoder.encode(jRes.toString(), "UTF-8"));
	}
	
}
