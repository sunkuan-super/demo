package com.sk.util.bean;

public class Person {
 
	private String name;
 
	private int age;
 
	private int sex;
 
	private String phone;
 
	private String address;
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public int getAge() {
		return age;
	}
 
	public void setAge(int age) {
		this.age = age;
	}
 
	public int getSex() {
		return sex;
	}
 
	public void setSex(int sex) {
		this.sex = sex;
	}
 
	public String getPhone() {
		return phone;
	}
 
	public void setPhone(String phone) {
		this.phone = phone;
	}
 
	public String getAddress() {
		return address;
	}
 
	public void setAddress(String address) {
		this.address = address;
	}
 
	public String toString() {
		return ":"+name + ";" + age + ";" + sex + ";" + phone + ";" + address;
	}
}