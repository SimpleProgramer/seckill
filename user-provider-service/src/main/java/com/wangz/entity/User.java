package com.wangz.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5158235201160483754L;
	
	private long userId;
	private String userName;
	private String password;

}
