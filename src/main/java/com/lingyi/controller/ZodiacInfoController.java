package com.lingyi.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingyi.common.JsonUtil;
import com.lingyi.domian.DTO.ZodiacDTO;
import com.lingyi.domian.DTO.ZodiacInfoDTO;
import com.lingyi.domian.DTO.ZodiacInfoDetailDTO;
import com.lingyi.service.ZodiacInfoService;


@Controller
@RequestMapping("/zodiacInfo")
public class ZodiacInfoController {
	
		 @Autowired
		 ZodiacInfoService zodiacInfoService; 

	     
	     @RequestMapping("/welcome")
	     public String welcome(Map<String, Object> model) {
	    	 model.put("name", "adam");
	    	 model.put("pic", "");
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
	     @ResponseBody
	     public String getZodiacInfo(String zodiacid) {
	    	 ZodiacInfoDTO zodiacInfoDTO=new ZodiacInfoDTO();
	    	 zodiacInfoDTO=zodiacInfoService.getZodiacInfo(zodiacid);
			 try {
				return	 JsonUtil.objectToJsonStr(zodiacInfoDTO);
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			 } 
	     }

	     
	     @RequestMapping("/getZodiacInfoWordCloud")
	     public String getZodiacInfoWordCloud(Map<String, Object> model) {
	    	 String zodiacid="2";
	    	 ZodiacInfoDTO zodiacInfoDTO=new ZodiacInfoDTO();
	    	 zodiacInfoDTO=zodiacInfoService.getZodiacInfo(zodiacid);
	    	 
			 try {
				 String pic= zodiacInfoService.getZodiacInfoWordCloud(getWordtext(zodiacInfoDTO));
				 model.put("name", "adam");
				 model.put("pic", pic);
		         return "welcome";
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			 } 
	     }
	     
	     
	     
	     @RequestMapping("/getZodiac")
	     @ResponseBody
	     public String getZodiac() {
	    	 List<ZodiacDTO>zodiacDTOList=new ArrayList<ZodiacDTO>();
	    	 zodiacDTOList=zodiacInfoService.getZodiac();
	    	 try {
	    		 return JsonUtil.objectToJsonStr(zodiacDTOList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    		 return "error";
			}
	     }
	     
	     @RequestMapping("/initZodiac")
	     @ResponseBody
	     public String initZodiac() {
	    	 try {
		    	 List<ZodiacDTO>zodiacDTOList=new ArrayList<ZodiacDTO>();
		    	 zodiacDTOList=zodiacInfoService.getZodiac();
		    	 for(ZodiacDTO item : zodiacDTOList){
		    		 zodiacInfoService.addZodiac(item);
		    	 }
		    	 return "sucsess";
	    	 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		    		 return "error";
			 }
	     }
	     
	
	     @RequestMapping("/addZodiacInfo")
	     public void addZodiacInfo() {
	     }
	     
	     private String getWordtext(ZodiacInfoDTO zodiacInfoDTO){
	    	 StringBuilder result=new StringBuilder();
	    	 List<ZodiacInfoDetailDTO> detialList=zodiacInfoDTO.getZodiacInfoDetail();
	    	 for(ZodiacInfoDetailDTO item :detialList){
	    		 result.append((item.getJob()=="null"? "":item.getJob())+";");
	    		 result.append((item.getPresummary()=="null"? "":item.getPresummary())+";");
	    		 result.append((item.getStar()=="null"? "":item.getStar())+";");
	    		 result.append((item.getColor()=="null"? "":item.getColor())+";");
	    		 result.append((item.getNumber()=="null"? "":item.getSummary())+";");
	    		 result.append((item.getSummary()=="null"? "":item.getMoney())+";");
	    		 result.append((item.getMoney()=="null"? "":item.getMoney())+";");
	    		 result.append((item.getCareer()=="null"? "":item.getCareer())+";");
	    		 result.append((item.getLove()=="null"? "":item.getLove())+";");
	    		 result.append((item.getHealth()=="null"? "":item.getHealth())+";");
	    	 }
	    	 return result.toString();
	     }
}
