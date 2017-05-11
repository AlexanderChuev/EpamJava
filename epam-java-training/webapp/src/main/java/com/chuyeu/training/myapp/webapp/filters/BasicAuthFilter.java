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
import com.chuyeu.training.myapp.services.util.Language;
import com.chuyeu.training.myapp.services.util.LanguageWrapper;
import com.chuyeu.training.myapp.services.util.RedisUtil;
import com.chuyeu.training.myapp.services.util.UserAuthStorage;

public class BasicAuthFilter implements Filter {

	private ApplicationContext appContext;
	

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

		LanguageWrapper languageWrapper = appContext.getBean(LanguageWrapper.class);
		languageWrapper.setLanguage(getLanguage(req));

		
		String[] credentials = resolveCredentials(req);

		if (credentials == null || credentials.length != 2) {
			res.sendError(401);
			return;
		}
		RedisUtil redisUtil = appContext.getBean(RedisUtil.class);

		String[] userData = redisUtil.check(credentials);
		
		UserCredentials user = null;
		
		if (userData == null) {
			IUserService userService = appContext.getBean(IUserService.class);
			user = userService.getByEmailAndPassword(credentials[0], credentials[1]);
			redisUtil.write(credentials, user);
		} else {
			user = new UserCredentials();
			Integer valueOf = Integer.valueOf(userData[0]);
			user.setId(valueOf);
			UserRole valueOf2 = UserRole.valueOf(userData[1].toUpperCase());
			user.setUserRole(valueOf2);
			if(user.getUserRole()==null){
				System.out.println(userData[0] +"!"+ userData[1]);
				System.out.println(valueOf2+"??????????????????????????");
			}
		}

		UserAuthStorage userDataStorage = appContext.getBean(UserAuthStorage.class);

		if (user != null) {
			userDataStorage.setId(user.getId());
			userDataStorage.setUserRole(user.getUserRole());
			if(userDataStorage.getUserRole()==null){
				
			}
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

	private Language getLanguage(HttpServletRequest req) {
		Enumeration<String> headers = req.getHeaders("Accept-Language");
		if (headers.hasMoreElements()) {
			return Language.valueOf(headers.nextElement().toUpperCase());
		}
		return Language.RU;
	}

	private boolean verificationAccess(HttpServletRequest req, UserAuthStorage userDataStorage) {

		if (req.getRequestURI().contains("/product") && req.getMethod().toUpperCase().equals("POST")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}
		if (req.getRequestURI().contains("/product") && req.getMethod().toUpperCase().equals("PUT")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}
		if (req.getRequestURI().contains("/product") && req.getMethod().toUpperCase().equals("DELETE")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}

		if (req.getRequestURI().contains("/attribute") && req.getMethod().toUpperCase().equals("POST")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}

		if (req.getRequestURI().contains("/attribute") && req.getMethod().toUpperCase().equals("DELETE")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}

		if (req.getRequestURI().contains("/product-variant") && req.getMethod().toUpperCase().equals("POST")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		} 
		if (req.getRequestURI().contains("/product-variant") && req.getMethod().toUpperCase().equals("PUT")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}
		if (req.getRequestURI().contains("/product-variant") && req.getMethod().toUpperCase().equals("DELETE")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}

		if (req.getRequestURI().contains("/variant") && req.getMethod().toUpperCase().equals("POST")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}

		if (req.getRequestURI().contains("/variant") && req.getMethod().toUpperCase().equals("DELETE")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}

		if (req.getRequestURI().contains("/user/credentials")
				&& (req.getMethod().toUpperCase().equals("GET") || req.getMethod().toUpperCase().equals("PUT"))) {
			if (UserRole.CLIENT.equals(userDataStorage.getUserRole())) {
				String[] uri = req.getRequestURI().split("/");
				Integer id = Integer.valueOf(uri[3]);
				if (userDataStorage.getId().equals(id)) {
					return true;
				}
			}
			if (UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
				return true;
			}
		}

		if (req.getRequestURI().contains("/user/profile")
				&& (req.getMethod().toUpperCase().equals("GET") || req.getMethod().toUpperCase().equals("PUT"))) {
			if (UserRole.CLIENT.equals(userDataStorage.getUserRole())) {
				String[] uri = req.getRequestURI().split("/");
				Integer id = Integer.valueOf(uri[3]);
				if (userDataStorage.getId().equals(id)) {
					return true;
				}
			}
			if (UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
				return true;
			}
		}

		if (req.getRequestURI().contains("/user") && req.getMethod().toUpperCase().equals("GET")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
			return true;
		}

		if (req.getRequestURI().contains("/user") && req.getMethod().toUpperCase().equals("DELETE")
				&& UserRole.ADMIN.equals(userDataStorage.getUserRole())) {
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

		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(userDataStorage.getId()+"!"+userDataStorage.getUserRole());
		return false;
	}

	@Override
	public void destroy() {
	}

}
