package com.ssh.common;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageAndFlag {
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private boolean flag;
	
	public MessageAndFlag(){
		
	}
	
	public MessageAndFlag(String message, boolean flag){
		this.message = message;
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "message: "+message+"| flag: "+flag;
	}
	
	
}
