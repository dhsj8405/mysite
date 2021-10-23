package com.douzone.mysite.vo;

public class AdminSiteVo {
	private Long no;
	private String title;
	private String welcome;
	private String profile;
	private String description;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDescripton() {
		return description;
	}
	public void setDescripton(String text) {
		this.description = text;
	}
	@Override
	public String toString() {
		return "AdminSiteVo [no=" + no + ", title=" + title + ", welcome=" + welcome + ", profile=" + profile
				+ ", description=" + description + "]";
	}
	
}
