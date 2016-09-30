package br.com.caelum.contas.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN_JSP = "login";
	private static final String PARAMETER_USUARIO_LOGADO = "usuario_logado";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
	throws Exception {
		
		String uri = request.getRequestURI();
		if(uri.endsWith("login") || uri.endsWith("logar") || uri.contains("resources")) {
			return true;
		}
		
		if(request.getSession().getAttribute(PARAMETER_USUARIO_LOGADO)!=null) {
			return true;
		} else {
			response.sendRedirect(LOGIN_JSP);
			return false;
		}
		
	}
}
