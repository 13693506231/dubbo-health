package com.kk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：seanyang
 * @date ：Created in 2019/6/7
 * @description ：
 * @version: 1.0
 */
public class Result implements Serializable {
	private boolean flag;//执行结果，true为执行成功 false为执行失败
	private String message;//返回结果信息
	private Object data;//返回数据

	@Override
	public String toString() {
		return "Result{" +
			"flag=" + flag +
			", message='" + message + '\'' +
			", data=" + data +
			'}';
	}

	public Result(){}

	public Result(boolean flag, String message) {
		this.flag = flag;
		this.message = message;
	}

	public Result(boolean flag, String message, Object data) {
		this.flag = flag;
		this.message = message;
		this.data = data;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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

