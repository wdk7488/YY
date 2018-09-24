package com.ssh.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Global {
	public static ClassPathXmlApplicationContext beans = new ClassPathXmlApplicationContext("../applicationContext.xml");

	public static Object getBean(String daoName){
        return beans.getBean(daoName);
    }

}
