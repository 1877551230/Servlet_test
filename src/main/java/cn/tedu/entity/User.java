package cn.tedu.entity;

public class User {
	private int id;
	private String name;
	private String password;
	private int age;
	private String address;
	private String headimage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setHeadimage(String headimage) {
		this.headimage=headimage;
		
	}
	public String getHeadimage() {
		return headimage;
	}
}
