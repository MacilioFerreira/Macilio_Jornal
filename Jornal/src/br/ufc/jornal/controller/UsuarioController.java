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
import br.ufc.jornal.model.Role;
import br.ufc.jornal.model.Usuario;

@Transactional
@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;	
	
	@Autowired
	private SecaoDao secaoDao;
	
	@Autowired
	private RoleDao roleDao;
	
	// Controlador Login
	@RequestMapping("formularioLogin")
	public String formularioLogin(Model model){
		List<Role> roles = roleDao.listar();
		model.addAttribute("roles", roles);
		return "/usuario/formulario_login";
	}

	
	
	@RequestMapping("efetuarLogin")
	public String efetuarLogin(Usuario user, HttpSession session){
 
		String senha = user.getSenha();
		String login = user.getLogin();
		long roleId = user.getRoleId();
               
        Usuario  userRef = usuarioDao.getUserLogin(login); 
		
        // Quando for inserir os interceptores, fazer o teste entre o valor que vem da seleção e o papel
        // Fazer a funcionalidade de ao clicar em um seção ele ver as noticias para a mesma.
		if(userRef != null && userRef.getSenha().equals(senha) && userRef.getRoleId().equals(roleId)){
			session.setAttribute("usuario", userRef);
			return "../../index";
		}
		
        return "redirect:formularioLogin";		
        
	}
	
	
	// Controlador Usuário 	
	 @RequestMapping("formularioUsuario")
	 public String formularioUsuario(){
		 return "/usuario/formulario_usuario";
	 }
	 
	 @RequestMapping("inserirUsuario")
	 public String inserirUsuario(Usuario usuario, HttpSession session){
			 
      // Testa se o Usuário já está cadastrado..
	  if(usuarioDao.getUserLogin(usuario.getLogin()) != null){ 
			 return "/usuario/formulario_usuario";
	  }
	  
	  Role papel = roleDao.getRoleId(1L); 
	  List<Role> roles = roleDao.usuarioRoles(usuario.getId());
	  roles.add(papel);
		 
	  usuario.setRoleId(1L);
	  usuario.setRoles(roles);
	  
	  this.usuarioDao.adicionar(usuario); 	 
	
	  return "redirect:formularioLogin";     
	 
	 }
     
	 @RequestMapping("listarUsuario")
	 public String listarUsuario(Model model){
		
		 List<Usuario> users = this.usuarioDao.listar();
		 model.addAttribute("usuarios", users);
		 model.addAttribute("quantidade", users.size());
		 
		 return "/usuario/listar_usuarios";		 
	 }
	 
	 
	 @RequestMapping("alterarUsuario")
	 public String editarUsuario(Usuario user){
		 
		 this.usuarioDao.alterar(user);
		 
		 return "redirect:listarUsuario";
	 }
	 
	 @RequestMapping("excluirUsuario")
	 public String excluirUsuario(Usuario user){
		 
		 this.usuarioDao.remover(user);
		 
		 return "redirect:listarUsuario";
	 }

	 // Controlador de Cadastro de Jornalista
	 
	 @RequestMapping("formularioJornalista")
	 public String formularioJornalista(Model model){
		 List<Role> roles = roleDao.listar();
		 model.addAttribute("roles", roles);
		return "/usuario/formulario_login_editor"; 
	 }
	 
	 @RequestMapping("efetuarLoginEditor")
	 public String efetuarLoginEditor (Usuario usuario, HttpSession session){
		 
		    String senha = usuario.getSenha();
			String login = usuario.getLogin();
	               
	        Usuario  userRef = usuarioDao.getUserLogin(login); 
	        if(userRef != null && userRef.getSenha().equals(senha) && userRef.getRoleId() == 2){
				session.setAttribute("usuario", userRef);
				return "/usuario/formulario_jornalista";
			}
			
	        return "redirect:formularioLoginEditor";	
	 }
	 
	 @RequestMapping("adicionarJornalista")
	 public String adicionarJornalista(Usuario usuario, HttpSession session){
		 	 
		 Usuario u = usuarioDao.getUserLogin(usuario.getLogin());
		 Long id_ref_jornalista = roleDao.getRoleNome("Jornalista");
		// Caso em que o Usuário está no banco, atualiza e seta um novo papel
		  if(u != null){ 
			  
			     Role userPapel = roleDao.getRoleId(id_ref_jornalista); 
				 List<Role> roles = roleDao.usuarioRoles(usuario.getId());
				 roles.add(userPapel);
				 
				 usuario.setId(u.getId());
				 usuario.setRoleId(id_ref_jornalista);
			 	 usuario.setRoles(roles);
			 	 
			 	 this.usuarioDao.alterar(usuario);
			 	 
			 	 return "redirect:formularioLogin";  

		  }else{	 // Caso em que o Usuário não está no banco, atualiza os papéis do usuário e inseri
	
   		         Role papel = roleDao.getRoleId(id_ref_jornalista); 
		         List<Role> roles = roleDao.usuarioRoles(usuario.getId());
		         roles.add(papel);

                 usuario.setRoleId(id_ref_jornalista);
	 	         usuario.setRoles(roles);
		 
		         this.usuarioDao.adicionar(usuario); 
		
		         return "redirect:formularioLogin";  
	       }
	 }		  
}
