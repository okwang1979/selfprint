package com.spring;

public class Student {
	
	private IGoSchool goSchool;
	
	private String name;
	
	private Integer sex;
	
	
	
	

 

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IGoSchool getGoSchool() {
		return goSchool;
	}

	public void setGoSchool(IGoSchool goSchool) {
		this.goSchool = goSchool;
	}
	
	public void goSchool(){
		goSchool.goSchool();
	}
	
	
	

}
