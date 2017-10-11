package com.watook.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.watook.security.WatookToken;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type");

		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();
		String decryKey = "";
		if (url.contains("auth")||url.contains("/error")) {
			chain.doFilter(request, response);
		} else {
			try {
				decryKey = WatookToken.decrypt(req.getHeader("token"), "$uTh!!##");
			} catch (Exception e) {
				System.out.println("Invalid token");
			}

			if (decryKey != null && decryKey.contains("m$TcHr0LeUsER")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect("/Watook/api/error");
			}
		}
	}

	@Override
	public void destroy() {

	}
}