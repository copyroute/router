package com.copyroute.ui.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.copyroute.cdm.global.Statics;

// CORS, Cross-Origin Resource Sharing, defines a mechanism to enable client-side cross-origin requests.  
// CORS are widely supported by modern browsers like FireFox, Chrome, Safari, and IE.
// 
// https://spring.io/guides/gs/rest-service-cors/
// http://zhentao-li.blogspot.com/2012/06/enable-cors-support-in-rest-services.html

@Component
public class SimpleCORSFilter implements Filter {

	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		chain.doFilter(req, res);
	}

	@PostConstruct
	public void init(){ 
		Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); 
	}
	public void init(FilterConfig filterConfig) {Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); }
	public void destroy() {}

}