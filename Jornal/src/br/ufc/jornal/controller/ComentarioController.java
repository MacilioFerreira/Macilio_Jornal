package br.ufc.jornal.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.jornal.dao.ComentarioDao;
import br.ufc.jornal.dao.NoticiaDao;
import br.ufc.jornal.dao.UsuarioDao;
import br.ufc.jornal.model.Comentario;

@Transactional
@Controller
public class ComentarioController {

	@Autowired
	private ComentarioDao comentarioDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private NoticiaDao noticiaDao;
	
	
	
	@RequestMapping("alterarComentario")
	public String alterarComentario(Comentario c){
		this.comentarioDao.alterar(c);
		return "redirect:listarComentario";
	}
	
	@RequestMapping("excluirComentario")
	public String excluirComentario(Comentario c){
		
		return "redirect:listarComentario";
	}
}
