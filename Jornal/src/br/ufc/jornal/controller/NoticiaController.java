package br.ufc.jornal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.jornal.dao.ComentarioDao;
import br.ufc.jornal.dao.NoticiaDao;
import br.ufc.jornal.dao.SecaoDao;
import br.ufc.jornal.dao.UsuarioDao;
import br.ufc.jornal.model.Comentario;
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
	@Autowired
	private ComentarioDao comentarioDao;
	
	@RequestMapping("noticiaForm")
	public String noticiaForm(Model model){

		// Buscas as seções no banco e adiciona uma lista como atributo
		List<Secao> secoes = secaoDao.listar();
		model.addAttribute("secoes", secoes);

		return "/noticia/formulario_noticia";
	}
	
	@RequestMapping(value = "cadastrarNoticia", method=RequestMethod.POST)
	public String cadastrarNoticia(Noticia noticia, HttpSession session, @RequestParam("imagem")MultipartFile img){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
	    if(usuario == null){
	        return "redirect:noticiaForm";	
	    }
	    
	    if (usuario.user_role("Jornalista")) {
	    	
	    	// Inserindo a Imagem	para a noticia			 
			 if (!img.isEmpty()) {
				
				 try {

					String nome = new Date().getTime() + "-" + img.getOriginalFilename();
					String caminho = "/home/macilio/jornalImagens/img_noticia/"+nome;
					byte[] bytes = img.getBytes();
					BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(new File(caminho)));
					output.write(bytes);
					output.close();

					String nomePasta = "/img_noticia/"+nome;
					// Setando caminho da imagem.
					noticia.setCaminho_imagem(caminho);
					noticia.setNomeImagem(nomePasta);
				    System.out.println("+======="+caminho);
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				return "";
			}

	    	
			
	    	noticia.setAutorNoticia(usuarioDao.getUserId(usuario.getId()));
	    	noticia.setSecao(secaoDao.getSecao(noticia.getSecaoId()));
	    	noticia.setVisivel(true);
	    	
	    	this.noticiaDao.inserir(noticia);
	    	
	    	return "../../index";
		}
	    
	    return "redirect:noticiaForm";
	}
	
	@RequestMapping("lerManchetes")
	public String listarNoticia(Secao secao, Model model){	
		
		List<Noticia> noticias = this.noticiaDao.noticia_por_secao(secao.getSecaoId());
		model.addAttribute("noticias", noticias);	
		
		if (noticias.isEmpty()) {
			return "mensagem";
		}

		return "/noticia/listar_manchetes"; 
	}
	
	@RequestMapping("alterarNoticia")
	public String alterarNoticia(Noticia n){		
		
		this.noticiaDao.alterar(n);		
	    return "redirect:lerManchetes";
	}
	
	@RequestMapping("lerNoticia")
	public String lerNoticia(Noticia n, Model model, HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		Noticia noticia = noticiaDao.getNoticia(n.getNoticiaId());
		model.addAttribute("noticia", noticia);
		model.addAttribute("usuario", usuario);
		
		return "/noticia/listar_noticia";
		
	}

	// Opção de remoção deve ser exibida, no momento em que se está lendo a noticia...
	@RequestMapping("excluirNoticia")
	public String excluirNoticia(Noticia n, HttpSession session){ 
		
		//Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		Noticia noticia = noticiaDao.getNoticia(n.getNoticiaId());
		
		Usuario userRef = usuarioDao.getUserLogin(noticia.getAutorNoticia().getLogin());
		
		if(userRef != null && userRef.getRoleId() == 3 && noticia.getAutorNoticia().equals(userRef) || userRef.getRoleId() == 2){
			  
			  noticia.setVisivel(false);
			  return "redirect:verCategorias";
		}
		
				
		return "/noticia/listar_noticia"; 
	}

	// Inserindo comentários
	@RequestMapping("formularioComentario")
	public String formularioComentario(Noticia n, Model model){
		
		
        Noticia noticia = noticiaDao.getNoticia(n.getNoticiaId()); 
		model.addAttribute("noticia", noticia);
	

		return "/comentario/formulario_comentario";
		 
	}
	
	@RequestMapping("adicionarComentario")
	public String adicionarComentario( long id_noticia, String texto,  HttpSession session, Model model){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		Noticia noticia = noticiaDao.getNoticia(id_noticia);
		
		
		if(usuario != null && usuario.user_role("Leitor")){
			
			// Cria um novo comentário e seta seus valores de acordo com os passados
            Comentario coment = new Comentario();
            coment.setAutorComentario(usuarioDao.getUserLogin(usuario.getLogin()));
            coment.setNoticiaDeOrigem(noticiaDao.getNoticia(id_noticia));
            coment.setTexto(texto);

			// Inserindo Comentário
            this.comentarioDao.inserir(coment);
			
			// Adiciona o comentário a lista de comentários da noticia. Depois de inserir o comentário
			List<Comentario> coments = comentarioDao.comentarios(noticia.getNoticiaId());
			coments.add(coment);
			noticia.setComentarios(coments); // Atualiza comentarios da noticia.
	        
			// Atribuindo valores para serem listados
			model.addAttribute("noticia", noticia);

			return "/comentario/listar_comentario";
		}
		
		return "/comentario/formulario_comentario"; // Tenho que redirecionar para a noticia que acabou de ser comentada.. 
	}
}
