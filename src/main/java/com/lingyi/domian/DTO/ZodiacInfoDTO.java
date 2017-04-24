package com.lingyi.domian.DTO;

import java.util.List;

public class ZodiacInfoDTO {
	
	/**
	 * 星座id
	 */
	private String zodiacid;
	/**
	 * 星座名
	 */
	private String zodiacname;
	
	/**
	 * 类型
	 */
	private int type;
	
	/**
	 * 类型名 1=today 2=tomorrow 3=week 4=month 5=year
	 */
	private int typeName;
	
	private List<ZodiacInfoDetailDTO> zodiacInfoDetail;

	public String getZodiacid() {
		return zodiacid;
	}

	public void setZodiacid(String zodiacid) {
		this.zodiacid = zodiacid;
	}

	public String getZodiacname() {
		return zodiacname;
	}

	public void setZodiacname(String zodiacname) {
		this.zodiacname = zodiacname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTypeName() {
		return typeName;
	}

	public void setTypeName(int typeName) {
		this.typeName = typeName;
	}

	public List<ZodiacInfoDetailDTO> getZodiacInfoDetail() {
		return zodiacInfoDetail;
	}

	public void setZodiacInfoDetail(List<ZodiacInfoDetailDTO> zodiacInfoDetail) {
		this.zodiacInfoDetail = zodiacInfoDetail;
	}
	


}


