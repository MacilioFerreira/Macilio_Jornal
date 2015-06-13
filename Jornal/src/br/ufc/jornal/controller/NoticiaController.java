package br.ufc.jornal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.jornal.dao.NoticiaDao;
import br.ufc.jornal.dao.SecaoDao;
import br.ufc.jornal.dao.UsuarioDao;
import br.ufc.jornal.model.Noticia;
import br.ufc.jornal.model.Secao;
import br.ufc.jornal.model.Usuario;

@Transactional
@Controller
public class NoticiaController {
	
	@Autowired
	private NoticiaDao noticiaDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private SecaoDao secaoDao;
	
	@RequestMapping("noticiaForm")
	public String noticiaForm(){
		return "/noticia/login_noticia";
	}
	
	@RequestMapping("efetuarLoginNoticia")
	public String efetuarLoginNoticia (Usuario user, HttpSession session, Model model){
		
		String senha = user.getSenha();
		String login = user.getLogin();
		
		Usuario usuario = usuarioDao.getUserLogin(login) ;
		
		if(usuario != null && usuario.getSenha().equals(senha) && usuario.user_role("Jornalista")){
			// Buscas as seções no banco e adiciona uma lista como atributo
			List<Secao> secoes = secaoDao.listar();
			model.addAttribute("secoes", secoes);
			// Adiciona o usuário a sessão.
			session.setAttribute("usuario", usuario);
			return "/noticia/formulario_noticia";
		}
				
		return "redirect:noticiaForm";
	}
	
	@RequestMapping("cadastrarNoticia")
	public String cadastrarNoticia(Noticia noticia, HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
	    if(usuario == null){
	        return "redirect:noticiaForm";	
	    }
	    
	    noticia.setAutorNoticia(usuarioDao.getUserId(usuario.getId()));
	    noticia.setSecao(secaoDao.getSecao(noticia.getSecaoId()));
	    
		this.noticiaDao.inserir(noticia);
		
		return "../../index";
	}
	
	@RequestMapping("listarNoticia")
	public String listarNoticia(Noticia noticia, Model model){	
		
		List<Noticia> noticias = this.noticiaDao.listar();
		model.addAttribute("noticia", noticias);	
		
		return "/noticia/listar_noticia"; 
	}
	
	@RequestMapping("alterarNoticia")
	public String alterarNoticia(Noticia n){		
		
		this.noticiaDao.alterar(n);
		
	    return "redirect:listarNoticia";
	}

	@RequestMapping("excluirNoticia")
	public String excluirNoticia(Noticia n, HttpSession session){ // Ajeitar isso, pois ele está diretamente relacionada com a listagem de noticias.
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		Usuario userRef = usuarioDao.getUserLogin(usuario.getLogin());
		
		if(userRef != null && userRef.getRoleId() == 3 && n.getAutorNoticia().equals(usuario) || userRef.getRoleId() == 2){
			this.noticiaDao.remover(n);	 
			return "../../index";
		}
		
		// Opção de remoção deve ser exibida, no momento em que se está lendo a noticia...
				
		return "redirect:noticiaForm";
	}

}
