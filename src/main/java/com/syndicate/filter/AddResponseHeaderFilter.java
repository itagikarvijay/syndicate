package com.syndicate.filter;

import java.io.IOException;



import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter("/")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AddResponseHeaderFilter implements Filter {

	    @Override
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	        final HttpServletResponse response = (HttpServletResponse) res;
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, PATCH");
	        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, x-access-token");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
	            response.setStatus(HttpServletResponse.SC_OK);
	        } else {
	            chain.doFilter(req, res);
	        }
	    }
	    @Override
	        public void destroy() {
	    }
	    @Override
	        public void init(FilterConfig config) throws ServletException {
	    }

}
