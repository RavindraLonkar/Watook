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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.watook.security.WatookToken;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityFilter implements Filter {

	@Autowired
	private Environment environment;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		if (url.contains("build/session")) {
			chain.doFilter(request, response);
		} else {
			String decryKey = WatookToken.decrypt(req.getHeader("token"), environment.getProperty("ENCY_USER_KEY"));
			if (decryKey != null && decryKey.contains(environment.getProperty("MATCH_USER_KEY"))) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(environment.getProperty("TOKEN_INVALID_REDIRECT_URL"));
			}
		}
	}

	@Override
	public void destroy() {

	}
}