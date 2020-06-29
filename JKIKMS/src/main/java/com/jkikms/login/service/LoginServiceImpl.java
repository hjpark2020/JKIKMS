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
import com.jkikms.common.util.SHA256Util;
import com.jkikms.vo.UserVO;

@Service("com.jkikms.login.service.LoginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name="com.jkikms.Mapper.LoginMapper")
	LoginMapper loginMapper;

	@Override
	public Map<String, Object> loginCheck(Map<String, Object> param) {
		Map<String, Object> rm = new HashMap<>();

		UserVO userVo = new UserVO();
		String userId = (String) param.get("userId");
		String userPw = (String) param.get("userPw");
		
		String loginResult = "";
		String errMsg = "";
		if(userId == null || "".equals(userId)) {
			loginResult = "N";
			errMsg = "emptyId";
		} else {
			if(userPw == null || "".equals(userPw)) {
				loginResult = "N";
				errMsg = "emptyPw";
			} else {
				//DB 셀렉 필요
				
				userVo.setUserId(userId);
				//userVo.setUserPwd(userPw);
				
				userVo = loginMapper.loginChk(userVo);

				if( userVo != null ) {
					
					String encryptPw = SHA256Util.getEncrypt(userPw, userVo.getSalt());
					if(userVo.getUserPwd().equals(encryptPw)) { 
						loginResult = "Y";
					} else {
						loginResult = "N";
						errMsg = "failPw";
					}
					
					
				} else {
					loginResult = "N";
					errMsg = "nonUser";
				}
				
			}
		}
		
		rm.put("result", loginResult);
		rm.put("errMsg", errMsg);
		rm.put("userInfo", userVo);
			
		
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

	@Override
	public JSONObject idCheck(String userId) {
		int result = 0;
		String success = "N";
		try {
			result = loginMapper.idCheck(userId);
			success = "Y";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jRes = new JSONObject();
		jRes.put("success", success);
		jRes.put("result", result);
		
		return jRes;
	}

	@Override
	public JSONObject registerUser(Map<String, Object> param) {
		// TODO Auto-generated method stub
		JSONObject jRes = new JSONObject();
		
		String errMsg = "";
		if("".equals( param.get("userId") ) || param.get("userId") == null) {
			errMsg = "emptyId";
		} else if("".equals( param.get("userPw") ) || param.get("userPw") == null || "".equals( param.get("userPwChk") ) || param.get("userPwChk") == null) {
			errMsg = "emptyPw";
		} else if ( !param.get("userPw").equals(param.get("userPwChk")) ) {
			errMsg = "failPwChk";
		} else if (  ((String) param.get("userPw")).length() < 6) {
			errMsg = "failPwLength";
		} else if( "".equals( param.get("userName") ) || param.get("userName") == null) {
			errMsg = "emptyName";
		}
		
		UserVO userVo = new UserVO();
		userVo.setUserId((String) param.get("userId"));
		userVo.setUserPwd((String) param.get("userPw"));
		userVo.setUserName((String) param.get("userName"));
		userVo.setUserNic((String) param.get("userName"));
		userVo.setUserLolNic((String) param.get("userLolNic"));
		userVo.setUserMail((String) param.get("userEmail"));
		userVo.setUserGubun("nomarl");
		userVo.setUserCertifi("Y");
		userVo.setLevel(2);
		userVo.setUserUseYn("Y");
		
		
		/////////////비번 암호화////////////////
		String salt = SHA256Util.generateSalt();
		userVo.setSalt(salt);
		userVo.setUserPwd(SHA256Util.getEncrypt(userVo.getUserPwd(), salt));

		
		Integer result = 0; 
		try {
			result = loginMapper.registerUser(userVo);
			
		} catch (Exception e) {
			e.printStackTrace();
			errMsg = "exception";
		}
		
		jRes.put("errMsg", errMsg);
		jRes.put("result", result);
		
		return jRes;
	}
}
