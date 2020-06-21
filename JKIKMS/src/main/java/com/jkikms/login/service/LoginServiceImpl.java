package com.jkikms.login.service;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.jkikms.Mapper.LoginMapper;
import com.jkikms.vo.UserVO;

@Service("com.jkikms.login.service.LoginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name="com.jkikms.Mapper.LoginMapper")
	LoginMapper loginMapper;

	@Override
	public Map<String, Object> loginCheck(Map<String, Object> param) {
		Map<String, Object> rm = new HashMap<>();

		String userId = (String) param.get("userId");
		String userPw = (String) param.get("userPw");
		
		String loginResult = "";
		String errMsg = "";
		if(userId == null || "".equals(userId)) {
			loginResult = "N";
			errMsg = "loginErr001";
		} else {
			if(userPw == null || "".equals(userPw)) {
				loginResult = "N";
				errMsg = "loginErr002";
			} else {
				//DB 셀렉 필요
				UserVO userVo = new UserVO();
				userVo.setUserId(userId);
				userVo.setUserPwd(userPw);
				
				userVo = loginMapper.loginChk(userVo);

				if( userVo != null ) {
					loginResult = "Y";
				} else {
					loginResult = "N";
					errMsg = "loginErr003";
				}
				
			}
		}
		
		rm.put("result", loginResult);
		rm.put("errMsg", errMsg);
		rm.put("userId", userId);
			
		
		return rm;
	}

	@Override
	public String getKakaoAccessToken(String authorizeCdoe) {
		
		/*
		 * String access_Token = ""; String refresh_Token = "";
		 */
        String reqURL = "https://kauth.kakao.com/oauth/token";
        String result = "";
        String accessToken = "";
        int responseCode = 0;
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=a8c340a775979fb6b199e33db95bad51");
            sb.append("&redirect_uri=http://localhost/kakaoLogin");
            sb.append("&code=" + authorizeCdoe);
            bw.write(sb.toString());
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


                JSONParser parser = new JSONParser();
        		JSONObject jObj =  (JSONObject) parser.parse(result);
        		
    			accessToken = (String) jObj.get("access_token");	

    			br.close();
    			bw.close();
            } else {
            	accessToken = "";
            }
 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            accessToken = "";
        } catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			accessToken = "";
		}
		return accessToken;
	}

	@Override
	public Map<String, Object> getKakaoUserInfo(String accesseToken) {
		
		Map<String, Object> resultMap = new HashMap<>();
			
		String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accesseToken);
            
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            JSONParser parser = new JSONParser();
    		JSONObject jObj = (JSONObject) parser.parse(result);
    		JSONObject tempJobj;
    		
    		resultMap.put("resultCode", responseCode);
			resultMap.put("kakaoId", Long.toString( (Long) jObj.get("id")) );
			
			tempJobj = (JSONObject) jObj.get("properties");
			resultMap.put("kakaoNickName", (String)tempJobj.get("nickname"));
			
			tempJobj = (JSONObject) jObj.get("kakao_account");
			resultMap.put("kakaoEmail", (String)tempJobj.get("email"));
			
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resultMap.put("resultCode", 0);
        } catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			resultMap.put("resultCode", 0);
		}
		
		return resultMap;
	}
}
