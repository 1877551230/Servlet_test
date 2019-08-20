package cn.tedu.vo;

import java.io.Serializable;

public class Result implements Serializable{
	/**
	 * 0:代表失败
	 * 1:代表成功
	 */
	private int status;
	/**
	 * 存储提示消息
	 */
	private String message;
	/**
	 * 存储数据的
	 */
	private Object data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
