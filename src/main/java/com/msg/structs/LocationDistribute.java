package com.msg.structs;

public class LocationDistribute {
	private String name;
	private String sign;
	private String x;
	private String y;
	
	public LocationDistribute(String name, String sign, String x, String y) {
		super();
		this.name = name;
		this.sign = sign;
		this.x = x;
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
}
