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
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.services.impl.util.IRedisUtil;
import com.chuyeu.training.myapp.services.impl.util.UserAuthStorage;

public class BasicAuthFilter implements Filter {

	private ApplicationContext appContext;
	private UserCredentials user;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(filterConfig.getServletContext());
		appContext = context;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (!isAuthRequired(req)) {
			chain.doFilter(request, response);
			return;
		}
		String[] credentials = resolveCredentials(req);

		if (credentials == null || credentials.length != 2) {
			res.sendError(401);
			return;
		}

		IRedisUtil redisUtil = appContext.getBean(IRedisUtil.class);

		String[] userData = redisUtil.check(credentials);
		if (userData == null) {
			IUserService userService = appContext.getBean(IUserService.class);
			user = userService.getByEmailAndPassword(credentials[0], credentials[1]);
			redisUtil.write(credentials, user);
		} else {
			user = new UserCredentials();
			user.setId(Integer.valueOf(userData[0]));
			user.setUserRole(UserRole.valueOf(userData[1].toUpperCase()));
		}

		UserAuthStorage userDataStorage = appContext.getBean(UserAuthStorage.class);

		if (user != null) {
			userDataStorage.setId(user.getId());
			userDataStorage.setUserRole(user.getUserRole());
			boolean verificationAccess = verificationAccess(req, userDataStorage);
			if (verificationAccess) {
				chain.doFilter(request, response);
			} else {
				res.sendError(401);
			}
		} else {
			res.sendError(401);
		}

	}

	private boolean isAuthRequired(HttpServletRequest req) {

		if (req.getMethod().toUpperCase().equals("GET") && (req.getRequestURI().contains("/product")
				|| req.getRequestURI().contains("/attribute") || req.getRequestURI().contains("/product-variant")
				|| req.getRequestURI().contains("/order-item") || req.getRequestURI().contains("/serialization"))) {
			return false;
		}
		if (req.getMethod().toUpperCase().equals("POST") && req.getRequestURI().contains("/user")) {
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

	private String getLanguage(HttpServletRequest req) {
		try {
			Enumeration<String> headers = req.getHeaders("Accept-Language");
			return headers.nextElement();
		} catch (Exception e) {
			return null;
		}

	}

	private boolean verificationAccess(HttpServletRequest req, UserAuthStorage userDataStorage) {

		if (req.getRequestURI().contains("/product") && req.getMethod().toUpperCase().equals("POST")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}
		if (req.getRequestURI().contains("/product") && req.getMethod().toUpperCase().equals("PUT")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}
		if (req.getRequestURI().contains("/product") && req.getMethod().toUpperCase().equals("DELETE")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}

		if (req.getRequestURI().contains("/attribute") && req.getMethod().toUpperCase().equals("POST")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}

		if (req.getRequestURI().contains("/attribute") && req.getMethod().toUpperCase().equals("DELETE")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}

		if (req.getRequestURI().contains("/product-variant") && req.getMethod().toUpperCase().equals("POST")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}
		if (req.getRequestURI().contains("/product-variant") && req.getMethod().toUpperCase().equals("PUT")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}
		if (req.getRequestURI().contains("/product-variant") && req.getMethod().toUpperCase().equals("DELETE")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}

		if (req.getRequestURI().contains("/variant") && req.getMethod().toUpperCase().equals("POST")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}

		if (req.getRequestURI().contains("/variant") && req.getMethod().toUpperCase().equals("DELETE")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}

		if (req.getRequestURI().contains("/user/credentials")
				&& (req.getMethod().toUpperCase().equals("GET") || req.getMethod().toUpperCase().equals("PUT"))) {
			if (userDataStorage.getUserRole().equals(UserRole.CLIENT)) {
				String[] uri = req.getRequestURI().split("/");
				Integer id = Integer.valueOf(uri[3]);
				if (userDataStorage.getId().equals(id)) {
					return true;
				}
			}
			if (userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
				return true;
			}
		}

		if (req.getRequestURI().contains("/user/profile")
				&& (req.getMethod().toUpperCase().equals("GET") || req.getMethod().toUpperCase().equals("PUT"))) {
			if (userDataStorage.getUserRole().equals(UserRole.CLIENT)) {
				String[] uri = req.getRequestURI().split("/");
				Integer id = Integer.valueOf(uri[3]);
				if (userDataStorage.getId().equals(id)) {
					return true;
				}
			}
			if (userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
				return true;
			}
		}

		if (req.getRequestURI().contains("/user") && req.getMethod().toUpperCase().equals("GET")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}

		if (req.getRequestURI().contains("/user") && req.getMethod().toUpperCase().equals("DELETE")
				&& userDataStorage.getUserRole().equals(UserRole.ADMIN)) {
			return true;
		}

		if (req.getRequestURI().contains("/order-item") && (req.getMethod().toUpperCase().equals("POST")
				|| req.getMethod().toUpperCase().equals("PUT") || req.getMethod().toUpperCase().equals("DELETE"))) {
			return true;
		}

		if (req.getRequestURI().contains("/order") && (req.getMethod().toUpperCase().equals("GET")
				|| req.getMethod().toUpperCase().equals("POST") || req.getMethod().toUpperCase().equals("PUT")
				|| req.getMethod().toUpperCase().equals("DELETE"))) {
			return true;
		}

		return false;
	}

	@Override
	public void destroy() {
	}

}
