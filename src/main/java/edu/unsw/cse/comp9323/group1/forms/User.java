package edu.unsw.cse.comp9323.group1.forms;

public class User {
	
	private String name = null;
	private  String standard = null;
	private int age;
	private String sex = null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", standard=" + standard + ", age=" + age
				+ ", sex=" + sex + "]";
	}
}
