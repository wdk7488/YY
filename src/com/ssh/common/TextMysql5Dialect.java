package com.ssh.common;

import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

public class TextMysql5Dialect extends MySQL5Dialect {

	public TextMysql5Dialect(){
		super();
		this.registerHibernateType(Types.LONGVARCHAR , "text");
	}
}
