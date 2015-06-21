package br.ufc.jornal.controller;

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

		if(session != null ){
            this.secaoDao.inserir(s);
            return "redirect:verCategorias";
	    }
			
		return "redirect:formularioSecao";
			
	}
	
	@RequestMapping("verCategorias")
	public String verCategorias (Model model){
		
		List<Secao> secoes = this.secaoDao.listar();
		model.addAttribute("secoes", secoes);
		
		return "/secao/listar_secoes";
	}
}
