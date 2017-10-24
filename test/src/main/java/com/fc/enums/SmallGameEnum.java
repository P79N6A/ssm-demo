package com.fc.enums;

/**
 * @author fangcong
 */
public enum SmallGameEnum {

	/**
	 * 石头
	 */
	STONE(0,"石头"),
	/**
	 * 剪刀
	 */
	SCISSORS(1,"剪刀"),
	/**
	 * 布
	 */
	CLOTH(2,"布");
	
	private Integer value;
	private String desc;
	
	SmallGameEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	
	public static SmallGameEnum getEnumByValue(Integer value){
		if (value == null){
			return null;
		}
		for (SmallGameEnum e : SmallGameEnum.values()) {
			if (value.equals(e.getValue())){
				return e;
			}
		}
		return null;
	}

	public Integer getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

}
