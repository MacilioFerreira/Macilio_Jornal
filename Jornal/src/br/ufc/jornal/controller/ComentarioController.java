package br.ufc.jornal.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.jornal.dao.ComentarioDao;
import br.ufc.jornal.model.Comentario;

@Transactional
@Controller
public class ComentarioController {

	@Autowired
	private ComentarioDao comentarioDao;
	
	@RequestMapping("formularioComentario")
	public String formularioComentario(){
        return ""; 
	}
	
	@RequestMapping("adicionarComentario")
	public String adicionarComentario(@Valid Comentario c, BindingResult result){
		
		if(result.hasErrors()){
			return "formularioComentario";
		}
		
		this.comentarioDao.inserir(c);
		
		return "";
	}
	
	@RequestMapping("listarComentario")
	public String listarComentario(Model model){
		
		List<Comentario> comentarios = this.comentarioDao.listar();
		model.addAttribute("comentarios", comentarios);
		model.addAttribute("quantidade", comentarios.size());
		
		return "";
	}
	
	@RequestMapping("alterarComentario")
	public String alterarComentario(Comentario c){
		this.comentarioDao.alterar(c);
		return "redirect:listarComentario";
	}
	
	@RequestMapping("excluirComentario")
	public String excluirComentario(Comentario c){
		/*this.comentarioDao.remover(c);*/
		return "redirect:listarComentario";
	}
}
