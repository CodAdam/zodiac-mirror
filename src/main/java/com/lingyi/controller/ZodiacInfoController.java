package com.lingyi.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.lingyi.domian.DTO.ZodiacInfoDTO;
import com.lingyi.service.ZodiacInfoService;


@Controller
@RequestMapping("/zodiacInfo")
public class ZodiacInfoController {
	
		 @Autowired
		 ZodiacInfoService zodiacInfoService; 

	     
	     @RequestMapping("/welcome")
	     public String welcome(Map<String, Object> model) {
	    	 model.put("name", "adam");
	         return "welcome";
	     } 
	     
         @RequestMapping("/index")  
         public String index(){     
             return "index";//返回的内容就是templetes下面文件的名称  
         }
         
         @RequestMapping("/mirror")  
         public String mirror(){     
             return "mirror";//返回的内容就是templetes下面文件的名称  
         }
         
         @RequestMapping("/about")  
         public String about(){  
             return "about";//返回的内容就是templetes下面文件的名称  
         }
	     
	     
	     @RequestMapping("/getZodiacInfo")
	     public ZodiacInfoDTO getZodiacInfo() {
	    	 ZodiacInfoDTO zodiacInfoDTO=new ZodiacInfoDTO();
	    	 zodiacInfoDTO=zodiacInfoService.getZodiacInfo();
	         return zodiacInfoDTO;
	     }
	     
	     @RequestMapping("/addZodiacInfo")
	     public void addZodiacInfo() {
	    	 ZodiacInfoDTO zodiacInfoDTO=new ZodiacInfoDTO();
	    	 zodiacInfoDTO.setCareer("career");
	    	 zodiacInfoDTO.setColor("color");
	    	 zodiacInfoDTO.setDate("date");
	    	 zodiacInfoDTO.setHealth("health");
	    	 zodiacInfoDTO.setInfoType(1);
	    	 zodiacInfoDTO.setJob("job");
	    	 zodiacInfoDTO.setLove("love");
	    	 zodiacInfoDTO.setMoney("money");
	    	 zodiacInfoDTO.setNumber(1);
	    	 zodiacInfoDTO.setPresummary("presummary");
	    	 zodiacInfoDTO.setStar("star");
	    	 zodiacInfoDTO.setSummary("summary");
	    	 zodiacInfoDTO.setZodiacid(1);
	    	 zodiacInfoDTO.setZodiacname("zodiacname");
	    	 zodiacInfoService.addZodiacInfo(zodiacInfoDTO);
	     }
	     
}
