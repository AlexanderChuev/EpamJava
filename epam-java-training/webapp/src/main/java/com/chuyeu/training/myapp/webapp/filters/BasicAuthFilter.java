package com.chuyeu.training.myapp.webapp.filters;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.services.impl.UserAuthStorage;

public class BasicAuthFilter implements Filter {

	private IUserService userService;
	private ApplicationContext appContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(filterConfig.getServletContext());
		userService = context.getBean(IUserService.class);
		appContext = context;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (isAuthRequired(req)) {
			chain.doFilter(request, response);
		}

		String[] credentials = resolveCredentials(req);

		if (credentials == null || credentials.length != 2) {
			res.sendError(401);
			return;
		}

		UserCredentials user = userService.getByEmailAndPassword(credentials[0], credentials[1]);
		UserAuthStorage userDataStorage = appContext.getBean(UserAuthStorage.class);

		if (user != null && user.getEmail() != null && user.getPassword() != null) {
			userDataStorage.setId(user.getId());
			userDataStorage.setUserRole(user.getUserRole());
			chain.doFilter(request, response);
		} else {
			res.sendError(401);
		}

	}

	private boolean isAuthRequired(HttpServletRequest req) {
		if (req.getMethod().toUpperCase().equals("GET") && (req.getRequestURI().equals("/product")
				|| req.getRequestURI().equals("/attribute") || req.getRequestURI().equals("/product-variant"))) {
			return false;
		}
		
		if(req.getMethod().toUpperCase().equals("POST") && req.getRequestURI().equals("/order-item")){
			return false;
		}
		
		return true;
	}

	private String[] resolveCredentials(HttpServletRequest req) {
		try {
			Enumeration<String> headers = req.getHeaders("Authorization");
			String nextElement = headers.nextElement();
			String base64Credentials = nextElement.substring("Basic".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			String[] emailAndPassword = credentials.split(":", 2);
			return emailAndPassword;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
