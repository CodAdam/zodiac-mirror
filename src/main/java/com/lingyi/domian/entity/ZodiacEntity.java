package com.lingyi.domian.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ZodiacEntity {
	@Id
	String zodiacid;
	String zodiacName;
	String date;
	String pic;
	public String getZodiacid() {
		return zodiacid;
	}
	public void setZodiacid(String zodiacid) {
		this.zodiacid = zodiacid;
	}
	public String getZodiacName() {
		return zodiacName;
	}
	public void setZodiacName(String zodiacName) {
		this.zodiacName = zodiacName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
