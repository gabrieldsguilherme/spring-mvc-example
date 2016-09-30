package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	private static final String LISTA_JSP = "conta/lista";
	private static final int HTTP_STATUS_200 = 200;
	private static final String PAGAR_CONTA_URI = "/pagarConta";
	private static final String REMOVER_CONTA_URI = "/removerConta";
	private static final String LISTA_URI = "/lista";
	private static final String ADICIONAR_CONTA_URI = "/adicionarConta";
	private static final String FORMULARIO_URI = "/formulario";
	private static final String CONTA_ADICIONADA_JSP = "contaAdicionada";
	private static final String FORMULARIO_JSP = "formulario";
	
	private ContaDAO dao;
	
	@Autowired
	public ContaController(ContaDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(FORMULARIO_URI)
	public String formulario() {
		return FORMULARIO_JSP;
	}

	@RequestMapping(ADICIONAR_CONTA_URI)
	public String adicionarConta(@Valid Conta conta, BindingResult result) {
		if (result.hasErrors()) {
			return FORMULARIO_JSP;
		}
		dao.adiciona(conta);		
		System.out.println(conta.getDescricao() + " adicionada com sucesso!");

		return CONTA_ADICIONADA_JSP;
	}
	
	@RequestMapping(LISTA_URI)
	public ModelAndView lista() {
		List<Conta> contas = dao.lista();
		ModelAndView mv = new ModelAndView(LISTA_JSP);
		mv.addObject("contas", contas);
		return mv;
	}
	
	@RequestMapping(REMOVER_CONTA_URI)
	public void removerConta(Conta conta, HttpServletResponse response) {
		dao.remove(conta);
		response.setStatus(HTTP_STATUS_200);
	}
	
	@RequestMapping(PAGAR_CONTA_URI)
	public void pagarConta(Conta conta, HttpServletResponse response) {
		dao.paga(conta.getId());
		response.setStatus(HTTP_STATUS_200);
	}
	
}
