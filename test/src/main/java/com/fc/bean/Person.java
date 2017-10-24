package com.fc.bean;

import java.io.Serializable;

/**
 * @author fangcong
 */
public class Person implements Serializable,Cloneable{
	
	/**
	 * 进行Comparator排序测试新建的实体类
	 */
	private static final long serialVersionUID = 4314083631181865786L;
	private String firstname;
	private String lastname;
	private Boolean sex;
	private Integer age;

	public Person() {
		super();
	}

	@Override
	public Person clone(){
		Object cloneObject = null;
		try{
			cloneObject = super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return (Person)cloneObject;
	}

	public Person(String firstname, String lastname, Boolean sex, Integer age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
		this.age = age;
	}

	public String getFirstName() {
		return firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public Boolean getSex() {
		return sex;
	}

	public Integer getAge() {
		return age;
	}

	// 为了输入方便，重写了toString()
	@Override
	public String toString() {
		return firstname + " " + lastname + " "
				+ (sex.booleanValue() ? "男" : "女") + " " + age;
	}
}
