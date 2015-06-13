package br.ufc.jornal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.jornal.dao.ClassificadoDao;
import br.ufc.jornal.model.Classificado;

@Transactional
@Controller
public class ClassificadoController {

	@Autowired
	private ClassificadoDao classificadoDao;
	
    @RequestMapping("formularioClassificado")
    public String formularioClassificado(){
    	return "";
    }
    
    @RequestMapping("adicionarClassificado")
    public String adicionarClassificado(Classificado c, HttpSession session){
    	

    	List<Classificado> classificados = this.classificadoDao.getClassificado();
    	
    	for (Classificado cl : classificados) {
			if(c.getMelhor_oferta() > cl.getMelhor_oferta()){
				this.classificadoDao.inserir(c);
			}else{
				return "formularioClassificado";
			}
		}
    	
    	return ""; // página de sucesso caso ele seja a maior oferta..
    }
    
    @RequestMapping("listarClassificado")
    public String listarClassificado(Model model){
    	
    	List<Classificado> classificados = this.classificadoDao.listar();
    	model.addAttribute("classificados", classificados);
    	model.addAttribute("quantidade", classificados.size());
    	
    	return "";
    }
    
    @RequestMapping("alterarClassificado")
    public String alterarClassificado(Classificado c){
    	this.classificadoDao.alterar(c);
    	return "redirect:listarClassificado";
    }
    
    @RequestMapping("excluirClassificado")
    public String excluirClassificado(Classificado c){
    	this.classificadoDao.remover(c);
    	return "redirect:listarClassificado";
    }

}