package com.lingyi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingyi.domian.DTO.ZodiacDTO;
import com.lingyi.domian.DTO.ZodiacInfoDTO;
import com.lingyi.domian.DTO.ZodiacInfoDetailDTO;
import com.lingyi.domian.entity.ZodiacEntity;
import com.lingyi.domian.entity.ZodiacInfoEntity;
import com.lingyi.domian.repository.ZodiacInfoRepository;
import com.lingyi.domian.repository.ZodiacRepository;

import java.util.List;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class ZodiacInfoServiceImpl implements ZodiacInfoService{
	
	@Autowired
	ZodiacInfoRepository zodiacInfoRepository;
	
	@Autowired
	ZodiacRepository zodiacRepository;
	
	public ZodiacInfoDTO getZodiacInfo(){
		ZodiacInfoDTO zodiacInfoDTO=new ZodiacInfoDTO();
		zodiacInfoDTO= makeZodiacInfoDTOByApi();
		zodiacInfoRepository.findAll();
		return zodiacInfoDTO;
	}
	
	private ZodiacInfoDTO makeZodiacInfoDTOByApi(){
		ZodiacInfoDTO zodiacInfoDTO=new ZodiacInfoDTO();
		
		return zodiacInfoDTO;
		
	}
	
	public void addZodiac(ZodiacDTO zodiacDTO){
		ZodiacEntity zodiacEntity=new ZodiacEntity();
		zodiacEntity=makeZodiacEntity(zodiacDTO);
		zodiacRepository.save(zodiacEntity);
	}

	private ZodiacEntity makeZodiacEntity(ZodiacDTO zodiacDTO){
		ZodiacEntity result=new ZodiacEntity();
		result.setZodiacid(zodiacDTO.getZodiacid().toString());
		result.setZodiacName(zodiacDTO.getZodiacName());
		result.setDate(zodiacDTO.getDate());
		result.setPic(zodiacDTO.getPic());
		return result;
		
	}
	
    //星座查询
    public String getZodiacInfoWordCloud(String text){
        String result =null;
        String url ="http://localhost:5000/getWordCloudPng/";//请求接口地址 词云服务
        Map params = new HashMap();//请求参数
        //params.put("wordtext", text); 
        try {
			url+=URLEncoder.encode(text,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            result =visit(url, "GET");
            JSONObject object = new JSONObject(result);
            if(object.getString("code").equals("10000")){
            	String JsonResult=object.getString("data");
            	//String resultPic=JsonResult.getString("pic");
            	return JsonResult;
            }else{
            	return  "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  "exception";
        }
         
    }

 

	/**
	*数据宝第三方接口
	**/
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";  
    //配置您申请的KEY
    public static final String APPKEY ="af855ff1097b52e8bca8abb72cbdbfb2";

    //1.运势查询
    public  ZodiacInfoDTO getZodiacInfo(String zodiacid){
        String result =null;
        ZodiacInfoDTO zodiacInfoDTO=new ZodiacInfoDTO();
   
        zodiacInfoDTO.setZodiacInfoDetail(new ArrayList<ZodiacInfoDetailDTO>());
        String url ="http://api.chinadatapay.com/grassroots/constellation/307/2";//请求接口地址 星座运势查询
        Map params = new HashMap();//请求参数
            params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
            params.put("astroid",zodiacid);//星座编号，如:1
            params.put("date","");//默认当日//运势类型：today,tomorrow,week,nextweek,month,year

        try {
            result =net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if(object.getString("code").equals("10000")){
            	
            	JSONObject JsonResult=object.getJSONObject("data");
            	zodiacInfoDTO.setZodiacid(JsonResult.getString("astroid"));
            	zodiacInfoDTO.setZodiacname(JsonResult.getString("astroname"));          	
            
            	//年运势
            	ZodiacInfoDetailDTO zodiacInfoYearDetailDTO =new ZodiacInfoDetailDTO();            
            	JSONObject JsonYearResult=JsonResult.getJSONObject("year");
            	zodiacInfoYearDetailDTO.setType(5);
            	zodiacInfoYearDetailDTO.setTypeName("year");
            	zodiacInfoYearDetailDTO.setSummary(JsonYearResult.getString("summary"));
            	zodiacInfoYearDetailDTO.setMoney(JsonYearResult.getString("money"));
            	zodiacInfoYearDetailDTO.setCareer(JsonYearResult.getString("career"));
            	zodiacInfoYearDetailDTO.setLove(JsonYearResult.getString("love"));
            	zodiacInfoDTO.getZodiacInfoDetail().add(zodiacInfoYearDetailDTO);
               	//月运势
            	ZodiacInfoDetailDTO zodiacInfoMonthDetailDTO =new ZodiacInfoDetailDTO();            
            	JSONObject JsonMonthResult=JsonResult.getJSONObject("month");
            	zodiacInfoMonthDetailDTO.setType(4);
            	zodiacInfoMonthDetailDTO.setTypeName("month");
            	zodiacInfoMonthDetailDTO.setDate(JsonMonthResult.getString("date"));
            	zodiacInfoMonthDetailDTO.setSummary(JsonMonthResult.getString("summary"));
            	zodiacInfoMonthDetailDTO.setMoney(JsonMonthResult.getString("money"));
            	zodiacInfoMonthDetailDTO.setCareer(JsonMonthResult.getString("career"));
            	zodiacInfoMonthDetailDTO.setLove(JsonMonthResult.getString("love"));
            	zodiacInfoDTO.getZodiacInfoDetail().add(zodiacInfoMonthDetailDTO);

               	//周运势
            	ZodiacInfoDetailDTO zodiacInfoWeekDetailDTO =new ZodiacInfoDetailDTO();            
            	JSONObject JsonWeekResult=JsonResult.getJSONObject("week");
            	zodiacInfoWeekDetailDTO.setType(3);
            	zodiacInfoWeekDetailDTO.setTypeName("week");
            	zodiacInfoWeekDetailDTO.setDate(JsonWeekResult.getString("date"));
            	zodiacInfoWeekDetailDTO.setMoney(JsonWeekResult.getString("money"));
            	zodiacInfoWeekDetailDTO.setCareer(JsonWeekResult.getString("career"));
            	zodiacInfoWeekDetailDTO.setLove(JsonWeekResult.getString("love"));
            	zodiacInfoWeekDetailDTO.setHealth(JsonWeekResult.getString("health"));
            	zodiacInfoWeekDetailDTO.setJob(JsonWeekResult.getString("job"));
            	zodiacInfoDTO.getZodiacInfoDetail().add(zodiacInfoWeekDetailDTO);
            	
               	//明日运势
            	ZodiacInfoDetailDTO zodiacInfoTomorrowDetailDTO =new ZodiacInfoDetailDTO();            
            	JSONObject JsonTomorrowResult=JsonResult.getJSONObject("tomorrow");
            	zodiacInfoTomorrowDetailDTO.setType(2);
            	zodiacInfoTomorrowDetailDTO.setTypeName("tomorrow");
            	zodiacInfoTomorrowDetailDTO.setDate(JsonTomorrowResult.getString("date"));
            	zodiacInfoTomorrowDetailDTO.setDate(JsonTomorrowResult.getString("presummary"));
            	zodiacInfoTomorrowDetailDTO.setDate(JsonTomorrowResult.getString("star"));
            	zodiacInfoTomorrowDetailDTO.setDate(JsonTomorrowResult.getString("color"));
            	zodiacInfoTomorrowDetailDTO.setDate(JsonTomorrowResult.getString("number"));
            	zodiacInfoTomorrowDetailDTO.setDate(JsonTomorrowResult.getString("summary"));
            	zodiacInfoTomorrowDetailDTO.setMoney(JsonTomorrowResult.getString("money"));
            	zodiacInfoTomorrowDetailDTO.setCareer(JsonTomorrowResult.getString("career"));
            	zodiacInfoTomorrowDetailDTO.setLove(JsonTomorrowResult.getString("love"));
            	zodiacInfoTomorrowDetailDTO.setHealth(JsonTomorrowResult.getString("health"));
            	zodiacInfoDTO.getZodiacInfoDetail().add(zodiacInfoTomorrowDetailDTO);

               	//今日运势
            	ZodiacInfoDetailDTO zodiacInfoTodayDetailDTO =new ZodiacInfoDetailDTO();            
            	JSONObject JsonTodayResult=JsonResult.getJSONObject("today");
            	zodiacInfoTodayDetailDTO.setType(2);
            	zodiacInfoTodayDetailDTO.setTypeName("today");
            	zodiacInfoTodayDetailDTO.setDate(JsonTodayResult.getString("date"));
            	zodiacInfoTodayDetailDTO.setDate(JsonTodayResult.getString("presummary"));
            	zodiacInfoTodayDetailDTO.setDate(JsonTodayResult.getString("star"));
            	zodiacInfoTodayDetailDTO.setDate(JsonTodayResult.getString("color"));
            	zodiacInfoTodayDetailDTO.setDate(JsonTodayResult.getString("number"));
            	zodiacInfoTodayDetailDTO.setDate(JsonTodayResult.getString("summary"));
            	zodiacInfoTodayDetailDTO.setMoney(JsonTodayResult.getString("money"));
            	zodiacInfoTodayDetailDTO.setCareer(JsonTodayResult.getString("career"));
            	zodiacInfoTodayDetailDTO.setLove(JsonTodayResult.getString("love"));
            	zodiacInfoTodayDetailDTO.setHealth(JsonTodayResult.getString("health"));
            	zodiacInfoDTO.getZodiacInfoDetail().add(zodiacInfoTodayDetailDTO);
            	
            	return zodiacInfoDTO;
            }else{
            	zodiacInfoDTO.setZodiacid(object.getString("code"));
            	zodiacInfoDTO.setZodiacname(object.getString("message"));
            	return zodiacInfoDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ZodiacInfoDTO();
        }
         
    }

    //星座查询
    public List<ZodiacDTO> getZodiac(){
        String result =null;
        List<ZodiacDTO> zodiacDTOList=new ArrayList<ZodiacDTO>();
        String url ="http://api.chinadatapay.com/grassroots/constellation/307/1";//请求接口地址 星座查询
        Map params = new HashMap();//请求参数
            params.put("key",APPKEY);//应用APPKEY(应用详细页查询)

        try {
            result =net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if(object.getString("code").equals("10000")){
            	JSONArray JsonResultArray=object.getJSONArray("data");
                for(int i=0;i<JsonResultArray.length();i++){ 
                	ZodiacDTO zodiacDTO=new ZodiacDTO();
                	zodiacDTO.setZodiacid(JsonResultArray.getJSONObject(i).getString("astroid"));
                	zodiacDTO.setZodiacName(JsonResultArray.getJSONObject(i).getString("astroname"));
                	zodiacDTO.setDate(JsonResultArray.getJSONObject(i).getString("date"));
                	zodiacDTO.setPic(JsonResultArray.getJSONObject(i).getString("pic"));
                	zodiacDTOList.add(zodiacDTO);
                }  
            	return zodiacDTOList;
            }else{
            	ZodiacDTO zodiacDTO=new ZodiacDTO();
            	zodiacDTO.setZodiacid(object.getString("code"));
            	zodiacDTO.setZodiacName(object.getString("message"));
            	zodiacDTOList.add(zodiacDTO);
            	return zodiacDTOList ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  new ArrayList<ZodiacDTO>();
        }
         
    }

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */

    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
    
    public static String visit(String strUrl,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}