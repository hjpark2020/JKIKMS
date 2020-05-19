package com.jkikms.lol;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LOLcontroller {
	@RequestMapping(value="/loltest", method=RequestMethod.GET)
	 public JSONObject lolcontroller(Model md, HttpServletRequest request) {
		String encrypSummonerid="";
		String nick="";
		JSONObject jobj = null;
	        try{
	            String ID_LOL = "munchdrunkpurpp"; 
	            String API_KEY_LOL = "RGAPI-bcd2a46e-2405-4acf-b6c0-3a579065340e"; 
	            String urlStr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ ID_LOL +"?api_key="+API_KEY_LOL;
	            URL url = new URL(urlStr); // 위 urlStr을 이용해서 URL 객체를 만들어줍니다.
	            BufferedReader bf;
	            String line = "";
	            String result="";
	            InputStream is = url.openStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            bf = new BufferedReader(isr);
	            while((line=bf.readLine())!=null){
	                result=result.concat(line);
	            }
	            //System.out.print(result); 20200519
	    		JSONParser parser = new JSONParser();
	    		Object obj =parser.parse(result);
	    		jobj = (JSONObject) obj;
	    		//JSONObject obj = (JSONObject) parser.parse(result);
	    		
	    		System.out.println("//////////////////////////////");
	    		encrypSummonerid= (String) jobj.get("id");
	    		nick = (String) jobj.get("name");
	    		String urlStr_entry = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+encrypSummonerid+"?api_key="+API_KEY_LOL;
	    		URL url_entry = new URL(urlStr_entry);
	    		String lineEn="";
	    		String resultEn="";
	    		InputStream ise = url_entry.openStream();
	    		BufferedReader br = new BufferedReader(new InputStreamReader(ise));
	    		while((lineEn=br.readLine())!=null) {
	    			resultEn=resultEn.concat(lineEn);
	    		}
	    		System.out.print(resultEn);//encrypted ID 를 통한 match 정보 출력
	    		
	        }
	        catch(Exception e){
	            System.out.println(e.getMessage());
	        }
	        return jobj;
	 }
}