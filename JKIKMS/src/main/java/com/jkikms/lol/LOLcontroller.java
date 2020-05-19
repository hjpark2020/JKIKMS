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
		String id="";
		String nick="";
		JSONObject jobj = null;
	        try{
	            String ID_LOL = "munchdrunkpurpp"; 
	            String API_KEY_LOL = "RGAPI-9cf7d48a-87a7-4127-a7be-3a6fa36c91d6"; 
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
	            //System.out.print(result);
	    		JSONParser parser = new JSONParser();
	    		Object obj =parser.parse(result);
	    		jobj = (JSONObject) obj;
	    		//JSONObject obj = (JSONObject) parser.parse(result);
	    		id = (String) jobj.get("id");
	    		nick = (String) jobj.get("name");
	    		//System.out.print(id);
	        }
	        catch(Exception e){
	            System.out.println(e.getMessage());
	        }
	        return jobj;
	 }
}