package br.com.caelum.contas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.UsuarioDAO;
import br.com.caelum.contas.modelo.Usuario;

@Controller
public class UsuarioController {
	
	private static final String DESLOGAR_URI = "/deslogar";
	private static final String PARAMETER_USUARIO_LOGADO = "usuario_logado";
	private static final String REDIRECT_LOGIN_JSP = "redirect:login";
	private static final String MENU_JSP = "menu";
	private static final String LOGAR_URI = "/logar";
	private static final String LOGIN_URI = "/login";

	@RequestMapping(LOGIN_URI)
	public String login(Usuario usuario) {
		return "usuario/login";
	}
	
	@RequestMapping(LOGAR_URI)
	public ModelAndView logar(Usuario usuario, HttpSession session) {
		UsuarioDAO dao = new UsuarioDAO();
		ModelAndView mv;
		if (dao.existeUsuario(usuario)) {
			session.setAttribute(PARAMETER_USUARIO_LOGADO, usuario);
			mv = new ModelAndView(MENU_JSP);
			mv.addObject("usuario", usuario);
			return mv;
		}
		mv = new ModelAndView(REDIRECT_LOGIN_JSP);
		return mv;
	}
	
	@RequestMapping(DESLOGAR_URI)
	public String deslogar(HttpSession session) {
		session.invalidate();
		return REDIRECT_LOGIN_JSP;
	}

}
