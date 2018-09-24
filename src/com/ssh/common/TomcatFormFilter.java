package com.ssh.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TomcatFormFilter implements Filter{
	
	protected String encoding;
	
	public TomcatFormFilter(){}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(request.getCharacterEncoding() == null && encoding != null){
            //设置字符集
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
		chain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 //从web.xml中读取encoding值
		encoding = filterConfig.getInitParameter("encoding");
	}
	
}
