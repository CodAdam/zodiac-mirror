package com.lingyi.service;

import java.util.List;

import com.lingyi.domian.DTO.ZodiacDTO;
import com.lingyi.domian.DTO.ZodiacInfoDTO;

public interface ZodiacInfoService {
	//public void addZodiacInfo(ZodiacInfoDTO zodiacInfoDTO);
	public ZodiacInfoDTO getZodiacInfo(String zodiacid);
	public List<ZodiacDTO> getZodiac();
	public void addZodiac(ZodiacDTO zodiacDTO);
	public String getZodiacInfoWordCloud(String text);
}
