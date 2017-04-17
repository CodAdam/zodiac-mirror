package com.lingyi.domian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZodiacInfo")
public class ZodiacInfoEntity {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long Zid;
	
	int zodiacid;
	
	String zodiacname;
	
	/**
	 * 1:year 2:week 3:today 4:tomorrow 5:month
	 */
	int infoType;
	
	//year
	String date;
	String summary;
	String money;
	String career;
	String love;
	//week
	String health;
	String job;
	//today
	String presummary;
	String star;
	String color;
	int number;
	public long getGuid() {
		return Zid;
	}
	public void setGuid(long Zid) {
		this.Zid = Zid;
	}
	public int getZodiacid() {
		return zodiacid;
	}
	public void setZodiacid(int zodiacid) {
		this.zodiacid = zodiacid;
	}
	public String getZodiacname() {
		return zodiacname;
	}
	public void setZodiacname(String zodiacname) {
		this.zodiacname = zodiacname;
	}
	public int getInfoType() {
		return infoType;
	}
	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getLove() {
		return love;
	}
	public void setLove(String love) {
		this.love = love;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getPresummary() {
		return presummary;
	}
	public void setPresummary(String presummary) {
		this.presummary = presummary;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
