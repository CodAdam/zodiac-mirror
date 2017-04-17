package com.lingyi.domian.DTO;

public class ZodiacInfoDTO {
	
	private int zodiacid;
	private String zodiacname;
	/**
	 * 1:year 2:week 3:today 4:tomorrow 5:month
	 */
	private int infoType;
	//year
	private String date;
	private String summary;
	private String money;
	private String career;
	private String love;
	//week
	private String health;
	private String job;
	//today
	private String presummary;
	private String star;
	private String color;
	private int number;
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
