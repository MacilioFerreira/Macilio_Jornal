package br.ufc.jornal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.jornal.dao.RoleDao;
import br.ufc.jornal.dao.SecaoDao;
import br.ufc.jornal.dao.UsuarioDao;
import br.ufc.jornal.model.Secao;

@Transactional
@Controller
public class SecaoController {
	
	@Autowired
	private UsuarioDao usuarioDao;	
	
	@Autowired
	private SecaoDao secaoDao;
	
	@Autowired
	private RoleDao roleDao;

	@RequestMapping("formularioSecao")
	public String formularioSecao(){
		  return "/secao/cadastrar_secao";
	}
	
	
	@RequestMapping("adicionarSecao")
	public String adicionarSecao(Secao s, HttpSession session){

		if(session != null && s.getTitulo() != null && s.getDescricao() != null){
			this.secaoDao.inserir(s);
			return "redirect:verCategorias";
	    }
		
		return "redirect:formularioSecao";			
			
	}
	
	@RequestMapping("verCategorias")
	public String verCategorias (Model model){
		
		List<Secao> secoes = this.secaoDao.listar();
		
		// Insiro na lista aquelas seções que possuiem noticias
		List<Secao> secoes_permitidas = new ArrayList<Secao>();
		for (Secao secao : secoes) {
			if (secao.getNoticias().isEmpty() == false) {
				secoes_permitidas.add(secao);
			}
		}
		// Passando as seções permitidas
		model.addAttribute("secoes", secoes_permitidas);
		
		return "/secao/listar_secoes";
	}
}
