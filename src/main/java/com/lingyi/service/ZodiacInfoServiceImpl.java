package com.lingyi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingyi.domian.DTO.ZodiacInfoDTO;
import com.lingyi.domian.entity.ZodiacInfoEntity;
import com.lingyi.domian.repository.ZodiacInfoRepository;

@Service
public class ZodiacInfoServiceImpl implements ZodiacInfoService{
	
	@Autowired
	ZodiacInfoRepository zodiacInfoRepository;
	
	public ZodiacInfoDTO getZodiacInfo(){
		ZodiacInfoDTO zodiacInfoDTO=new ZodiacInfoDTO();
		zodiacInfoRepository.findAll();
		return zodiacInfoDTO;
	}
	
	public void addZodiacInfo(ZodiacInfoDTO zodiacInfoDTO){
		ZodiacInfoEntity zodiacInfoEntity=new ZodiacInfoEntity();
		zodiacInfoEntity.setCareer(zodiacInfoDTO.getCareer());
		zodiacInfoEntity.setColor(zodiacInfoDTO.getColor());
		zodiacInfoEntity.setDate(zodiacInfoDTO.getDate());
		zodiacInfoEntity.setHealth(zodiacInfoDTO.getHealth());
		zodiacInfoEntity.setInfoType(zodiacInfoDTO.getInfoType());
		zodiacInfoEntity.setJob(zodiacInfoDTO.getJob());
		zodiacInfoEntity.setLove(zodiacInfoDTO.getLove());
		zodiacInfoEntity.setMoney(zodiacInfoDTO.getMoney());
		zodiacInfoEntity.setNumber(zodiacInfoDTO.getNumber());
		zodiacInfoEntity.setPresummary(zodiacInfoDTO.getPresummary());
		zodiacInfoEntity.setStar(zodiacInfoDTO.getStar());
		zodiacInfoEntity.setSummary(zodiacInfoDTO.getSummary());
		zodiacInfoEntity.setZodiacid(zodiacInfoDTO.getZodiacid());
		zodiacInfoEntity.setZodiacname(zodiacInfoDTO.getZodiacname());
		zodiacInfoRepository.save(zodiacInfoEntity);
	}
}
