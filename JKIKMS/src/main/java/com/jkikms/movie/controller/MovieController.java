package com.jkikms.movie.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

@RestController
public class MovieController {
	// 영화리스트가져오기 임시경로
	@RequestMapping("/movieList")
	public String movieList(HttpServletRequest request) {
		
		String apiKey = "c4066d3ba2e8669680dcf7fd00347194";
		
		KobisOpenAPIRestService kobisService = new KobisOpenAPIRestService(apiKey);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE,-1);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		String response = "";
		try {
			response = kobisService.getDailyBoxOffice(true, format.format(calendar.getTime()) , "10", "", "", "");
			
			JSONParser parser = new JSONParser();
			JSONObject jObj = (JSONObject) parser.parse(response);
			JSONObject tempJobj = (JSONObject) jObj.get("boxOfficeResult");
			
			ArrayList<JSONObject> list = (ArrayList<JSONObject>) tempJobj.get("dailyBoxOfficeList");
			for (JSONObject movie : list) {
				System.out.println("===================================");
				System.out.println("랭킹 : " + movie.get("rank"));
				System.out.println("신규진입여부 : " + movie.get("rankOldAndNew"));
				System.out.println("영화제목 : " + movie.get("movieNm"));
				System.out.println("개봉일 : " + movie.get("openDt"));
				System.out.println("누적관객수 : " + movie.get("audiAcc"));
				System.out.println("전일대비증감분 : " + movie.get("rankInten"));
				System.out.println("===================================");
			}
			
		} catch (OpenAPIFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return response;
	}
}
