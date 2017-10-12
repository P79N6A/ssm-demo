package com.demo.state;

/**
 * @author fangcong
 */
public class Mm {

	private String name;
	
	private AbstractMmState state = new MmHappyState();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void smile(){
		state.smile();
	}
	
	public void cry(){
		state.cry();
	}
	
	public void say(){
		state.say();
	}
}
