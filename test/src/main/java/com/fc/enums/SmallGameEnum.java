package com.fc.enums;

public enum SmallGameEnum {

	STONE(0,"石头"),
	SCISSORS(1,"剪刀"),
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
